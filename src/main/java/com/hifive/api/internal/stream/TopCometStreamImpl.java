package com.hifive.api.internal.stream;



import com.hifive.api.internal.stream.connect.ConnectionLifeCycleListener;
import com.hifive.api.internal.stream.connect.HttpClient;
import com.hifive.api.internal.stream.connect.HttpResponse;
import com.hifive.api.internal.stream.message.StreamMessageConsume;
import com.hifive.api.internal.stream.message.StreamMsgConsumeFactory;
import com.hifive.api.internal.stream.message.TopCometMessageListener;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.internal.util.HifiveUtils;
import com.hifive.api.internal.util.RequestParametersHolder;
import com.hifive.api.internal.util.StringUtils;
import com.hifive.link.Logger;
import com.hifive.link.logging.LogUtil;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author zhenzi 2011-8-9 上午09:59:31
 */
public class TopCometStreamImpl implements TopCometStream {
	private static final Logger logger = LogUtil.getLoggerFactory(TopCometStreamImpl.class)
			.create(TopCometStreamImpl.class);
	private ConnectionLifeCycleListener connectionListener;
	private TopCometMessageListener cometMessageListener;
	private Configuration conf;
	private StreamMsgConsumeFactory msgConsumeFactory = null;
	/**
	 * 停掉全部连接
	 */
	private boolean gloableStop = false;
	private List<ControlThread> controlThreads = new ArrayList<ControlThread>();

	protected TopCometStreamImpl(Configuration conf) {
		this.conf = conf;
	}

	public void setConnectionListener(
			ConnectionLifeCycleListener connectionLifeCycleListener) {
		this.connectionListener = connectionLifeCycleListener;
	}

	public void setMessageListener(TopCometMessageListener cometMessageListener) {
		this.cometMessageListener = cometMessageListener;
	}

	public void start() {
		Set<TopCometStreamRequest> cometRequests = conf.getConnectReqParam();
		for (TopCometStreamRequest cometRequest : cometRequests) {
			if (cometRequest.getConnectListener() == null) {
				cometRequest.setConnectListener(connectionListener);
			}
			if (cometRequest.getMsgListener() == null) {
				if (cometMessageListener == null) {//如果又没有自己的message listener又没设置全局的message listener 则系统不能启动，抛错。
					throw new RuntimeException("Comet message listener must not null");
				}
				cometRequest.setMsgListener(cometMessageListener);
			}
		}
		msgConsumeFactory = new StreamMsgConsumeFactory(conf.getMinThreads(),
				conf.getMaxThreads(), conf.getQueueSize(),
				conf.getMsgConsumeThreadPoolRejectHandler());

		for (TopCometStreamRequest cometRequest : cometRequests) {
			// 这里不把线程设置成daemon线程的原因，如果设置成daemon线程后，如果主线程退出后这些线程都将自动退出
			ControlThread ct = new ControlThread(cometRequest);
			controlThreads.add(ct);
			Thread t = new Thread(ct, "stream-control-thread-connectid-"
					+ cometRequest.getConnectId());
			t.setDaemon(false);
			t.start();
		}
	}

	/**
	 * 连接管理线程
	 * 
	 * @author zhenzi
	 * 
	 *         2012-8-12 下午3:06:48
	 */
	public class ControlThread implements Runnable {
		private static final String threadName = "top-stream-consume-thread";
		private TopCometStreamConsume currentStreamConsume;
		private boolean isReconnect = false;// 是否客户端发起重连

		private String serverRespCode = StreamConstants.CLIENT_FIRST_CONNECT;
		private ReentrantLock lock = new ReentrantLock();
		private Condition controlCondition = lock.newCondition();
		// 发生了多少次readtimeout
		private int readTimeoutOccureTimes = 0;
		private long lastStartConsumeThread = System.currentTimeMillis();

		/**
		 * 停掉
		 */
		private boolean stop = false;

		private TopCometStreamRequest cometReq;

		public ControlThread(TopCometStreamRequest cometReq) {
			this.cometReq = cometReq;
		}

		public ReentrantLock getLock() {
			return lock;
		}

		public Condition getControlCondition() {
			return controlCondition;
		}

		public void setServerRespCode(String serverRespCode) {
			this.serverRespCode = serverRespCode;
		}

		public void run() {
			long lastSleepTime = 0;
			while (!stop) {
				if (gloableStop) {
					break;
				}
				try {
					if (StreamConstants.SERVER_DEPLOY.equals(serverRespCode)) {// 服务端在发布
						if (logger.isDebugEnabled()) {
							logger.debug("Server is upgrade sleep "
									+ conf.getSleepTimeOfServerInUpgrade()
									+ " seconds");
						}
						try {
							Thread.sleep(conf.getSleepTimeOfServerInUpgrade() * 1000);
						} catch (InterruptedException e) {
							// ignore;
						}
						startConsumeThread();
					} else if (/* 客户端第一次发起连接请求 */
					StreamConstants.CLIENT_FIRST_CONNECT.equals(serverRespCode)
							||
							/* 服务端主动断开了所有的连接 */
							StreamConstants.SERVER_REHASH
									.equals(serverRespCode)
							||
							/* 连接到达最大时间 */
							StreamConstants.CONNECT_REACH_MAX_TIME
									.equals(serverRespCode) ||
							/* 在一些异常情况下需要重连 */
							StreamConstants.RECONNECT.equals(serverRespCode)) {
						startConsumeThread();
					} else if (/* 客户端自己把自己踢开 */
					StreamConstants.CLIENT_KICKOFF.equals(serverRespCode) ||
					/* 服务端把客户端踢开 */
					StreamConstants.SERVER_KICKOFF.equals(serverRespCode)) {
						if ((StreamConstants.CLIENT_KICKOFF
								.equals(serverRespCode) && !isReconnect)
								|| StreamConstants.SERVER_KICKOFF
										.equals(serverRespCode)) {
							stop = true;
							if (currentStreamConsume != null) {
								currentStreamConsume.closed = true;
							}
							break;// 终止掉当前线程
						}
					} else {// 错误码设置出错，停止线程
						stop = true;
						break;
					}
					// 连接成功，开始休眠
					if (!stop && !gloableStop) {
						try {
							lock.lock();
							lastSleepTime = System.currentTimeMillis();
							controlCondition.await(
									conf.getHttpReconnectInterval(),
									TimeUnit.SECONDS);
							if (System.currentTimeMillis() - lastSleepTime >= (conf
									.getHttpReconnectInterval() - 5 * 60) * 1000) {
								/*
								 * 快要到达连接的最大时间了，需要重新发起连接
								 */
								serverRespCode = StreamConstants.RECONNECT;
								isReconnect = true;
							}// 否则，是由于某种原因被唤醒的
						} catch (Exception e) {
							logger.error(e);
						} finally {
							lock.unlock();
						}
					}
				} catch (Throwable e) {
					logger.error("Occur some error,stop the stream consume", e);
					stop = true;
					try {
						lock.lock();
						controlCondition.signalAll();
					} catch (Exception ex) {
						// ignore
					} finally {
						lock.unlock();
					}
				}
			}
			//关闭资源
			if(currentStreamConsume != null){
				currentStreamConsume.close();
			}
			// 此控制线程由于某种原因退出，从列表中移除掉
			controlThreads.remove(this);
		}

		private void startConsumeThread() {
			StreamMessageConsume stream = null;
			try {
				stream = getMsgConsume();
			} catch (Exception ex) {
				stop = true;
				gloableStop = true;
				if (cometReq.getConnectListener() != null) {
					cometReq.getConnectListener().onException(ex);
				}
				return;
			}
			currentStreamConsume = new TopCometStreamConsume(stream, this,
					cometReq.getConnectListener());
			Thread consumeThread = new Thread(currentStreamConsume, threadName);
			consumeThread.setDaemon(true);
			consumeThread.start();
			lastStartConsumeThread = System.currentTimeMillis();
			/**
			 * 重新连接以后，修改isReconnect=false
			 * 这里去掉，因为当消息量很大的时候会出现：
			 * 客户端重连先连接成功了，但是服务端在之前的连接上发送的client kickoff消息还没有处理完，
			 * 导致在收到这个消息的时候，在控制线程里面处理的时候因为isReconnect = false了，
			 * 导致控制线程认为是客户端踢出。
			 * 
			 * 这里去掉的问题就是：如果isv需要在别的地方部署同一个appkey+userid+connect_id的长连接
			 * 的时候需要把之前的那个关闭掉，否则会出现永远互相踢的过程。
			 */
			//isReconnect = false;
		}

		private StreamMessageConsume getMsgConsume()
				throws TopCometSysErrorException, Exception {
			if (cometReq.getConnectListener() != null) {
				cometReq.getConnectListener().onBeforeConnect();
			}
			HifiveHashMap param = new HifiveHashMap();
			param.put(StreamConstants.PARAM_APPKEY, cometReq.getAppkey());
			if (!StringUtils.isEmpty(cometReq.getUserId())) {
				param.put(StreamConstants.PARAM_USERID, cometReq.getUserId());
			}
			if (!StringUtils.isEmpty(cometReq.getConnectId())) {
				param.put(StreamConstants.PARAM_CONNECT_ID,
						cometReq.getConnectId());
			}
			if (!StringUtils.isEmpty(cometReq.getGroupId())) {
				param.put(StreamConstants.PARAM_GROUP_ID, cometReq.getGroupId());
			}
			if(cometReq.getIsReliable()!=null){
				param.put(StreamConstants.PARAM_IS_RELIABLE, cometReq.getIsReliable());
			}
			String timestamp = String.valueOf(System.currentTimeMillis());
			param.put(StreamConstants.PARAM_TIMESTAMP, timestamp);
			Map<String, String> otherParam = cometReq.getOtherParam();
			if (otherParam != null) {
				Iterator<Entry<String, String>> it = otherParam.entrySet()
						.iterator();
				while (it.hasNext()) {
					Entry<String, String> e = it.next();
					param.put(e.getKey(), e.getValue());
				}
			}
			RequestParametersHolder paramHolder = new RequestParametersHolder();
			paramHolder.setProtocalMustParams(param);
			String sign = null;
			try {
				sign = HifiveUtils.signTopRequestNew(paramHolder,
						cometReq.getSecret(), false);
				if (StringUtils.isEmpty(sign)) {
					throw new RuntimeException("Get sign error");
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			param.put(StreamConstants.PARAM_SIGN, sign);

			HttpClient httpClient = new HttpClient(conf, param);
			HttpResponse response = httpClient.post();
			response.setCometRequest(cometReq);
			StreamMessageConsume consume= new StreamMessageConsume(msgConsumeFactory, response,
					cometReq.getMsgListener(), this);
			consume.setConfiguration(conf);
			return consume;
		}

	}

	/**
	 * 从长连接的通道中读取消息
	 * 
	 * @author zhenzi
	 * 
	 *         2012-8-12 下午3:08:47
	 */
	class TopCometStreamConsume implements Runnable {
		private StreamMessageConsume stream;
		private boolean closed = false;
		private ControlThread ct;
		private ConnectionLifeCycleListener connectListener;

		TopCometStreamConsume(StreamMessageConsume stream, ControlThread ct,
				ConnectionLifeCycleListener connectListener) {
			this.stream = stream;
			this.ct = ct;
			this.connectListener = connectListener;
		}

		public void run() {
			while (!gloableStop && !closed && stream.isAlive()) {
				try {
					stream.nextMsg();
				} catch (IOException e) {// 出现了read time out异常
					// 资源清理
					if (stream != null) {
						try {
							stream.close();
						} catch (IOException e1) {
							//ignore;
						}
					}
					stream = null;
					closed = true;
					/**
					 * 30分钟内发送了10次read time out exception 告知isv，但是不退出，继续发起重新连接
					 */
					ct.readTimeoutOccureTimes++;
					if (System.currentTimeMillis() - ct.lastStartConsumeThread < 30 * 60 * 1000) {
						// 如果30分钟内，read time out发生了10次，则告知isv
						if (ct.readTimeoutOccureTimes >= 10) {
							if (connectListener != null) {
								try {
									connectListener.onMaxReadTimeoutException();
								} catch (Exception ex) {}
							}
							//告知完后清0
							ct.readTimeoutOccureTimes = 0;
						}
					} else {// 如果超过30分钟，则清0
						ct.readTimeoutOccureTimes = 0;
					}
					// 通知重连
					ct.serverRespCode = StreamConstants.RECONNECT;
					try {
						ct.lock.lock();
						ct.controlCondition.signalAll();
					} catch (Exception e2) {
					} finally {
						ct.lock.unlock();
					}
				}
			}
			// 出现异常情况下做资源清理
			close();
		}
		/**
		 * 在外面调用stop需要关闭整个长连接的时候，ControlThread会调用此方法，把一些资源正确的释放掉
		 */
		public void close(){
			if (this.stream != null) {
				try {
					this.stream.close();
				} catch (Exception e) {
//					logger.warn(e.getMessage(), e);
					//ignore;
				}
			}
		}
	}
	/**
	 * 在运行期添加新的长连接
	 * @param newClient
	 */
	public void addNewStreamClient(TopCometStreamRequest newClient){
		if(newClient.getConnectListener() == null){
			newClient.setConnectListener(connectionListener);
		}
		if (newClient.getMsgListener() == null) {
			newClient.setMsgListener(cometMessageListener);
		}
		ControlThread ct = new ControlThread(newClient);
		controlThreads.add(ct);
		Thread t = new Thread(ct, "stream-control-thread-connectid-"
				+ newClient.getConnectId());
		t.setDaemon(false);
		t.start();
	}
	/**
	 * 关闭长连接。
	 * 这里没有休眠一段时间，让服务端清理资源，
	 * 这样的话客户端只要保证每次发起连接请求的connectid是一样的就不会出现问题。
	 * 其实在客户端使用的过程中，客户端使用固定的connectid，在多连接情况下更应该
	 * 使用固定的connectid，并且不能超过服务端的最大连接数限制.
	 */
	public void stop() {
		logger.warn("start stop stream consume...");
		// 设置所有的线程都关闭掉
		gloableStop = true;
		// 关闭控制线程
		//这里先复制一份controlThreads，因为在关闭的时候，control thread会从list中移除掉自己。
		Object[] copyControlThreads = controlThreads.toArray();
		for (Object o : copyControlThreads) {
			if(o instanceof ControlThread){
				ControlThread ct = (ControlThread)o;
				try {
					ct.lock.lock();
					ct.controlCondition.signalAll();
				} catch (Exception e) {
					logger.error(e);
				} finally {
					ct.lock.unlock();
				}
			}
		}
		// 关闭处理数据的线程池
		msgConsumeFactory.shutdown();
		logger.warn("stream consume stoped...");
	}

}

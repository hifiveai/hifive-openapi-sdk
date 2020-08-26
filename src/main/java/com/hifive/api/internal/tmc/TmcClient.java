package com.hifive.api.internal.tmc;


import com.hifive.api.internal.util.HifiveUtils;
import com.hifive.api.internal.util.NamedThreadFactory;
import com.hifive.api.internal.util.StringUtils;
import com.hifive.link.LinkException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 消息服务客户端入口。
 * 
 * @author fengsheng
 * @since 1.0, May 4, 2013
 */
public class TmcClient {

	private static final Log log = LogFactory.getLog(TmcClient.class);

	// sign parameters
	private static final String TIMESTAMP = "timestamp";
	private static final String APP_KEY = "app_key";
	private static final String GROUP_NAME = "group_name";
	private static final String SIGN = "sign";

	private final AtomicBoolean connected = new AtomicBoolean(false);

	private InnerClient client;
	private MessageHandler messageHandler;
	private TmcHandler tmcHandler;

	private ThreadPoolExecutor threadPool;
	private int queueSize = 2000; // 消息缓冲队列大小
	private int threadCount = Runtime.getRuntime().availableProcessors() * 10; // 并发处理的线程数量
	// 由于TMC已支持push， pullRequest只需作为收取消息的补偿动作
	private int fetchPeriod = 30; // 定时获取消息周期（单位：秒）

	private boolean removeDuplicate = false; // 是否启用消息去重功能
	private KeySelector keySelector; // 默认对交易、商品、退款进行去重

	private Timer fetchTimer;
	private TimerTask fetchTimerTask;

	private String uri; // 消息通道服务地址
	private String appKey;
	private String groupName;

	public TmcClient(String appKey, String appSecret) {
		this(appKey, appSecret, "default"); // 默认分组+线上服务
	}

	public TmcClient(String appKey, String appSecret, String groupName) {
		this("ws://mc.api.Hifive.com/", appKey, appSecret, groupName); // 指定分组+线上服务
	}

	public TmcClient(String uri, String appKey, String appSecret, String groupName) {
		this.uri = uri;
		this.appKey = appKey;
		this.groupName = groupName;
		this.client = new InnerClient(new TmcIdentity(appKey, groupName));
		this.client.appKey = appKey;
		this.client.appSecret = appSecret;
		this.client.groupName = groupName;
	}

	protected void setUri(String uri) {
		this.uri = uri;
	}

	protected String getAppKey() {
		return this.appKey;
	}

	protected String getGroupName() {
		return this.groupName;
	}

	protected InnerClient getClient() {
		return this.client;
	}

	protected ThreadPoolExecutor getThreadPool() {
		return this.threadPool;
	}

	protected MessageHandler getMessageHandler() {
		return this.messageHandler;
	}

	public void setMessageHandler(MessageHandler handler) {
		this.messageHandler = handler;
	}

	protected TmcHandler getTmcHandler() {
		return this.tmcHandler;
	}

	protected int getQueueSize() {
		return this.queueSize;
	}

	public void setQueueSize(int queueSize) {
		if (queueSize < threadCount) {
			throw new IllegalArgumentException("queue size must greater than thread count");
		}
		this.queueSize = queueSize;
	}

	public void setThreadCount(int threadCount) {
		if (threadCount < 1) {
			throw new IllegalArgumentException("thread count must greater than 1");
		}
		this.threadCount = threadCount;
	}

	public void setFetchPeriod(int fetchPeriod) {
		if (fetchPeriod < 1) {
			throw new IllegalArgumentException("fetch period must greater than 1");
		}
		this.fetchPeriod = fetchPeriod;
	}

	public void setRemoveDuplicate(boolean removeDuplicate) {
		this.removeDuplicate = removeDuplicate;
	}

	protected KeySelector getKeySelector() {
		return this.keySelector;
	}

	public void setKeySelector(KeySelector keySelector) {
		this.keySelector = keySelector;
	}

	public void connect() throws LinkException {
		if (!connected.compareAndSet(false, true)) {
			return;
		}
		if (this.removeDuplicate) {
			this.tmcHandler = new DuplicateRemoverTmcHandler(this);
		} else {
			this.tmcHandler = new TmcHandler(this);
		}
		this.client.setMessageHandler(this.tmcHandler);
		this.threadPool = new ThreadPoolExecutor(threadCount, threadCount, fetchPeriod * 2, TimeUnit.MICROSECONDS,
				new ArrayBlockingQueue<Runnable>(queueSize), new NamedThreadFactory("tmc-worker"), new CallerRunsPolicy());
		try {
			this.client.connect(uri);
		} catch (LinkException e) {
			connected.set(false);
			throw e;
		}
		this.doPullRequest();
	}

	/**
	 * 向指定的主题发布一条与用户无关的消息。
	 * 
	 * @param topic
	 *            主题名称
	 * @param content
	 *            严格根据主题定义的消息内容（JSON/XML）
	 */
	public void send(String topic, String content) throws LinkException {
		if (StringUtils.isEmpty(topic)) {
			throw new LinkException("topic is required");
		}
		if (StringUtils.isEmpty(content)) {
			throw new LinkException("content is required");
		}

		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put(MessageFields.KIND, MessageKind.Data);
		msg.put(MessageFields.DATA_TOPIC, topic);
		msg.put(MessageFields.DATA_CONTENT, content);
		this.client.sendAndWait(msg, 2000);
	}

	/**
	 * 向指定的主题发布一条与用户相关的消息。
	 * 
	 * @param topic
	 *            主题名称
	 * @param content
	 *            严格根据主题定义的消息内容（JSON/XML）
	 * @param session
	 *            用户授权码
	 */
	public void send(String topic, String content, String session) throws LinkException {
		if (StringUtils.isEmpty(topic)) {
			throw new LinkException("topic is required");
		}
		if (StringUtils.isEmpty(content)) {
			throw new LinkException("content is required");
		}
		if (StringUtils.isEmpty(session)) {
			throw new LinkException("session is required");
		}

		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put(MessageFields.KIND, MessageKind.Data);
		msg.put(MessageFields.DATA_TOPIC, topic);
		msg.put(MessageFields.DATA_CONTENT, content);
		msg.put(MessageFields.DATA_INCOMING_USER_SESSION, session);
		this.client.sendAndWait(msg, 2000);
	}

	public void close() {
		this.close("client closed");
	}

	public void close(String reason) {
		this.stopPullRequest();
		this.client.disconnect(reason);
		if (this.tmcHandler != null) {
			this.tmcHandler.close();
		}
		if (this.threadPool != null) {
			this.threadPool.shutdown();
			this.threadPool = null;
		}
		log.warn("tmc client closed");
	}

	protected void pullRequest() {
		try {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put(MessageFields.KIND, MessageKind.PullRequest);
			if (this.client.isOnline()) {
				this.client.send(msg);
			}
		} catch (Exception e) {
			log.warn("pull request error", e);
		}
	}

	private void doPullRequest() {
		this.stopPullRequest();
		this.fetchTimerTask = new TimerTask() {
			public void run() {
				pullRequest();
			}
		};
		Date begin = new Date();
		begin.setTime(begin.getTime() + fetchPeriod * 1000L);
		this.fetchTimer = new Timer("tmc-pull", true);
		this.fetchTimer.schedule(this.fetchTimerTask, begin, fetchPeriod * 1000L);
	}

	private void stopPullRequest() {
		if (this.fetchTimer != null) {
			this.fetchTimer.cancel();
			this.fetchTimer = null;
		}
	}

	class InnerClient extends MixClient {
		private String appKey;
		private String appSecret;
		private String groupName;

		public InnerClient(TmcIdentity id) {
			super(id);
		}

		@Override
		protected Map<String, Object> createConnectHeaders() {
			Map<String, String> headers = new HashMap<String, String>();
			Map<String, Object> headers2 = new HashMap<String, Object>();
			headers.put(TIMESTAMP, String.valueOf(System.currentTimeMillis()));
			headers2.put(TIMESTAMP, String.valueOf(System.currentTimeMillis()));
			headers.put(APP_KEY, this.appKey);
			headers2.put(APP_KEY, this.appKey);
			headers.put(GROUP_NAME, this.groupName);
			headers2.put(GROUP_NAME, this.groupName);
			String sign = null;
			try {
				sign = HifiveUtils.signTopRequestNew(headers, this.appSecret, false);
			} catch (Exception e) {
				log.error("sign error", e);
			}
			headers.put(SIGN, sign);
			headers2.put(SIGN, sign);
			return headers2;
		}
	}

}
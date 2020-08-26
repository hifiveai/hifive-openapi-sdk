package com.hifive.api.internal.stream.message;



import com.hifive.api.Constants;
import com.hifive.api.internal.stream.Configuration;
import com.hifive.api.internal.stream.StreamConstants;
import com.hifive.api.internal.stream.TopCometStreamImpl;
import com.hifive.api.internal.stream.connect.HttpResponse;
import com.hifive.api.internal.util.*;
import com.hifive.link.Logger;
import com.hifive.link.logging.LogUtil;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author zhenzi
 * 2011-8-12 上午10:05:00
 */
public class StreamMessageConsume{
	private static final Logger log = LogUtil.getLoggerFactory(StreamMessageConsume.class).create(StreamMessageConsume.class);
	private static final Pattern pattern = Pattern.compile("\\{\"packet\":\\{\"code\":(\\d+)(,\"confirm_id\":(\\d+))?(,\"msg\":(.+))?\\}\\}");
	/*
	 * 用于读取消息数据
	 */
	private HttpResponse response;
	
	private Configuration configuration;
	
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	/*
	 * 消息处理线程池 
	 */
	private StreamMsgConsumeFactory msgConsumeFactory;
	//用于标记消息通道知否还可用
	private boolean streamAlive = true;
	/*
	 * isv提供的消息监听器 
	 */
	private TopCometMessageListener msgListener;
	//用于与Controlthread交互，控制连接的管理
	private Condition controlCondition;
	private Lock lock;
	private TopCometStreamImpl.ControlThread ct;
	/**
	 * 因为在服务端kickoff的时候可能会发送多个server kickoff。
	 * 尤其是在消息量大的情况下。这里做个控制，对于一个连接，
	 * 给isv的只有一个serverkickoff消息
	 */
	private boolean isServerKickOffSend = false;
	
	public StreamMessageConsume(StreamMsgConsumeFactory msgConsumeFactory,
			HttpResponse response,TopCometMessageListener msgListener, TopCometStreamImpl.ControlThread ct) {
		this.msgConsumeFactory = msgConsumeFactory;
		this.response = response;
		this.msgListener = msgListener;
		this.controlCondition = ct.getControlCondition();
		this.lock = ct.getLock();
		this.ct = ct;
		
		if(response.getCometRequest().getIsReliable()){
			confirmBackgroud=new ConfirmBackgroud();
			Thread confirmThread=new Thread(confirmBackgroud,"comet-msg-confirm-thread");
			confirmThread.setDaemon(true);
			confirmThread.start();
		}
		
		
	}
	/**
	 * 给consume thread提供的读取消息的方法。
	 * @throws IOException
	 */
	public void nextMsg() throws IOException {
		if(!streamAlive){
			throw new IOException("Stream closed");
		}
        try {
            String line = response.getMsg();
            if(line == null){//正常读到流的末尾了。
            	streamAlive = false;
            	response.close();
            	return;
            }
            msgConsumeFactory.consume(new StreamEvent(line));
        } catch (IOException e) {//这个时候抛出：SocketTimeoutException
            response.close();
            streamAlive = false;
            throw e;
        }catch(RejectedExecutionException rejectException){
        	log.error("Message consume thread pool is full:", rejectException);
        }catch(NullPointerException npe){
        	log.error("Null point exception:", npe);
        }
    }
	public boolean isAlive(){
		return streamAlive;
	}
	/**
	 * 用于解析消息
	 * @author zhenzi
	 *
	 * 2012-8-12 下午2:21:27
	 */
	private class StreamEvent implements Runnable{
		private String msg;
		public StreamEvent(String msg){
			this.msg = msg;
		}
		public void run() {
			parseLine(msg);
		}
		
	}
	
	/**
	 * 解析消息，并且调用message listener的对应方法。
	 * @param msg
	 */
	private void parseLine(String msg){
		if(!StringUtils.isEmpty(msg)){
			try{
				Matcher matcher = pattern.matcher(msg);
				if (matcher.find()) {
					String code = matcher.group(MATCH_INDEX_CODE);
					if(StreamConstants.NEW_MESSAGE.equals(code)){
						if(msgListener instanceof DataPushMsgListener){
							MessageStatus msgStatus=new MessageStatus();
							String confirmId=matcher.group(MATCH_INDEX_CONFIRMID);
							msgStatus.setConfirmId(confirmId);
							((DataPushMsgListener)msgListener).onReceiveMsg(matcher.group(MATCH_INDEX_MSG),msgStatus);
							if(confirmId!=null&&!msgStatus.isNeedRollback()){
								doConfirm(confirmId);
							}
						}else{
							msgListener.onReceiveMsg(matcher.group(MATCH_INDEX_MSG));
						}
						
					}else if(StreamConstants.HEAT_BEAT.equals(code)){
						msgListener.onHeartBeat();
					}else if(StreamConstants.CONNECT_REACH_MAX_TIME.equals(code)){
						weakUp(code);
					}else if(StreamConstants.DISCARD_MESSAGE.equals(code)){
						msgListener.onDiscardMsg(matcher.group(MATCH_INDEX_MSG));
					}else if(StreamConstants.SERVER_DEPLOY.equals(code)){
						msgListener.onServerUpgrade(matcher.group(MATCH_INDEX_MSG));
						weakUp(code);
					}else if(StreamConstants.SERVER_REHASH.equals(code)){
						msgListener.onServerRehash();
						weakUp(code);
					}else if(StreamConstants.CLIENT_KICKOFF.equals(code)){
						msgListener.onClientKickOff();
						weakUp(code);
					}else if(StreamConstants.SERVER_KICKOFF.equals(code)){
//						msgListener.onServerKickOff();
						weakUp(code);
					}else if(StreamConstants.CONNECT_SUCCESS.equals(code)){
						msgListener.onConnectMsg(matcher.group(MATCH_INDEX_MSG));
					}else {
						msgListener.onOtherMsg(matcher.group(MATCH_INDEX_MSG));
					}
				}
			}catch(Exception e){
				msgListener.onException(e);
			}
		}
	}
	private void weakUp(String code){
		try{
			lock.lock();
			//如果不是服务端踢出
			if(!StreamConstants.SERVER_KICKOFF.equals(code)){
				ct.setServerRespCode(code);
				controlCondition.signalAll();
			}else if(!isServerKickOffSend){//如果是服务端踢出，但是第一次处理这个code
				isServerKickOffSend = true;
				ct.setServerRespCode(code);
				msgListener.onServerKickOff();//对于服务端踢出这个消息在这里特殊处理一下
				controlCondition.signalAll();
			}else {//服务端踢出，但是第二次处理这个code，则忽略
				controlCondition.signalAll();
			}
		}catch(Exception e){
			//ignore
		}finally{
			lock.unlock();
		}
	}
	/**
	 * 用于关闭整个数据通道
	 * @throws IOException
	 */
	public void close() throws IOException {
		streamAlive = false;
		if(confirmBackgroud!=null){
			confirmBackgroud.stop=true;
		}
		response.close();
	}
	
	private ConfirmBackgroud confirmBackgroud=null;
	private final static int MATCH_INDEX_CODE=1;
	private final static int MATCH_INDEX_CONFIRMID=3;
	private final static int MATCH_INDEX_MSG=5;
	
	private Queue<String> idsQueue=new LinkedBlockingQueue<String>();
	private Object confirmWaitLock=new Object();
	
	private void doConfirm(String confirmId){
		idsQueue.add(confirmId);
		if(idsQueue.size()>=50){
			synchronized (confirmWaitLock) {
				confirmWaitLock.notifyAll();
			}
			
		}
	}
	private void callConfirmApi(List<String> idList) throws IOException{
		HifiveHashMap aMap=new HifiveHashMap();
		StringBuilder sb=new StringBuilder(idList.get(0));//方法调用者保证list的size大于1
		for(int i=1;i<idList.size();i++){
			sb.append(",");
			sb.append(idList.get(i));
		}
		aMap.put("confirm_ids", sb.toString());	
		String groupId=this.response.getCometRequest().getGroupId();
		aMap.put("group_id", groupId);		
		doPost("Hifive.notify.msg.confirm",aMap) ;
	}
	private String doPost(String methodName,HifiveHashMap aMap) throws IOException{
		RequestParametersHolder holder=new RequestParametersHolder();
		HifiveHashMap pMustMap=new HifiveHashMap();
		pMustMap.put("method", methodName);
		pMustMap.put("timestamp", System.currentTimeMillis()+"");
		pMustMap.put("app_key", this.response.getCometRequest().getAppkey());
		pMustMap.put("v", "2.0");
		pMustMap.put("format", Constants.FORMAT_JSON);
		holder.setProtocalMustParams(pMustMap);
		
		HifiveHashMap pOptMap=new HifiveHashMap();
		pOptMap.put("sign_method", "hmac");
		holder.setProtocalOptParams(pOptMap);
		
		holder.setApplicationParams(aMap);
		String sign= HifiveUtils.signTopRequestNew(holder, this.response.getCometRequest().getSecret(), true);
		pMustMap.put("sign", sign);
		
		Map<String,String> params=new HashMap<String,String>();
		params.putAll(pMustMap);
		params.putAll(pOptMap);
		params.putAll(aMap);
		String rsp= WebUtils.doPost(configuration.getRestApiUrl(), params,"utf-8",0,0);
		if(rsp!=null&&rsp.startsWith("{\"error_response\":")){
			log.error(methodName+"_return_error^_^"+rsp);
		}
		return rsp;
	}
	//后台确认程序
	private class ConfirmBackgroud implements Runnable{
			private Boolean stop=false;
			
			public void run() {
				long lastConfirmTime=System.currentTimeMillis();
				while(!stop){					
					List<String>list=new ArrayList<String>();
					for(int count=0;count<50;count++){
						String confirmId=idsQueue.poll();
						if(confirmId!=null){
							list.add(confirmId);
						}else{
							break;
						}
					}
					if(list.size()>0){
						if(list.size()<50){
							long now=System.currentTimeMillis();
							long time=now-lastConfirmTime;
							lastConfirmTime=now;
							if(time<60000){//小于1分钟
								try {
									synchronized (confirmWaitLock) {
										long waitTime=60000-time;
										if(waitTime>1000){//小于1s就不休眠了，没有必要
											confirmWaitLock.wait(waitTime);
										}
										
									}
								} catch (InterruptedException e) {									
								}
							}
						}
						try {
							callConfirmApi(list) ;
						} catch (IOException e) {
							log.error("call Hifive.notify.msg.confirm failed ", e);
						}
					}else{
						try {	
							synchronized (confirmWaitLock) {
								confirmWaitLock.wait(1000*10);
							}
						} catch (InterruptedException e) {
						}
					}
				}
			}
		}
}

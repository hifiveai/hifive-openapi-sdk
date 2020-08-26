package com.hifive.api.internal.stream;



import com.hifive.api.internal.stream.connect.HttpConnectionConfiguration;
import com.hifive.api.internal.stream.message.MessageHandlerConfiguration;

import java.util.*;
import java.util.concurrent.RejectedExecutionHandler;

/**
 * 初始化stream client需要的一些配置
 * @author zhenzi
 * 2011-8-8 下午05:45:14
 */
public class Configuration implements HttpConnectionConfiguration, MessageHandlerConfiguration {
	private String connectUrl = "http://stream.api.Hifive.com/stream";
	private String restApiUrl = "http://gw.api.Hifive.com/router/rest";//此参数对于无需确认的消息无用
	
	private int httpConnectionTimeout = 5;//默认连接超时时间5s
	/*
	 * 由于服务端会间隔30s会发送一个心跳包，所以超时间比1分钟稍多点，使用者最好不要修改这个参数
	 */
	private int httpReadTimeout = 60 + 30;//90s
	private int httpConnectRetryCount = 225;//默认重试225次，如果每次连接的间隔在16s，那么默认尝试重连1个小时。
	private int httpConnectRetryInterval = 16;//16s，如果连接失败，两次连接之间的间隔时间。
	private int sleepTimeOfServerInUpgrade = 5 * 60;//当服务端在发布的时候休眠的时间，默认5分钟，
	private Map<String,String> reqHeader;
	/*
	 * 因为服务端目前在24小时后会断开连接，所以为了最低程度的降低消息丢失，在服务端断开之间客户端重连一次
	 * 默认是23小时加55分钟后自动重连
	 */
	private int httpReconnectInterval = (23 * 60 + 55) * 60;
	
	//默认处理消息的线程池最小线程为10个
	private int minThreads = 5;
	private int maxThreads = 100;
	/*
	 * 假设每条消息为1k大小，队列里可以存大约50M的数据
	 */
	private int queueSize = 50000;
	/*
	 * 允许isv注入在消息处理线程出现RejectedExecutionException 后的处理方法。
	 * 默认使用AbortPolicy
	 */
	private RejectedExecutionHandler msgConsumeThreadPoolRejectHandler;
	
	private Set<TopCometStreamRequest> connectReqParam;
	/**
	 * 1，userid是用户的id，是大于0的整数。
	 * 如果不需要传入userid，则可以传入小于等于0的数值。
	 * </br>
	 * 2，如果不指定userid，则所有与此appkey关联的所有用户的消息都会发送到此连接上</br>
	 * @param appkey
	 * @param secret
	 * @param userid 传入用户的userid
	 */
	public Configuration(String appkey,String secret,String userid){
		this(appkey,secret,userid,(String)null);
	}
	/**
	 * 1，userid是用户的id，是大于0的整数。
	 * 如果不需要传入userid，则可以传入小于等于0的数值。
	 * </br>
	 * 2，如果不指定userid，则所有与此appkey关联的所有用户的消息都会发送到此连接上</br>
	 * 3，connectId为连接标识（用于多连接）。可以为空。对于多连接的详细描述请参考文档。
	 * @param appkey
	 * @param secret
	 * @param userid 传入用户的userid
	 * @param connectId 连接标识
	 */
	public Configuration(String appkey,String secret,String userid,String connectId){
		TopCometStreamRequest cometReq = new TopCometStreamRequest(appkey,secret,userid,connectId);
		connectReqParam = new HashSet<TopCometStreamRequest>(1);
		connectReqParam.add(cometReq);
	}
	
	
	/**
	 * 用于多连接
	 * @param cometRequest
	 */
	public Configuration(Set<TopCometStreamRequest> cometRequest){
		if(cometRequest == null || (cometRequest != null && cometRequest.size() == 0)){
			throw new RuntimeException("comet request param is null");
		}
		this.connectReqParam = cometRequest;
	}
	//-------
	/**
	 * 三方集成用，如果使用top的主动消息推送，请不要用这个接口
	 * @param appkey
	 * @param secret
	 * @param connectId
	 * @param topics
	 * @param otherParam
	 */
	public Configuration(String appkey,String secret,String connectId,List<String> topics,Map<String,String> otherParam){
		TopCometStreamRequest cometReq = new TopCometStreamRequest(appkey,secret,null,connectId);
		if(otherParam == null){
			otherParam = new HashMap<String,String>(1);
		}
		if(topics != null && topics.size() > 0){
			StringBuilder topic = new StringBuilder();
			for (String t : topics) {
				topic.append(t).append(";");
			}
			otherParam.put("topic", topic.substring(0, topic.length() - 1));
		}
		cometReq.setOtherParam(otherParam);
		connectReqParam = new HashSet<TopCometStreamRequest>(1);
		connectReqParam.add(cometReq);
	}
	//-----------http connection config
	public void setHttpConnectionTimeout(int httpConnectionTimeout){
		this.httpConnectionTimeout = httpConnectionTimeout;
	}
	public int getHttpConnectionTimeout() {
		return httpConnectionTimeout;
	}
	public int getHttpReadTimeout() {
		return httpReadTimeout;
	}
	/**
	 * 由于服务端会间隔约30秒会发送一个心跳包，所以超时间比1分钟稍多点，使用者最好不要修改这个参数
	 * @param httpReadTimeout
	 */
	public void setHttpReadTimeout(int httpReadTimeout){
		this.httpReadTimeout = httpReadTimeout;
	}
	public void setHttpConnectRetryCount(int httpConnectRetryCount){
		this.httpConnectRetryCount = httpConnectRetryCount;
	}
	public int getHttpConnectRetryCount() {
		return httpConnectRetryCount;
	}
	/**
	 * 方便测试使用，生成环境请不要调用此方法
	 * @param connectUrl
	 */
	public void setConnectUrl(String connectUrl){
		this.connectUrl = connectUrl;
	}
	public String getConnectUrl() {
		return connectUrl;
	}
	public String getRestApiUrl() {
		return restApiUrl;
	}
	/**
	 * 方便测试使用，生成环境请不要调用此方法	 * l
	 */
	public void setRestApiUrl(String restApiUrl) {
		this.restApiUrl = restApiUrl;
	}
	public void setHttpConnectRetryInterval(int httpConnectRetryInterval){
		this.httpConnectRetryInterval = httpConnectRetryInterval;
	}
	public int getHttpConnectRetryInterval() {
		return httpConnectRetryInterval;
	}
	public void setSleepTimeOfServerInUpgrade(int sleepSecond){
		this.sleepTimeOfServerInUpgrade = sleepSecond;
	}
	public int getSleepTimeOfServerInUpgrade() {
		return sleepTimeOfServerInUpgrade;
	}
	/*
	 * 因为服务端目前在24小时后会断开连接，所以为了最低程度的降低消息丢失，在服务端断开之间客户端重连一次(non-Javadoc)
	 */
	public void setHttpReconnectInterval(int httpReconnectInterval){
		this.httpReconnectInterval = httpReconnectInterval;
	}
	public int getHttpReconnectInterval() {
		return httpReconnectInterval;
	}
	public void setRequestHeader(Map<String,String> reqHeader){
		this.reqHeader = reqHeader;
	}
	public Map<String, String> getRequestHeader() {
		return reqHeader;
	}
	//------------------http connection config end-
	//------------------message factory config
	public void setMinThreads(int minThreads){
		this.minThreads = minThreads;
	}
	public int getMinThreads() {
		return minThreads;
	}
	public void setMaxThreads(int maxThreads){
		this.maxThreads = maxThreads;
	}
	public int getMaxThreads() {
		return maxThreads;
	}
	public void setQueueSize(int queueSize){
		this.queueSize = queueSize;
	}
	public int getQueueSize() {
		return queueSize;
	}
	public RejectedExecutionHandler getMsgConsumeThreadPoolRejectHandler() {
		return msgConsumeThreadPoolRejectHandler;
	}
	public void setMsgConsumeThreadPoolRejectHandler(
			RejectedExecutionHandler msgConsumeThreadPoolRejectHandler) {
		this.msgConsumeThreadPoolRejectHandler = msgConsumeThreadPoolRejectHandler;
	}
	//--------message factory config end
	public Set<TopCometStreamRequest> getConnectReqParam() {
		return connectReqParam;
	}
}

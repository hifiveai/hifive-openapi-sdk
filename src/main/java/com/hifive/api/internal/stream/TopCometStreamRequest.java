package com.hifive.api.internal.stream;


import com.hifive.api.internal.stream.connect.ConnectionLifeCycleListener;
import com.hifive.api.internal.stream.message.TopCometMessageListener;
import com.hifive.api.internal.util.HifiveUtils;
import com.hifive.api.internal.util.StringUtils;

import java.util.Map;

/**
 * 请求参数
 * @author zhenzi
 * 2012-1-4 上午11:34:35
 */
public class TopCometStreamRequest {
	private String appkey;
	private String secret;
	private String userId;
	private String connectId;
	private String groupId=GroupEnum.GROUP_DEFAULT.getGroupId();//只能是1或者2，默认为1。如果是不可靠模式则groupId=1,如果是可靠模式，则groupId=2;
	private boolean isReliable;//是否使用可靠保证(即消息确认模式)。入塔的appkey设置有效，否则服务端忽略
	
	
	
	/**
	 * 其他业务参数
	 */
	private Map<String,String> otherParam;
	private ConnectionLifeCycleListener connectListener;
	private TopCometMessageListener msgListener;
	
	public TopCometStreamRequest(String appkey,String secret,String userId,String connectId){
		if(StringUtils.isEmpty(appkey)){
			throw new RuntimeException("appkey is null");
		}
		if(StringUtils.isEmpty(secret)){
			throw new RuntimeException("secret is null");
		}
		if(!StringUtils.isEmpty(userId)){
			try{
				Long.parseLong(userId);
			}catch(Exception e){
				throw new RuntimeException("userid must a number type");
			}
		}
		if(StringUtils.isEmpty(connectId)){
			this.connectId = HifiveUtils.getLocalNetWorkIp();
		}else{
			this.connectId = connectId;
		}		
		this.appkey = appkey;
		this.secret = secret;
		this.userId = userId;
		
		
		
	}
	public void setIsReliable(Boolean isReliable) {
		this.isReliable = isReliable;
	}
	public void setGroup(GroupEnum group){
		if(group!=null){
			this.groupId=group.getGroupId();		
		}
	}
	public String getAppkey() {
		return appkey;
	}
	public String getSecret() {
		return secret;
	}
	public String getUserId() {
		return userId;
	}
	
	public String getConnectId() {
		return connectId;
	}
	
	public String getGroupId() {
		return groupId;
	}
	
	public Boolean getIsReliable() {
		return isReliable;
	}
	
	public ConnectionLifeCycleListener getConnectListener() {
		return connectListener;
	}
	public void setConnectListener(ConnectionLifeCycleListener connectListener) {
		this.connectListener = connectListener;
	}
	public TopCometMessageListener getMsgListener() {
		return msgListener;
	}
	public void setMsgListener(TopCometMessageListener msgListener) {
		this.msgListener = msgListener;
	}
	
	public Map<String, String> getOtherParam() {
		return otherParam;
	}
	public void setOtherParam(Map<String, String> otherParam) {
		this.otherParam = otherParam;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appkey == null) ? 0 : appkey.hashCode());
		result = prime * result
				+ ((connectId == null) ? 0 : connectId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TopCometStreamRequest other = (TopCometStreamRequest) obj;
		if (appkey == null) {
			if (other.appkey != null)
				return false;
		} else if (!appkey.equals(other.appkey))
			return false;
		if (connectId == null) {
			if (other.connectId != null)
				return false;
		} else if (!connectId.equals(other.connectId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if(groupId == null){
			if(other.groupId!=null)
				return false;			
		}else if(!groupId.equals(other.groupId))
			return false;
		return true;
	}
	
}

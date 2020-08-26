package com.hifive.api.internal.stream.message;

/**
 * @author junmu
 *
 */
public class MessageStatus {
	
	private String confirmId;//只有需要确认的消息会有这个值
	
	private boolean needRollback=true;//是否需要回滚，即是否消息重发。false表示已经正确处理，不需要重发
	
	public String getConfirmId() {
		return confirmId;
	}

	public void setConfirmId(String confirmId) {
		this.confirmId = confirmId;
	}

	public boolean isNeedRollback() {
		return needRollback;
	}

	public void setNeedRollback(boolean needRollback) {
		this.needRollback = needRollback;
	}


	
	
	
}

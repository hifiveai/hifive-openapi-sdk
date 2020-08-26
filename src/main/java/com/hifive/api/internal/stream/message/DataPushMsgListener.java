package com.hifive.api.internal.stream.message;

/**
 * 支持消息确认功能
 * 对于实现了DataPushMsgListener接口的监听器类，
 * 		系统将调用 onReceiveMsg(String message,MsgStatus msgStatus);
 * 		不再调用老方法onReceiveMsg(String message);
 * @author junmu
 *
 */
public interface DataPushMsgListener extends TopCometMessageListener {

	/**
	 * 如果确认消息已经处理，不需要重发，则调用msgStatus.setNeedRollback(false)
	 * @param message
	 * @param msgStatus
	 */
	public void onReceiveMsg(String message,MessageStatus msgStatus);
}

package com.hifive.api.internal.stream.message;
/**
 * 
 * @author zhenzi
 * 2011-8-8 下午05:49:48
 */
public interface TopCometMessageListener {
	/**
	 * 服务端：当客户端的连接被接收后，服务端返回连接成功的消息<br/>
	 * 客户端：客户端不需要对此消息做任何处理
	 * @param message
	 */
	public void onConnectMsg(String message);
	/**
	 * 服务端：服务端会在每隔一段时间发送一个心跳包（如果一直有业务消息，则不会发送此心跳包）<br/>
	 * 客户端：不需要做任何处理，需要注意如果在一段时间内没有心跳包收到的话说明底层链路有问题了。
	 */
	public void onHeartBeat();
	/**
	 * 服务端：发送业务消息<br/>
	 * 客户端：接收到业务消息。<br/>
	 * 建议：建议客户端收到消息后，把消息放到一个消息池里面，异步处理消息，以免影响正常的消息接收
	 * @param message
	 */
	public void onReceiveMsg(String message);
	/**
	 * 服务端：服务端告知客户端丢弃消息的时间段。<br/>
	 * 客户端：当收到这个消息后，有两种解决办法。
	 *        1，异步调用Hifive.comet.discardinfo.get api查询哪些用户的消息丢弃了。
	 *           接下来通过增量api补完整这些丢弃的消息。
	 *        2，调用Hifive.topats.increment.messages.get api或者这段时间内appkey
	 *           所有开通消息服务的用户的所有消息。
	 * @param message
	 */
	public void onDiscardMsg(String message);
	/**
	 * 服务端：服务端在升级<br/>
	 * 客户端：sdk会在发布期间休眠一段时间，自动重连。<br/>
	 * 建议：由于服务端在发布的时候消息会丢弃，所以客户端在收到这个消息后，在连接正常之后补充消息，
	 *      补充的方式和onDidcardMsg的方式一样
	 * @param message 包括了服务端升级需要的时间
	 */
	public void onServerUpgrade(String message);
	/**
	 * 服务端：服务端负责不均衡，断开所有客户端连接<br/>
	 * 客户端：sdk会马上重连<br/>
	 * 建议：由于服务端在这个时候可能有消息会丢弃，所以客户端在收到这个消息后，在连接正常之后补充消息，
	 *     补充的方式和onDidcardMsg的方式一样
	 */
	public void onServerRehash();
	/**
	 * 服务端：消息量太大，isv接收太慢，服务端主动断开客户端<br/>
	 * 客户端：sdk不会重连，会停掉系统。<br/>
	 * 建议：1，首先把处理消息做成异步，让接收消息线程马上返回。<br/>
	 *       2，可以考虑使用多连接，参考url：http://open.Hifive.com/doc/detail.htm?id=818
	 */
	public void onServerKickOff();
	/**
	 *  //TODO 把客户端自己重连收到的踢消息和，因为isv在另外一个地方启动了相同的长连接导致的踢分开来，明确告诉isv就是因为你启动了相同的长连接踢
	 * 服务端：由于客户端使用相同的参数发起了另外一个请求，服务端把前一个连接断开<br/>
	 * 客户端：1，可能是sdk自动发起重连，打印一条信息。这种情况不需要做任何处理。<br/>
	 *        2，可能是相同的appkey在其他地方发起了连接请求，需要检查一下相同的appkey是否有在其他地方被使用。
	 */
	public void onClientKickOff();
	/**
	 * sdk的错误或者服务端增加了消息类型，但是sdk没有来得及升级。
	 * 建议升级一下sdk
	 * @param message
	 */
	public void onOtherMsg(String message);//其他由于某种原因不能被解析的信息
	/**
	 * 处理消息过程中可能由于某些原因导致抛出的异常
	 * @param e
	 */
	public void onException(Exception e);
}

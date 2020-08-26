package com.hifive.link.channel.tcp;


import com.hifive.link.Logger;
import com.hifive.link.channel.netty.NettyClientUpstreamHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

public class TcpClientUpstreamHandler extends NettyClientUpstreamHandler {
	public TcpClientUpstreamHandler(Logger logger, TcpClientChannel clientChannel) {
		super(logger, clientChannel);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		if (this.haveHandler())
			this.getHandler().onMessage(this.createContext(e.getMessage()));
	}
}
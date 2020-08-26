package com.hifive.link.remoting;

import com.hifive.link.LoggerFactory;
import com.hifive.link.channel.tcp.TcpServerChannel;
import org.jboss.netty.channel.ChannelPipeline;

public class NettyRemotingTcpServerChannel extends TcpServerChannel {

	public NettyRemotingTcpServerChannel(int port) {
		super(port);
	}
	
	public NettyRemotingTcpServerChannel(LoggerFactory factory, int port) {
		super(factory, port);
	}

	@Override
	protected void prepareCodec(ChannelPipeline pipeline) {
		pipeline.addLast("decoder", new NettyRemotingDecoder());
	}
}

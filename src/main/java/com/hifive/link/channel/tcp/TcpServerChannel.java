package com.hifive.link.channel.tcp;


import com.hifive.link.DefaultLoggerFactory;
import com.hifive.link.LoggerFactory;
import com.hifive.link.channel.netty.NettyServerChannel;
import org.jboss.netty.channel.ChannelPipeline;

public abstract class TcpServerChannel extends NettyServerChannel {

	public TcpServerChannel(int port) {
		this(DefaultLoggerFactory.getDefault(), port);
	}

	public TcpServerChannel(LoggerFactory factory, int port) {
		super(factory, port);
	}

	protected void preparePipeline(ChannelPipeline pipeline) {
		this.prepareCodec(pipeline);
		pipeline.addLast("handler", this.createHandler());
	}

	protected abstract void prepareCodec(ChannelPipeline pipeline);

	protected TcpServerUpstreamHandler createHandler() {
		return new TcpServerUpstreamHandler(
				this.loggerFactory,
				this.channelHandler,
				this.allChannels);
	}
}
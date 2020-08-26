package com.hifive.link.channel.netty;



import com.hifive.link.channel.ChannelSender;
import org.jboss.netty.channel.Channel;

import java.net.SocketAddress;

public abstract class NettyChannelSender implements ChannelSender {
	protected Channel channel;

	public NettyChannelSender(Channel channel) {
		this.channel = channel;
	}
	
	public Channel getChannel() {
		return this.channel;
	}
	
	@Override
	public SocketAddress getLocalAddress() {
		return this.channel.getLocalAddress();
	}
	
	@Override
	public SocketAddress getRemoteAddress() {
		return this.channel.getRemoteAddress();
	}
}
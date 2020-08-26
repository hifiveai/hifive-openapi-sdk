package com.hifive.link.channel.netty;

import com.hifive.link.channel.ClientChannel;
import org.jboss.netty.channel.Channel;

public interface NettyClientChannel extends ClientChannel {
	public void setChannel(Channel channel);
}

package com.hifive.link.remoting;



import com.hifive.link.LoggerFactory;
import com.hifive.link.channel.ChannelException;
import com.hifive.link.channel.ClientChannel;
import com.hifive.link.channel.ClientChannelSharedSelector;
import com.hifive.link.channel.tcp.TcpClient;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;

import java.net.URI;

public class NettyRemotingClientChannelSharedSelector extends ClientChannelSharedSelector {
	@Override
	protected ClientChannel connect(LoggerFactory loggerFactory, URI uri, int timeout) throws ChannelException {
		if (uri.getScheme().equalsIgnoreCase("tcp") || 
				uri.getScheme().equalsIgnoreCase("ssl")) {
			ChannelPipeline pipeline = Channels.pipeline();
			pipeline.addLast("decoder", new NettyRemotingDecoder());
			return TcpClient.connect(loggerFactory, uri, timeout, pipeline);
		}
		return super.connect(loggerFactory, uri, timeout);
	}
}

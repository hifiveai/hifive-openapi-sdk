package com.hifive.link.channel.tcp;



import com.hifive.link.Logger;
import com.hifive.link.LoggerFactory;
import com.hifive.link.channel.ChannelException;
import com.hifive.link.channel.ClientChannel;
import com.hifive.link.channel.ConnectingChannelHandler;
import com.hifive.link.channel.netty.NettyClient;
import org.jboss.netty.channel.ChannelPipeline;

import java.net.URI;

public class TcpClient extends NettyClient {
	public static ClientChannel connect(LoggerFactory loggerFactory,
										URI uri,
										int connectTimeoutMillis,
										ChannelPipeline pipeline)
			throws ChannelException {
		Logger logger = loggerFactory.create(String.format("TcpClientHandler-%s", uri));

		TcpClientChannel clientChannel = new TcpClientChannel();
		clientChannel.setUri(uri);

		ConnectingChannelHandler handler = new ConnectingChannelHandler();
		clientChannel.setChannelHandler(handler);

		TcpClientUpstreamHandler tcpHandler = new TcpClientUpstreamHandler(logger, clientChannel);
		// connect
		prepareAndConnect(logger, uri,
				pipeline, tcpHandler,
				uri.getScheme().equalsIgnoreCase("ssl"),
				connectTimeoutMillis);
		return clientChannel;
	}
}
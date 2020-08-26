package com.hifive.link.endpoint;


import com.hifive.link.ResetableTimer;
import com.hifive.link.channel.ChannelHandler;
import com.hifive.link.channel.ClientChannel;

import java.net.URI;

public class ClientChannelWrapper extends ChannelSenderWrapper implements ClientChannel {
	private ClientChannel clientChannel;

	public ClientChannelWrapper(ClientChannel clientChannel, int protocolVersion) {
		super(clientChannel, protocolVersion);
		this.clientChannel = clientChannel;
	}

	@Override
	public boolean isConnected() {
		return this.clientChannel.isConnected();
	}

	@Override
	public ChannelHandler getChannelHandler() {
		return this.clientChannel.getChannelHandler();
	}

	@Override
	public void setChannelHandler(ChannelHandler handler) {
		this.clientChannel.setChannelHandler(handler);
	}

	@Override
	public void setUri(URI uri) {
		this.clientChannel.setUri(uri);
	}

	@Override
	public URI getUri() {
		return this.clientChannel.getUri();
	}

	@Override
	public void setHeartbeatTimer(ResetableTimer timer) {
		this.clientChannel.setHeartbeatTimer(timer);
	}
}

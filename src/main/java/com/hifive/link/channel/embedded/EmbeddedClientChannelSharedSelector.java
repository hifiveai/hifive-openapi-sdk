package com.hifive.link.channel.embedded;



import com.hifive.link.LoggerFactory;
import com.hifive.link.channel.ChannelException;
import com.hifive.link.channel.ClientChannel;
import com.hifive.link.channel.ClientChannelSharedSelector;

import java.net.URI;

public class EmbeddedClientChannelSharedSelector extends ClientChannelSharedSelector {
	public EmbeddedClientChannelSharedSelector() {
		super();
	}

	public EmbeddedClientChannelSharedSelector(LoggerFactory loggerFactory) {
		super(loggerFactory);
	}

	protected ClientChannel connect(LoggerFactory loggerFactory, URI uri, int timeout) throws ChannelException {
		return uri.getScheme().equalsIgnoreCase("ws") ||
				uri.getScheme().equalsIgnoreCase("wss") ?
				EmbeddedWebSocketClient.connect(loggerFactory, uri, timeout) :
				super.connect(loggerFactory, uri, timeout);
	}
}

package com.hifive.link.remoting;



import com.hifive.link.LoggerFactory;
import com.hifive.link.channel.ChannelContext;
import com.hifive.link.channel.ServerChannelSender;

import java.util.List;
import java.util.Map.Entry;

public class SpringRemotingServerChannelHandler extends DefaultRemotingServerChannelHandler {
	private HandshakerBean handshaker;

	public SpringRemotingServerChannelHandler(LoggerFactory loggerFactory, HandshakerBean handshaker) {
		super(loggerFactory);
		this.handshaker = handshaker;
	}

	@SuppressWarnings("unchecked")
	public void onConnect(ChannelContext context) throws Exception {
		if (this.handshaker == null)
			return;
		this.handshaker.onHandshake(
				(List<Entry<String, String>>) context.getMessage(),
				new Context((ServerChannelSender) context.getSender()));
	}

	public class Context implements ChannelContextBean {
		private ServerChannelSender sender;

		public Context(ServerChannelSender sender) {
			this.sender = sender;
		}

		@Override
		public Object get(Object key) {
			return this.sender.getContext(key);
		}

		@Override
		public void set(Object key, Object value) {
			this.sender.setContext(key, value);
		}

	}
}
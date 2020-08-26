package com.hifive.link.channel;


import com.hifive.link.DefaultLoggerFactory;
import com.hifive.link.LoggerFactory;
import com.hifive.link.Pool;
import com.hifive.link.Text;
import com.hifive.link.channel.websocket.WebSocketClient;

import java.net.URI;
import java.util.Hashtable;

public class ClientChannelPooledSelector implements ClientChannelSelector {
	private final static int CONNECT_TIMEOUT = 5000;
	private Hashtable<String, Pool<ClientChannel>> channels;
	private LoggerFactory loggerFactory;
	private Object lockObject;

	public ClientChannelPooledSelector() {
		this(DefaultLoggerFactory.getDefault());
	}

	public ClientChannelPooledSelector(LoggerFactory loggerFactory) {
		this.loggerFactory = loggerFactory;
		this.channels = new Hashtable<String, Pool<ClientChannel>>();
		this.lockObject = new Object();
	}

	@Override
	public ClientChannel getChannel(final URI uri) throws ChannelException {
		String url = uri.toString();
		if (this.channels.get(url) == null) {
			synchronized (this.lockObject) {
				if (this.channels.get(url) == null) {
					this.channels.put(url, 
							this.createChannelPool(this.loggerFactory, uri, CONNECT_TIMEOUT));
				}
			}
		}

		try {
			return this.channels.get(url).chekOut();
		} catch (ChannelException e) {
			throw e;
		} catch (Throwable e) {
			throw new ChannelException("checkout error", e);
		}
	}

	@Override
	public void returnChannel(ClientChannel channel) {
		this.channels.get(channel.getUri().toString()).checkIn(channel);
	}

	protected ChannelPool createChannelPool(LoggerFactory loggerFactory, URI uri, int timeout) {
		return new ChannelPool(loggerFactory, uri, timeout);
	}

	public class ChannelPool extends Pool<ClientChannel> {
		protected LoggerFactory loggerFactory;
		protected URI uri;
		protected int timeout;

		public ChannelPool(LoggerFactory loggerFactory, URI uri, int timeout) {
			super(50, 10);
			this.loggerFactory = loggerFactory;
			this.uri = uri;
			this.timeout = timeout;
		}

		@Override
		public ClientChannel chekOut() throws Throwable {
			ClientChannel channel = super.chekOut();
			if (channel == null)
				throw new ChannelException(Text.RPC_POOL_BUSY);
			return channel;
		}

		@Override
		public ClientChannel create() throws ChannelException {
			return WebSocketClient.connect(this.loggerFactory, this.uri, this.timeout);
		}

		@Override
		public boolean validate(ClientChannel t) {
			return t.isConnected();
		}

	}
}

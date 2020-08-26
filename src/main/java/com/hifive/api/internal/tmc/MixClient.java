package com.hifive.api.internal.tmc;



import com.hifive.link.LinkException;
import com.hifive.link.Logger;
import com.hifive.link.LoggerFactory;
import com.hifive.link.channel.ChannelException;
import com.hifive.link.channel.ClientChannel;
import com.hifive.link.channel.ClientChannelSharedSelector;
import com.hifive.link.channel.embedded.EmbeddedClientChannelSharedSelector;
import com.hifive.link.endpoint.Endpoint;
import com.hifive.link.endpoint.EndpointChannelHandler;
import com.hifive.link.endpoint.EndpointProxy;
import com.hifive.link.endpoint.Identity;
import com.hifive.link.logging.LogUtil;
import com.hifive.link.endpoint.MessageHandler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 消息服务-推拉客户端
 */
class MixClient {

	protected Logger logger;
	protected Identity id;
	private Endpoint endpoint;
	private URI serverUri;
	private EndpointProxy server;
	private ClientChannelSharedSelector selector;

	private Timer reconnectTimer;
	private int reconnectInterval = 30000;

	public MixClient(Identity id) {
		// whatever, log first
		LoggerFactory loggerFactory = LogUtil.getLoggerFactory(this);
		this.logger = loggerFactory.create(this);

		// sharedpool with heartbeat 60s
		this.selector = new EmbeddedClientChannelSharedSelector(loggerFactory);
		this.selector.setHeartbeat(60000);

		// custom scheduler
		EndpointChannelHandler channelHandler = new EndpointChannelHandler(loggerFactory);
		// client do not need complex scheduler, only one io-thread
		// channelHandler.setScheduler(scheduler);

		this.endpoint = new Endpoint(loggerFactory, this.id = id);
		this.endpoint.setClientChannelSelector(this.selector);
		this.endpoint.setChannelHandler(channelHandler);
	}

	protected Identity getIdentity() {
		return this.id;
	}

	protected void setMessageHandler(MessageHandler handler) {
		this.endpoint.setMessageHandler(handler);
	}

	protected void connect(String uri) throws LinkException {
		try {
			this.connect(new URI(uri));
		} catch (URISyntaxException e) {
			this.logger.error(e);
		}
		this.startReconnect();
	}

	private void connect(URI uri) throws LinkException {
		this.server = this.endpoint.getEndpoint(new ServerIdentity(), uri, this.createConnectHeaders());
		this.serverUri = uri;
		this.logger.info("connected to tmc server: %s", this.serverUri);
	}

	protected void disconnect(String reason) {
		this.stopReconnect();

		if (!this.server.hasValidSender())
			return;

		try {
			ClientChannel channel = this.selector.getChannel(this.serverUri);
			if (channel != null) {
				channel.close(reason);
			}
		} catch (ChannelException e) {
			this.logger.error(e);
		}
	}

	protected final void send(Map<String, Object> message) throws ChannelException {
		this.server.send(message);
	}

	protected final void sendAndWait(Map<String, Object> message, int timeout) throws LinkException {
		this.server.sendAndWait(message, timeout);
	}

	protected Map<String, Object> createConnectHeaders() {
		return null;
	}

	protected boolean isOnline() {
		return this.server != null && this.server.hasValidSender();
	}

	private void startReconnect() {
		this.reconnectTimer = new Timer("tmc-reconnect", true);
		this.reconnectTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					if (!isOnline()) {
						logger.warn("reconnecting...");
						connect(serverUri);
					}
				} catch (Exception e) {
					logger.warn("reconnect error", e);
				}
			}
		}, this.reconnectInterval, this.reconnectInterval);
	}

	private void stopReconnect() {
		if (this.reconnectTimer != null) {
			this.reconnectTimer.cancel();
			this.reconnectTimer = null;
		}
	}

	public class ServerIdentity implements Identity {
		public Identity parse(Object data) throws LinkException {
			return null;
		}

		public void render(Object to) {
		}

		public boolean equals(Identity id) {
			return id instanceof ServerIdentity;
		}

		public String toString() {
			return id.toString();
		}
	}
}

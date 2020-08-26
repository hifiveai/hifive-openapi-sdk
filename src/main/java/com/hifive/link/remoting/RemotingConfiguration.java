package com.hifive.link.remoting;



import com.hifive.link.DefaultLoggerFactory;
import com.hifive.link.LoggerFactory;
import com.hifive.link.channel.ChannelHandler;
import com.hifive.link.channel.ClientChannelSelector;
import com.hifive.link.channel.ServerChannel;
import com.hifive.link.channel.websocket.WebSocketServerChannel;

import java.util.concurrent.ExecutorService;

// combined client/server remoting config helper
public class RemotingConfiguration {
	private static RemotingConfiguration configuration;

	public synchronized static RemotingConfiguration configure() {
		if (configuration == null)
			configuration = new RemotingConfiguration();
		return configuration;
	}

	private LoggerFactory loggerFactory;
	private DefaultRemotingServerChannelHandler defaultHandler;
	private SerializationFactory serializationFactory;

	public RemotingConfiguration() {
		this.loggerFactory(DefaultLoggerFactory.getDefault());
	}

	// should be set first
	public RemotingConfiguration loggerFactory(LoggerFactory loggerFactory) {
		this.loggerFactory = loggerFactory;
		RemotingService.setLoggerFactory(loggerFactory);
		return this;
	}

	public RemotingConfiguration clientChannelSelector(ClientChannelSelector selector) {
		RemotingService.setChannelSelector(selector);
		return this;
	}

	public RemotingConfiguration SerializationFactory(SerializationFactory serializationFactory) {
		this.serializationFactory = serializationFactory;
		RemotingService.setSerializationFactory(serializationFactory);
		return this;
	}

	// shold be set before bind()
	public RemotingConfiguration defaultServerChannelHandler(DefaultRemotingServerChannelHandler channelHandler) {
		this.defaultHandler = channelHandler;
		return this;
	}

	// bind to custom channel
	public RemotingConfiguration bind(ServerChannel channel) {
		channel.setChannelHandler((ChannelHandler) this.getChannelHandler());
		channel.run();
		return this;
	}

	public RemotingConfiguration websocket(int port) {
		return this.bind(new WebSocketServerChannel(this.loggerFactory, port, true));
	}

	public RemotingConfiguration tcp(int port) {
		return this;
	}

	public RemotingConfiguration http(int port) {
		return this;
	}

	public RemotingConfiguration addProcessor(
			String objectUri, MethodCallProcessor processor) {
		this.defaultHandler.addProcessor(objectUri, processor);
		return this;
	}

	public RemotingConfiguration businessThreadPool(ExecutorService threadPool) {
		this.defaultHandler.setThreadPool(threadPool);
		return this;
	}

	private synchronized DefaultRemotingServerChannelHandler getChannelHandler() {
		if (this.defaultHandler == null)
			this.defaultHandler = new DefaultRemotingServerChannelHandler(this.loggerFactory);
		if (this.serializationFactory != null)
			this.defaultHandler.setSerializationFactory(this.serializationFactory);
		return this.defaultHandler;
	}
}

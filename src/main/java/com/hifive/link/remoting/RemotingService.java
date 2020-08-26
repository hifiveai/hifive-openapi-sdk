package com.hifive.link.remoting;


import com.hifive.link.DefaultLoggerFactory;
import com.hifive.link.LoggerFactory;
import com.hifive.link.channel.ClientChannelSelector;
import com.hifive.link.channel.ClientChannelSharedSelector;

import java.net.URI;
import java.util.concurrent.atomic.AtomicInteger;

public class RemotingService {
	private static LoggerFactory loggerFactory = DefaultLoggerFactory.getDefault();
	private static ClientChannelSelector channelSelector;
	private static RemotingClientChannelHandler channelHandler;
	private static SerializationFactory serializationFactory;

	protected static void setLoggerFactory(LoggerFactory loggerFactory) {
		RemotingService.loggerFactory = loggerFactory;
	}

	protected static void setChannelSelector(ClientChannelSelector selector) {
		channelSelector = selector;
	}

	protected static void setSerializationFactory(SerializationFactory serializationFactory) {
		RemotingService.serializationFactory = serializationFactory;
	}

	public static Object connect(URI remoteUri, Class<?> interfaceClass) {
		return connect(remoteUri, interfaceClass, null);
	}

	public static Object connect(URI remoteUri, Class<?> interfaceClass, String serializationFormat) {
		DynamicProxy proxy = connect(remoteUri);
		proxy.setSerializationFormat(serializationFormat);
		return proxy.create(interfaceClass, remoteUri);
	}

	public static DynamicProxy connect(URI remoteUri) {
		return new DynamicProxy(remoteUri, getChannelSelector(), getChannelHandler());
	}

	private synchronized static RemotingClientChannelHandler getChannelHandler() {
		if (channelHandler == null)
			channelHandler = new RemotingClientChannelHandler(loggerFactory, new AtomicInteger(0));
		if (serializationFactory != null)
			channelHandler.setSerializationFactory(serializationFactory);
		return channelHandler;
	}

	private synchronized static ClientChannelSelector getChannelSelector() {
		if (channelSelector == null)
			channelSelector = new ClientChannelSharedSelector(loggerFactory);
		return channelSelector;
	}
}
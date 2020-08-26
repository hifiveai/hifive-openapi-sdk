package com.hifive.link.remoting;


import com.hifive.link.DefaultLoggerFactory;
import com.hifive.link.LoggerFactory;

import java.net.URI;
import java.util.HashMap;

public class DefaultRemotingServerChannelHandler extends RemotingServerChannelHandler {
	private HashMap<String, MethodCallProcessor> services;

	public DefaultRemotingServerChannelHandler() {
		this(DefaultLoggerFactory.getDefault());
	}

	public DefaultRemotingServerChannelHandler(LoggerFactory loggerFactory) {
		super(loggerFactory);
		this.services = new HashMap<String, MethodCallProcessor>();
	}

	public final void addProcessor(String objectUri, MethodCallProcessor processor) {
		this.services.put("/" + objectUri.toLowerCase(), processor);
	}

	@Override
	public final MethodReturn onMethodCall(MethodCall methodCall, MethodCallContext callContext) throws Throwable {
		// dispatch methodCall to service
		String objectUri = new URI(methodCall.Uri).getRawPath().trim();
		MethodCallProcessor processor = this.services.get(objectUri);
		if (processor == null)
			throw new NullPointerException(String.format(
					"processor not found for objectUri: %s", objectUri));
		return processor.process(methodCall, callContext);
	}
}

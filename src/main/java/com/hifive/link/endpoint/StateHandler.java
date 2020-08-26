package com.hifive.link.endpoint;


import com.hifive.link.LinkException;

public interface StateHandler {
	public void onConnect(EndpointProxy endpoint, ChannelSenderWrapper sender) throws LinkException;
}
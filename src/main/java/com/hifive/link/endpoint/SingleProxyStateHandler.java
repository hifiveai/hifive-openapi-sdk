package com.hifive.link.endpoint;


import com.hifive.link.LinkException;
import com.hifive.link.Text;
import com.hifive.link.channel.ServerChannelSender;

public class SingleProxyStateHandler implements StateHandler {
	@Override
	public void onConnect(EndpointProxy endpoint, ChannelSenderWrapper sender) throws LinkException {
		if (!(sender.getChannelSender() instanceof ServerChannelSender))
			return;
		// FIXME hack here, maybe not alwasy ServerChannelSender
		ServerChannelSender serverSender = (ServerChannelSender) sender.getChannelSender();
		if (serverSender.getContext("__endpoint") != null)
			throw new LinkException(Text.E_SINGLE_ALLOW);
		serverSender.setContext("__endpoint", endpoint);
	}

}

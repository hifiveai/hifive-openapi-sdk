package com.hifive.link.channel;


import com.hifive.link.ResetableTimer;
import java.net.URI;

public interface ClientChannel extends ChannelSender {
	public boolean isConnected();
	public ChannelHandler getChannelHandler();
	public void setChannelHandler(ChannelHandler handler);
	public void setUri(URI uri);
	public URI getUri();
	public void setHeartbeatTimer(ResetableTimer timer);
}
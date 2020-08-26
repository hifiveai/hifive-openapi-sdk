package com.hifive.link.channel.tcp;



import com.hifive.link.ResetableTimer;
import com.hifive.link.Text;
import com.hifive.link.channel.ChannelException;
import com.hifive.link.channel.ChannelHandler;
import com.hifive.link.channel.ClientChannel;
import com.hifive.link.channel.netty.NettyClientChannel;
import org.jboss.netty.channel.Channel;

import java.net.URI;
import java.nio.ByteBuffer;

public class TcpClientChannel extends TcpChannelSender implements ClientChannel, NettyClientChannel {
	private URI uri;
	private ChannelHandler channelHandler;
	private ResetableTimer timer;

	public TcpClientChannel() {
		super(null);
	}
	
	public TcpClientChannel(Channel channel) {
		super(channel);
	}

	@Override
	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@Override
	public void setUri(URI uri) {
		this.uri = uri;
	}

	@Override
	public URI getUri() {
		return this.uri;
	}

	public ChannelHandler getChannelHandler() {
		this.delayPing();
		return this.channelHandler;
	}

	@Override
	public void setChannelHandler(ChannelHandler handler) {
		this.channelHandler = handler;
	}

	@Override
	public boolean isConnected() {
		return this.channel.isConnected();
	}

	@Override
	public void setHeartbeatTimer(ResetableTimer timer) {
		this.timer = timer;
		this.timer.setTask(new Runnable() {
			@Override
			public void run() {
				// if (isConnected())
				// TODO:easy heartbeat frame
				// channel.write();
			}
		});
		this.timer.start();
	}

	@Override
	public void send(ByteBuffer dataBuffer, SendHandler sendHandler) throws ChannelException {
		this.checkChannel();
		super.send(dataBuffer, sendHandler);
	}

	@Override
	public void send(byte[] data, int offset, int length) throws ChannelException {
		this.checkChannel();
		super.send(data, offset, length);
	}

	private void checkChannel() throws ChannelException {
		// prevent unknown exception after connected and get channel
		// channel.write is async default
		if (!this.channel.isConnected()) {
			if (this.timer != null)
				try {
					this.timer.stop();
				} catch (InterruptedException e) {
				}
			throw new ChannelException(Text.CHANNEL_CLOSED);
		}
		this.delayPing();
	}

	private void delayPing() {
		if (this.timer != null)
			this.timer.delay();
	}
}

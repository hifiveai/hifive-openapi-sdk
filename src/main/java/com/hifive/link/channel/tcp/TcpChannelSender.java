package com.hifive.link.channel.tcp;

import com.hifive.link.Text;
import com.hifive.link.channel.ChannelException;
import com.hifive.link.channel.netty.NettyChannelSender;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;

import java.nio.ByteBuffer;


public abstract class TcpChannelSender extends NettyChannelSender {
	public TcpChannelSender(Channel channel) {
		super(channel);
	}

	@Override
	public void send(byte[] data, int offset, int length) throws ChannelException {
		this.send(ChannelBuffers.wrappedBuffer(data, offset, length), null);
	}

	@Override
	public void send(ByteBuffer dataBuffer, SendHandler sendHandler) throws ChannelException {
		this.send(ChannelBuffers.wrappedBuffer(dataBuffer), sendHandler);
	}

	@Override
	public boolean sendSync(ByteBuffer dataBuffer, SendHandler sendHandler, int timeoutMilliseconds) throws ChannelException {
		throw new ChannelException(Text.DO_NOT_SUPPORT);
	}

	@Override
	public void close(String reason) {
		channel.write(reason).addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				future.getChannel().close();
			}
		});
	}

	private void send(Object message, final SendHandler sendHandler) throws ChannelException {
		if (sendHandler == null)
			this.channel.write(message);
		else
			this.channel.write(message).addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if (sendHandler != null)
						sendHandler.onSendComplete(future.isSuccess());
				}
			});
	}
}
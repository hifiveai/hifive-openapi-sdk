package com.hifive.link.channel.websocket;

import com.hifive.link.Text;
import com.hifive.link.channel.netty.NettyChannelSender;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class WebSocketChannelSender extends NettyChannelSender {
	public WebSocketChannelSender(Channel channel) {
		super(channel);
	}

	@Override
	public void send(byte[] data, int offset, int length) throws ChannelException {
		ChannelBuffer buffer = ChannelBuffers.wrappedBuffer(data, offset, length);
		BinaryWebSocketFrame frame = new BinaryWebSocketFrame(buffer);
		this.send(frame, null, 0);
	}

	@Override
	public void send(ByteBuffer dataBuffer, SendHandler sendHandler) throws ChannelException {
		this.sendSync(dataBuffer, sendHandler, 0);
	}

	@Override
	public boolean sendSync(ByteBuffer dataBuffer, SendHandler sendHandler, int timeoutMilliseconds) throws ChannelException {
		ChannelBuffer buffer = ChannelBuffers.wrappedBuffer(dataBuffer);
		BinaryWebSocketFrame frame = new BinaryWebSocketFrame(buffer);
		return this.send(frame, sendHandler, timeoutMilliseconds);
	}

	@Override
	public void close(String reason) {
		this.channel.write(new CloseWebSocketFrame(1000, reason));
	}

	private boolean send(WebSocketFrame frame, final SendHandler sendHandler, int timeout) throws ChannelException {
		// do not support fragmentation
		frame.setFinalFragment(true);

		// weather sendSync enabled
		final CountDownLatch latch = timeout > 0 ? new CountDownLatch(1) : null;

		this.channel.write(frame).addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if (latch != null)
					latch.countDown();
				else if (sendHandler != null)
					sendHandler.onSendComplete(future.isSuccess());
			}
		});

		if (latch == null)
			return true;

		boolean success = false;
		try {
			return success = latch.await(timeout, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			throw new ChannelException(Text.WS_SEND_SYNC_ERROR, e);
		} finally {
			if (sendHandler != null)
				sendHandler.onSendComplete(success);
		}
	}

}

package com.hifive.link.remoting;



import com.hifive.link.BufferManager;
import com.hifive.link.Logger;
import com.hifive.link.LoggerFactory;
import com.hifive.link.Text;
import com.hifive.link.channel.ChannelContext;
import com.hifive.link.remoting.protocol.RemotingTcpProtocolHandle;
import com.hifive.link.remoting.protocol.RemotingTransportHeader;
import com.hifive.protocol.tcp.TcpContentDelimiter;
import com.hifive.protocol.tcp.TcpOperations;
import com.hifive.protocol.tcp.TcpTransportHeader;
import org.jboss.netty.channel.SimpleChannelHandler;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RemotingClientChannelHandler extends SimpleChannelHandler {
	private Logger logger;
	private AtomicInteger flagAtomic;
	private Map<Integer, RemotingCallback> callbacks;
	private SerializationFactory serializationFactory;

	public RemotingClientChannelHandler(LoggerFactory loggerFactory, AtomicInteger flagAtomic) {
		this.logger = loggerFactory.create(this);
		this.flagAtomic = flagAtomic;
		this.callbacks = new ConcurrentHashMap<Integer, RemotingCallback>();
		this.serializationFactory = new DefaultSerializationFactory();
	}

	public void setSerializationFactory(SerializationFactory serializationFactory) {
		this.serializationFactory = serializationFactory;
	}

	public ByteBuffer pending(RemotingCallback handler,
			short operation,
			HashMap<String, Object> transportHeaders,
			MethodCall methodCall)
			throws FormatterException {
		byte[] data = this.serializationFactory.
				get(handler.serializationFormat).
				serializeMethodCall(methodCall);
		return this.pending(handler,
				operation,
				transportHeaders,
				data,
				0,
				data.length);
	}

	// act as formatter sink
	public ByteBuffer pending(RemotingCallback handler,
			short operation,
			HashMap<String, Object> transportHeaders,
			byte[] data,
			int dataOffset,
			int dataLength) {
		Integer flag = this.flagAtomic.incrementAndGet();

		ByteBuffer requestBuffer = BufferManager.getBuffer();
		RemotingTcpProtocolHandle handle = new RemotingTcpProtocolHandle(requestBuffer);
		handle.WritePreamble();
		handle.WriteMajorVersion();
		handle.WriteMinorVersion();
		handle.WriteOperation(TcpOperations.Request);
		handle.WriteContentDelimiter(TcpContentDelimiter.ContentLength);
		handle.WriteContentLength(data.length);
		transportHeaders.put(RemotingTransportHeader.Flag, flag);
		transportHeaders.put(RemotingTransportHeader.Format, handler.serializationFormat);
		handle.WriteTransportHeaders(transportHeaders);
		handle.WriteContent(data);

		handler.flag = flag;
		this.callbacks.put(handler.flag, handler);
		if (this.logger.isDebugEnabled())
			this.logger.debug(Text.RPC_PENDING_CALL, flag);

		return requestBuffer;
	}

	public void cancel(RemotingCallback callback) {
		this.callbacks.remove(callback.flag);
	}

	public void onMessage(ChannelContext context) {
		Object msg = context.getMessage();
		RemotingTcpProtocolHandle protocol = msg instanceof ByteBuffer ?
				new RemotingTcpProtocolHandle((ByteBuffer) msg) :
				(RemotingTcpProtocolHandle) msg;
		protocol.ReadPreamble();
		protocol.ReadMajorVersion();
		protocol.ReadMinorVersion();

		short operation = protocol.ReadOperation();
		if (operation != TcpOperations.Reply)
			return;

		protocol.ReadContentDelimiter();
		protocol.ReadContentLength();

		HashMap<String, Object> transportHeaders = null;
		try {
			transportHeaders = protocol.ReadTransportHeaders();
		} catch (Exception e) {
			this.logger.error(e);
		}
		Object flag;
		if (transportHeaders == null ||
				(flag = transportHeaders.get(RemotingTransportHeader.Flag)) == null)
			return;

		if (this.logger.isDebugEnabled())
			this.logger.debug(Text.RPC_GET_RETURN, flag);

		RemotingCallback callback = this.callbacks.remove(flag);
		if (callback == null)
			return;

		Object statusCode = transportHeaders.get(TcpTransportHeader.StatusCode);
		Object statusPhrase = transportHeaders.get(TcpTransportHeader.StatusPhrase);
		if (statusCode != null &&
				Integer.parseInt(statusCode.toString()) > 0) {
			callback.onException(new Exception(String.format(
					Text.RPC_RETURN_ERROR, statusCode, statusPhrase)));
			return;
		}

		MethodReturn methodReturn = null;
		try {
			methodReturn = this.serializationFactory.
					get(callback.serializationFormat).
					deserializeMethodReturn(protocol.ReadContent(), callback.returnType);
		} catch (FormatterException e) {
			callback.onException(e);
			return;
		}

		try {
			callback.onMethodReturn(methodReturn);
		} catch (Exception e) {
			this.logger.error(e);
		}
	}

	public void onClosed(String reason) {
		RemotingException error = new RemotingException(Text.RPC_CHANNEL_BROKEN);
		// all is fail!
		for (Entry<Integer, RemotingCallback> i : this.callbacks.entrySet()) {
			try {
				i.getValue().onException(error);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.callbacks = new HashMap<Integer, RemotingCallback>();
	}
}

package com.hifive.link.channel.embedded;



import com.hifive.link.Logger;
import com.hifive.link.LoggerFactory;
import com.hifive.link.Text;
import com.hifive.link.channel.ClientChannel;
import com.hifive.link.channel.websocket.WebSocketClientHelper;
import com.hifive.link.embedded.websocket.WebSocket;
import com.hifive.link.embedded.websocket.WebSockets;
import com.hifive.link.embedded.websocket.impl.WebSocketBase;
import com.hifive.link.embedded.websocket.impl.WebSocketImpl;
import org.jboss.netty.channel.ChannelException;

import java.net.URI;
import java.util.Map;

public class EmbeddedWebSocketClient {
	private final static String[] subprotocol = new String[0];

	public static ClientChannel connect(LoggerFactory loggerFactory, URI uri, int timeout) throws ChannelException {
		resetSettings();
		Logger logger = loggerFactory.create(String.format("EmbeddedWebSocketHandler-%s", uri));
		EmbeddedWebSocketClientChannel clientChannel = new EmbeddedWebSocketClientChannel();
		clientChannel.setUri(uri);
		try {
			WebSocket socket = WebSockets.create(
					uri.toASCIIString(),
					new EmbeddedWebSocketHandler(logger, clientChannel),
					subprotocol);
			((WebSocketBase) socket).setPacketDumpMode(0);
			socket.setBlockingMode(false);
			// socket's timeunit is second
			socket.setConnectionTimeout(timeout / 1000);
			// also use default headers setting
			Map<String, String> headers = WebSocketClientHelper.getHeaders(uri);
			if (headers != null) {
				for (String h : headers.keySet())
					((WebSocketImpl) socket).getRequestHeader().addHeader(h, headers.get(h));
			}
			// startSocket.connect(); is sync
			// https://github.com/wsky/top-push-client/issues/20
			socket.connect();
		} catch (Exception e) {
			throw new ChannelException(Text.CONNECT_ERROR, e);
		}

		if (clientChannel.error != null)
			throw new ChannelException(Text.WS_HANDSHAKE_ERROR, clientChannel.error);

		return clientChannel;
	}

	// websocket-client settings
	private static void resetSettings() {
		// read buffer, for reading inbound bytes
		// 32767
		// System.setProperty("websocket.bufferSize", "0x7FFF");

		// outgoing queue size
		System.setProperty("websocket.upstreamQueueSize", "10000");

		// dump for test
		System.setProperty("websocket.packatdump", "0");
	}
}

package com.hifive.link.endpoint.protocol;


import com.hifive.link.endpoint.MessageIO;

import java.nio.ByteBuffer;

public class DefaultMessageDecoderFactory implements MessageDecoderFactory {
	private MessageDecoder01 decoder01 = new MessageDecoder01();
	private MessageDecoder02 decoder02 = new MessageDecoder02();

	@Override
	public MessageIO.MessageDecoder get(ByteBuffer buffer) {
		int version = buffer.get();
		if (version == 1)
			return this.decoder01;
		return this.decoder02;
	}
}

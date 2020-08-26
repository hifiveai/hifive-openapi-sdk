package com.hifive.link.endpoint.protocol;

import com.hifive.link.endpoint.MessageIO;

import java.nio.ByteBuffer;

public interface MessageDecoderFactory {
	public MessageIO.MessageDecoder get(ByteBuffer buffer);
}
package com.hifive.link.endpoint.protocol;


import com.hifive.link.endpoint.Message;
import com.hifive.link.endpoint.MessageIO;

public class DefaultMessageEncoderFactory implements MessageEncoderFactory {
	private MessageEncoder01 encoder01 = new MessageEncoder01();
	private MessageEncoder02 encoder02 = new MessageEncoder02();

	@Override
	public MessageIO.MessageEncoder get(Message message) {
		if (message.protocolVersion == 1)
			return this.encoder01;
		return this.encoder02;
	}
}
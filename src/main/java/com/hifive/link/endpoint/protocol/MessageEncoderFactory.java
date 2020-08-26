package com.hifive.link.endpoint.protocol;

import com.hifive.link.endpoint.Message;
import com.hifive.link.endpoint.MessageIO;

public interface MessageEncoderFactory {
	public MessageIO.MessageEncoder get(Message message);
}
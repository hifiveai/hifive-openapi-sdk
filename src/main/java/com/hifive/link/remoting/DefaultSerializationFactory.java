package com.hifive.link.remoting;

public class DefaultSerializationFactory implements SerializationFactory {
	private Serializer serializer = new DefaultSerializer();

	public Serializer get(Object format) {
		return this.serializer;
	}
}

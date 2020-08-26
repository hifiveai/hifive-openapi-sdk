package com.hifive.link.remoting;

public interface SerializationFactory {
	public Serializer get(Object format);
}
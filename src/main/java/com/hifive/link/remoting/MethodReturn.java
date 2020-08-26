package com.hifive.link.remoting;

@SuppressWarnings("serial")
public class MethodReturn implements Message {
	public Object ReturnValue;
	public Throwable Exception;
}

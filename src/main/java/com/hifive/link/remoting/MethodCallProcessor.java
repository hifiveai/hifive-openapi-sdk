package com.hifive.link.remoting;

public interface MethodCallProcessor {
	public MethodReturn process(MethodCall methodCall, MethodCallContext callContext) throws Throwable;
}
package com.hifive.api.internal.stream.connect;

/**
 * 连接的生命周期的监听器
 * @author zhenzi
 * 2011-8-8 下午05:47:59
 */
public interface ConnectionLifeCycleListener {
	/**
	 * 在每次发起连接请求之前，允许应用程序做一些事情。
	 */
	public void onBeforeConnect();
	/**
	 * 系统中出现的任何与连接相关的异常时调用此方法，这里包括连接过程中发生的异常，和系统处理过程中发生的异常</br>
	 * 1，连接异常,签名错误，丢失参数等异常时，抛出这个异常后，表名没有正确连接到服务端</br>
	 * 2，系统处理过程中可能会抛出一些异常</br>
	 * isv可以关注此异常信息，用于诊断系统运行状况
	 * 
	 * @param e 包含了系统运行过程中错误的信息。
	 */
	public void onException(Throwable e);
	/**
	 * 当系统在30分钟内超过10次timeout了,则调用这个方法，sdk会继续重练
	 * 但是强烈建议isv监控此方法，当频繁出现readtimeout的时候，说明
	 * 网络环境可能不是很稳定，需要人工介入检查一下是不是网络有问题。
	 * 
	 */
	public void onMaxReadTimeoutException();
}

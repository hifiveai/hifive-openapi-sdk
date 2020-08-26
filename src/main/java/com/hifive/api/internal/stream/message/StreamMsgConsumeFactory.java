package com.hifive.api.internal.stream.message;


import com.hifive.api.internal.util.NamedThreadFactory;

import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

/**
 * 消息处理工厂
 * @author zhenzi
 * 2011-8-9 下午03:06:53
 */
public class StreamMsgConsumeFactory {
	private int minThreads;
	private int maxThreads;
	private int queueSize;
	private ThreadPoolExecutor threadPool;
	//标示是否关闭
	private boolean isShutDown = false;
	public StreamMsgConsumeFactory(int minThreads,int maxThreads,int queueSize,RejectedExecutionHandler rejectHandler){
		if(minThreads <= 0 || maxThreads <= 0 || queueSize <= 0){
			throw new RuntimeException("minThread,maxThread and queueSize must large than 0");
		}
		this.minThreads = minThreads;
		this.maxThreads = maxThreads;
		this.queueSize = queueSize;
		
		threadPool = new ThreadPoolExecutor(this.minThreads, this.maxThreads,
    			60, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(this.queueSize),
				new NamedThreadFactory("pool-msg-consume",true),rejectHandler == null ? new AbortPolicy() : rejectHandler);
	}
	public void consume(Runnable task)throws RejectedExecutionException,NullPointerException {
		if(!isShutDown){
			threadPool.execute(task);
		}
	}
	public void shutdown(){
		isShutDown = true;
		try{
			threadPool.shutdown();
		}catch(Exception e){};//关闭的过程中抛出的异常忽略
	}
}

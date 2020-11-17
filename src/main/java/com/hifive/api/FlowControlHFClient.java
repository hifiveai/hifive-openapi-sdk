package com.hifive.api;

import java.util.concurrent.Semaphore;

/**
 * 带流控的调用客户端。
 * 
 * @author fengsheng
 * @since 1.0, Sep 4, 2012
 */
public class FlowControlHFClient extends DefaultHFClient {

	private Semaphore semaphore = new Semaphore(Runtime.getRuntime().availableProcessors() * 10);

	public FlowControlHFClient(String serverUrl, String appKey, String appSecret) {
		super(serverUrl, appKey, appSecret);
	}

	public FlowControlHFClient(String serverUrl, String appKey, String appSecret, String format) {
		super(serverUrl, appKey, appSecret, format);
	}

	public FlowControlHFClient(String serverUrl, String appKey, String appSecret, String format, int connectTimeout, int readTimeout) {
		super(serverUrl, appKey, appSecret, format, connectTimeout, readTimeout);
	}

	public FlowControlHFClient(String serverUrl, String appKey, String appSecret, String format, int connectTimeout, int readTimeout, String signMethod) {
		super(serverUrl, appKey, appSecret, format, connectTimeout, readTimeout, signMethod);
	}

	public void setConcurrentNum(int concurrentNum) {
		this.semaphore = new Semaphore(concurrentNum);
	}

	public <T extends HifiveResponse> T execute(HFRequest<T> request) throws ApiException {
		return this.execute(request, null);
	}

	public <T extends HifiveResponse> T execute(final HFRequest<T> request, final String session) throws ApiException {
		try {
			this.semaphore.acquire();
			try {
				return super.execute(request, session);
			} finally {
				this.semaphore.release();
			}
		} catch (InterruptedException e) {
			throw new ApiException(e);
		}
	}

}

package com.hifive.api;


/**
 * HIFIVE客户端。
 * 
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public interface HFClient {
	
	/**
	 * 执行TOP公开API请求。
	 * @param <T>
	 * @param request 具体的TOP请求
	 * @return
	 * @throws ApiException
	 */
	public <T extends HifiveResponse> T execute(HFRequest<T> request) throws ApiException ;
	/**
	 * 执行TOP隐私API请求。
	 * @param <T>
	 * @param request 具体的TOP请求
	 * @param session 用户会话授权码
	 * @return
	 * @throws ApiException
	 */
	public <T extends HifiveResponse> T execute(HFRequest<T> request, String session) throws ApiException ;
}

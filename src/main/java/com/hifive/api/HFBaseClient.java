/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.hifive.api;


/**
 * @author runzhi
 */
public interface HFBaseClient {

    /**
     * @param <T> 请求泛参
     * @param request 请求结果
     * @return 返回结果
     * @throws HFApiException 请求异常
     */
    public <T extends HFResponse> T execute(HFRequest<T> request) throws HFApiException;

    /**
     * @param <T> 请求泛参
     * @param authToken 根据授权token执行
     * @param request 请求结果
     * @return 返回结果
     * @throws HFApiException 请求异常
     */
    public <T extends HFResponse> T execute(HFRequest<T> request, String authToken)
            throws HFApiException;

    /**
     * @param <T> 请求泛参
     * @param request 请求结果
     * @param authToken 请求token
     * @param version 版本
     * @return 返回结果
     * @throws HFApiException 请求异常
     */
    public <T extends HFResponse> T execute(HFRequest<T> request, String authToken,
                                            String version) throws HFApiException;
}

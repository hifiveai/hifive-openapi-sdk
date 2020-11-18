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
     * @param <T>
     * @param request
     * @return
     * @throws HFApiException
     */
    public <T extends HFResponse> T execute(HFRequest<T> request) throws HFApiException;

    /**
     * @param <T>
     * @param request
     * @return
     * @throws HFApiException
     */
    public <T extends HFResponse> T execute(HFRequest<T> request, String authToken)
            throws HFApiException;

    /**
     * @param <T>
     * @param request
     * @param authToken
     * @param version
     * @return
     * @throws HFApiException
     */
    public <T extends HFResponse> T execute(HFRequest<T> request, String authToken,
                                            String version) throws HFApiException;
}

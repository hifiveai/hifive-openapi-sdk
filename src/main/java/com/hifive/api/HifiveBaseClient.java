/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.hifive.api;


/**
 * 
 * @author runzhi
 */
public interface HifiveBaseClient {

    /**
     * 
     * 
     * @param <T>
     * @param request
     * @return
     * @throws HifiveApiException
     */
    public <T extends HifiveResponse> T execute(HifiveRequest<T> request) throws HifiveApiException;

    /**
     * 
     * 
     * @param <T>
     * @param request
     * @return
     * @throws HifiveApiException
     */
    public <T extends HifiveResponse> T execute(HifiveRequest<T> request, String authToken)
                                                                                             throws HifiveApiException;

    /**
     * 
     * 
     * @param <T>
     * @param request
     * @param authToken
     * @param version
     * @return
     * @throws HifiveApiException
     */
    public <T extends HifiveResponse> T execute(HifiveRequest<T> request, String authToken,
                                                String version) throws HifiveApiException;
}

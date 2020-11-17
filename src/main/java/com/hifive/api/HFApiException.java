/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.hifive.api;


/**
 * 
 * @author runzhi
 */
public class HFApiException extends ApiException {

    /**  */
    private static final long serialVersionUID = -3681931870669185915L;

    public HFApiException() {
        super();
    }

    public HFApiException(Throwable cause) {
        super(cause);
    }

    public HFApiException(String message, Throwable cause) {
        super(message, cause);
    }
}

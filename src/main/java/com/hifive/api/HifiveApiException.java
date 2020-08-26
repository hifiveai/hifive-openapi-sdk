/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.hifive.api;


/**
 * 
 * @author runzhi
 */
public class HifiveApiException extends ApiException {

    /**  */
    private static final long serialVersionUID = -3681931870669185915L;

    public HifiveApiException() {
        super();
    }

    public HifiveApiException(Throwable cause) {
        super(cause);
    }

    public HifiveApiException(String message, Throwable cause) {
        super(message, cause);
    }
}

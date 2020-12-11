package com.hifive.net;

/**
 * Created by huangyong on 2017/7/17.
 */

public class HttpException extends Exception {

    private static final long serialVersionUID = 1L;
    public int code;
    public String action;
    public String message;

    public HttpException() {
        super();
    }

    public HttpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(Throwable cause) {
        super(cause);
    }

    public HttpException(int code, String action, String message) {
        super();
        this.code = code;
        this.action = action;
        this.message = message;
    }

}


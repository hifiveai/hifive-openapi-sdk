package com.hifive.api;

/**
 * 基于REST的TOP客户端。
 *
 * @author yong.huang
 * @since 1.0, Sep 13, 2009
 */
public class ApiRuleException extends ApiException {

    private static final long serialVersionUID = -7787145910600194272L;

    public ApiRuleException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

}

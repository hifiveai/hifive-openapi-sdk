package com.hifive.api.request;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yong.huang
 */
public class HifiveBaseRequest  {

    private String clientId;

    private String version ="V4.0.1";

    private String nonce;

    private String authorization = "HF3-HMAC-SHA1";

    private String method;

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getClientId() {
        return clientId;
    }

    public String getVersion() {
        return version;
    }

    public String getNonce() {
        return StringUtils.isEmpty(nonce) ? RandomStringUtils.randomAlphabetic(32) : nonce;
    }

    public String getAuthorization() {
        return authorization;
    }
}
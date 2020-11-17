package com.hifive.api.request;

import com.hifive.api.HFRequest;
import com.hifive.api.HifiveResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yong.huang
 */
public abstract class HFBaseRequest<T extends HifiveResponse> implements HFRequest<T> {

    private String clientId;

    private String version ="V4.0.1";

    private String nonce;

    private String authorization = "HF3-HMAC-SHA1";

    private String method;

    private String token;

    private Long timestamp;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}

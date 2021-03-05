package com.hifive.model;


import java.io.Serializable;
import java.util.Map;

/**
 * HttpRespInfo
 *
 * @author Jerry.X.He
 */
public class HttpRespInfo implements Serializable {

    /**
     * 协议类型
     */
    private String protocol;
    /**
     * 状态码
     */
    private String statusCode;
    /**
     * reasonPhrase
     */
    private String reasonPhrase;
    /**
     * 相关的响应头
     */
    private Map<String, String> headers;
    /**
     * 响应体
     */
    private String content;

    public HttpRespInfo() {
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

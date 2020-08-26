package com.hifive.api.internal.util;

import java.util.HashMap;
import java.util.Map;

public class RequestParametersHolder {

    private String method;

    private HifiveHashMap applicationHeaders;

    private HifiveHashMap protocalMustParams;
    private HifiveHashMap protocalOptParams;
    private HifiveHashMap applicationParams;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public HifiveHashMap getProtocalMustParams() {
        return protocalMustParams;
    }

    public void setProtocalMustParams(HifiveHashMap protocalMustParams) {
        this.protocalMustParams = protocalMustParams;
    }

    public HifiveHashMap getProtocalOptParams() {
        return protocalOptParams;
    }

    public void setProtocalOptParams(HifiveHashMap protocalOptParams) {
        this.protocalOptParams = protocalOptParams;
    }

    public HifiveHashMap getApplicationParams() {
        return applicationParams;
    }

    public void setApplicationParams(HifiveHashMap applicationParams) {
        this.applicationParams = applicationParams;
    }

    public HifiveHashMap getApplicationHeaders() {
        return applicationHeaders;
    }

    public void setApplicationHeaders(HifiveHashMap applicationHeaders) {
        this.applicationHeaders = applicationHeaders;
    }

    public Map<String, String> getAllParams() {
        Map<String, String> params = new HashMap<String, String>();
        if (protocalMustParams != null && !protocalMustParams.isEmpty()) {
            params.putAll(protocalMustParams);
        }
        if (protocalOptParams != null && !protocalOptParams.isEmpty()) {
            params.putAll(protocalOptParams);
        }
        if (applicationParams != null && !applicationParams.isEmpty()) {
            params.putAll(applicationParams);
        }
        return params;
    }


}

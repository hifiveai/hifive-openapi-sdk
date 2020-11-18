package com.hifive.api;


import com.hifive.api.internal.parser.json.ObjectJsonParser;
import com.hifive.api.internal.parser.xml.ObjectXmlParser;
import com.hifive.api.internal.util.*;
import com.hifive.utils.HttpKit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于REST的TOP客户端。
 *
 * @author yong.huang
 * @since 1.0, Sep 13, 2009
 */
public class DefaultHFClient implements HFClient {

    private static final String APP_KEY = "app_key";
    private static final String FORMAT = "format";
    private static final String METHOD = "method";
    private static final String TIMESTAMP = "timestamp";
    private static final String VERSION = "v";
    private static final String SIGN = "sign";
    private static final String SIGN_METHOD = "sign_method";
    private static final String PARTNER_ID = "partner_id";
    private static final String SESSION = "session";

    private String serverUrl;
    private String appKey;
    private String appSecret;
    private String format = Constants.FORMAT_JSON;
    private String signMethod = Constants.SIGN_METHOD_HMAC;

    private int connectTimeout = 3000;//3秒
    private int readTimeout = 15000;//15秒
    private boolean needCheckRequest = true;
    private boolean needEnableParser = true;

    public DefaultHFClient(String serverUrl, String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.serverUrl = serverUrl;
    }


    public DefaultHFClient(String serverUrl, String appKey, String appSecret, String format) {
        this(serverUrl, appKey, appSecret);
        this.format = format;
    }

    public DefaultHFClient(String serverUrl, String appKey, String appSecret, String format, int connectTimeout, int readTimeout) {
        this(serverUrl, appKey, appSecret, format);
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    public DefaultHFClient(String serverUrl, String appKey, String appSecret, String format, int connectTimeout, int readTimeout, String signMethod) {
        this(serverUrl, appKey, appSecret, format, connectTimeout, readTimeout);
        this.signMethod = signMethod;
    }

    public <T extends HFResponse> T execute(HFRequest<T> request) throws ApiException {
        return execute(request, null);
    }

    public <T extends HFResponse> T execute(HFRequest<T> request, String session) throws ApiException {
        HFParser<T> parser = null;
        if (this.needEnableParser) {
            if (Constants.FORMAT_XML.equals(this.format)) {
                parser = new ObjectXmlParser<T>(request.getResponseClass());
            } else {
                parser = new ObjectJsonParser<T>(request.getResponseClass());
            }
        }
        return _execute(request, parser, session);
    }

    private <T extends HFResponse> T _execute(HFRequest<T> request, HFParser<T> parser, String session) throws ApiException {
        if (this.needCheckRequest) {
            try {
                request.check();// if check failed,will throw ApiRuleException.
            } catch (ApiRuleException e) {
                T localResponse = null;
                try {
                    localResponse = request.getResponseClass().newInstance();
                } catch (InstantiationException e2) {
                    throw new ApiException(e2);
                } catch (IllegalAccessException e3) {
                    throw new ApiException(e3);
                }
                localResponse.setCode(e.getErrCode());
                localResponse.setMsg(e.getErrMsg());
                return localResponse;
            }
        }

        Map<String, Object> rt = doExcute(request, session);
        if (rt == null)
            return null;

        T tRsp = null;
        if (this.needEnableParser) {
            try {
                tRsp = parser.parse((String) rt.get("rsp"));
            } catch (RuntimeException e) {
                HifiveLogger.logBizError((String) rt.get("rsp"));
                throw e;
            }
        } else {
            try {
                tRsp = request.getResponseClass().newInstance();
            } catch (Exception e) {
            }
        }
        if (!tRsp.isSuccess()) {
            HifiveLogger.logErrorScene(rt, tRsp, appSecret);
        }
        return tRsp;
    }

    public <T extends HFResponse> Map<String, Object> doExcute(HFRequest<T> request, String session) throws ApiException {
        Map<String, Object> result = new HashMap<String, Object>();
        RequestParametersHolder requestHolder = new RequestParametersHolder();
        HifiveHashMap appParams = new HifiveHashMap(request.getTextParams());
        requestHolder.setApplicationParams(appParams);


        HifiveHashMap appHeaders = new HifiveHashMap();

        appHeaders.put(Constants.X_HF_ACTION, request.getApiMethodName());
        String version = request.getVersion();// 允许用户设置时间戳
        if (StringUtils.isEmpty(version)) {
            version = Constants.VERSION;
        }

        appHeaders.put(Constants.X_HF_VERSION, version);
        appHeaders.put(Constants.X_HF_NONCE, request.getNonce());
        appHeaders.put(Constants.X_HF_CLIENT_ID, request.getClientId());
        appHeaders.put(Constants.X_HF_APP_ID, appKey);
        if (!StringUtils.isEmpty(request.getToken())) {
            appHeaders.put(Constants.X_HF_TOKEN, request.getToken());
        }
        appHeaders.put(Constants.AUTHORIZATION, request.getAuthorization());
        Long timestamp = request.getTimestamp();// 允许用户设置时间戳
        if (timestamp == null) {
            timestamp = System.currentTimeMillis();
        }
        appHeaders.put(Constants.X_HF_TIMESTAMP, timestamp);
        requestHolder.setApplicationHeaders(appHeaders);

        String method = request.getMethod();// 允许用户设置时间戳
        if (StringUtils.isEmpty(method)) {
            method = HFRequest.METHOD_GET;
        }
        requestHolder.setMethod(method);

        // 添加签名参数
        try {
            appHeaders.put(Constants.AUTHORIZATION, appHeaders.get(Constants.AUTHORIZATION) + " " + Constants.SIGNATURE + "=" + HifiveUtils.signTopRequestNew(requestHolder, appSecret));
        } catch (IOException e) {
            throw new ApiException(e);
        }

        StringBuffer urlSb = new StringBuffer(serverUrl);
        try {
            String sysMustQuery = WebUtils.buildQuery(requestHolder.getProtocalMustParams(), Constants.CHARSET_UTF8);
            String sysOptQuery = WebUtils.buildQuery(requestHolder.getProtocalOptParams(), Constants.CHARSET_UTF8);
            if (!StringUtils.isEmpty(sysMustQuery) && !StringUtils.isEmpty(sysOptQuery)) {
                urlSb.append("?");
            }
            if (!StringUtils.isEmpty(sysMustQuery)) {
                urlSb.append(sysMustQuery);
            }
            if (!StringUtils.isEmpty(sysOptQuery)) {
                urlSb.append("&");
                urlSb.append(sysOptQuery);
            }
        } catch (IOException e) {
            throw new ApiException(e);
        }

        String rsp = null;
        try {
            // 是否需要上传文件
            if (request instanceof HFUploadRequest) {
                HFUploadRequest<T> uRequest = (HFUploadRequest<T>) request;
                Map<String, FileItem> fileParams = HifiveUtils.cleanupMap(uRequest.getFileParams());
                rsp = WebUtils.doPost(urlSb.toString(), appParams, fileParams, Constants.CHARSET_UTF8, connectTimeout, readTimeout, requestHolder.getApplicationHeaders());
            } else {
                if (method.equals(HFRequest.METHOD_POST)) {
                    rsp = HttpKit.post(serverUrl, appParams, null, requestHolder.getApplicationHeaders());
                } else {
                    rsp = HttpKit.get(serverUrl, appParams, requestHolder.getApplicationHeaders());
                }
            }
        } catch (IOException e) {
            throw new ApiException(e);
        }
        result.put("rsp", rsp);
        result.put("textParams", appParams);
        result.put("url", urlSb.toString());
        return result;
    }

    public void setNeedCheckRequest(boolean needCheckRequest) {
        this.needCheckRequest = needCheckRequest;
    }

    public void setNeedEnableParser(boolean needEnableParser) {
        this.needEnableParser = needEnableParser;
    }

    public void setNeedEnableLogger(boolean needEnableLogger) {
        HifiveLogger.setNeedEnableLogger(needEnableLogger);
    }


}

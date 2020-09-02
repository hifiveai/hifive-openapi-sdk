package com.hifive;

import com.hifive.exception.ApplicationException;
import com.hifive.model.CreateApiResponse;
import com.hifive.model.constant.ServerEnum;
import com.hifive.utils.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 企业用户 服务实现类
 * </p>
 *
 * @author yong.huang
 * @Date: 2020/08/03$ $
 */
public class OpenApiClient {

    /**
     * <p>
     * 请求方法
     * </p>
     *
     * @author yong.huang
     * @Date:
     **/
    private static String method;

    /**
     * <p>
     * 请求地址
     * </p>
     *
     * @author yong.huang
     * @Date:
     **/
    private static String serverUrl;

    /**
     * <p>
     * 密钥串
     * </p>
     *
     * @author yong.huang
     * @Date:
     **/
    private static String secret;

    /**
     * <p>
     * 请求协议头
     * </p>
     *
     * @author yong.huang
     * @Date:
     **/
    private static Map<String, String> headers = new HashMap<>();

    /**
     * <p>
     * 请求参数
     * </p>
     *
     * @author yong.huang
     * @Date:
     **/
    private static Map<String, String> params = new HashMap<>();

    public static OpenApiClientBuilder builder() {
        return new OpenApiClientBuilder();
    }

    public static class OpenApiClientBuilder {

        public OpenApiClientBuilder headerServerUrl(String headerServerUrl) {
            serverUrl = headerServerUrl;
            return this;
        }

        public OpenApiClientBuilder headerSecret(String headerSecret) {
            secret = headerSecret;
            return this;
        }

        public OpenApiClientBuilder headerMethod(String headerMethod) {
            method = headerMethod;
            return this;
        }

        public OpenApiClientBuilder headerAction(String headerAction) {
            headers.put("X-HF-Action", headerAction);
            return this;
        }

        public OpenApiClientBuilder headerVersion(String headerVersion) {
            headers.put("X-HF-Version", headerVersion);
            return this;
        }

        public OpenApiClientBuilder headerAppId(String headerAppId) {
            headers.put("X-HF-AppId", headerAppId);
            return this;
        }

        public OpenApiClientBuilder headerNonce(String headerNonce) {
            headers.put("X-HF-Nonce", headerNonce);
            return this;
        }

        public OpenApiClientBuilder headerClientId(String headerClientId) {
            headers.put("X-HF-ClientId", headerClientId);
            return this;
        }

        public OpenApiClientBuilder headerAuthorization(String headerAuthorization) {
            headers.put("Authorization", headerAuthorization);
            return this;
        }

        public OpenApiClientBuilder headerTimestamp(String headerTimestamp) {
            headers.put("X-HF-Timestamp", headerTimestamp);
            return this;
        }

        public OpenApiClientBuilder param(String key, String value) {
            params.put(key, value);
            return this;
        }

        protected final OpenApiClient buildClient() {
            return new OpenApiClient();
        }
    }

    /**
     * <p>
     * 发送请求
     * </p>
     *
     * @author yong.huang
     * @Date:
     **/
    public CreateApiResponse sendRequst() {
        String headerBase64 = SignUtil.headersBase64(method, headers);
        String param = SignUtil.buildParam(params);
        param = StringUtils.isEmpty(param) ? headerBase64 : param + "&" + headerBase64;
        String base64String = HelperUtil.base64(param);
        byte[] hmacSha1byte = HelperUtil.hmacSha1(base64String, secret);
        if (1 > hmacSha1byte.length) {
            throw new ApplicationException(ServerEnum.SIGN_MISMATCH);
        }

        String sign = HelperUtil.md5Hex(hmacSha1byte).toUpperCase();
        headers.put("Authorization", headers.get("Authorization") + " " + "Signature=" + sign);
        String res = null;
        if (method.equals("GET")) {
            res = HttpClientUtils.get(serverUrl, headers, params);
        } else {
            res = HttpClientUtils.post(serverUrl, headers, params);
        }
        return JsonUtil.fromJson(res, CreateApiResponse.class);
    }


    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        String nonce = RandomStringUtils.randomAlphabetic(32);
        OpenApiClient client = OpenApiClient.builder()
                .headerSecret("2d241e8f934d47d5")
                .headerClientId("hifive-wubojuan")
                .headerAction("OrderSearchMusic")
                .headerAppId("5216d02806d5464b943492838b7e4390")
                .headerAuthorization("HF3-HMAC-SHA1")
                .headerMethod("GET")
                .headerNonce(nonce)
                .headerServerUrl("https://hifive-openapi-qa.hifiveai.com")
                .headerTimestamp(String.valueOf(time))
                .headerVersion("V4.0.1")
                .param("Keyword", "a")
                .buildClient();
        CreateApiResponse response = client.sendRequst();
        System.out.println(JsonUtil.toJsonString(response));
    }

}

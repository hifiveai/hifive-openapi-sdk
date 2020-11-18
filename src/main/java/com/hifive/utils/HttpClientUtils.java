package com.hifive.utils;

import com.alibaba.fastjson.JSON;
import com.hifive.exception.ApplicationException;
import com.hifive.model.HttpRespInfo;
import com.hifive.model.constant.ServerEnum;
import com.hifive.model.constant.StringConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultSchemePortResolver;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * HttpClientUtils
 *
 * @author Jerry.X.He
 * @date 2018/4/12 13:50
 */
public class HttpClientUtils {


    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);
    private static RequestConfig httpParams;
    private static PoolingHttpClientConnectionManager cm;
    private static HttpClient client;
    /**
     * 最大连接数
     */
    public static final int MAX_TOTAL_CONNECTIONS = 200;
    /**
     * 获取连接的最大等待时间
     */
    public static final int WAIT_TIMEOUT = 30000;
    /**
     * 每个路由最大连接数
     */
    public static final int MAX_ROUTE_CONNECTIONS = 300;
    /**
     * 连接超时时间
     */
    public static final int CONNECT_TIMEOUT = 60000;
    /**
     * 读取超时时间
     */
    public static final int READ_TIMEOUT = 60000;

    static {
        cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        cm.setDefaultMaxPerRoute(80);
        httpParams = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(READ_TIMEOUT).build();
    }

    public static synchronized HttpClient getHttpClient() {
        if (null == client) {
            client = HttpClientBuilder.create().setConnectionManager(cm)
                    .setSchemePortResolver(new DefaultSchemePortResolver())
                    .setSSLSocketFactory(SSLConnectionSocketFactory.getSocketFactory())
                    .setDefaultRequestConfig(httpParams).build();
        }
        return client;
    }


    /**
     * 发送一个 get 请求, 使用给定的 header, params
     *
     * @param url         url
     * @param headers     headers
     * @param params      params
     * @param respCharset respCharset
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 2018/4/8 14:19
     */
    public static String get(String url, Map<String, String> headers, Map<String, String> params, String respCharset) {
        LOGGER.info(" 发送 http-get 请求, url : " + url + ", headers : " + JSON.toJSONString(headers) + ", params : " + JSON.toJSONString(params));
        HttpClient httpclient = getHttpClient();
        HttpGet get = null;
        String queryString = encapQueryString(params);

        try {
            String nextSep = url.contains("?") ? "&" : "?";
            String reqUrl = url;
            if (!StringUtils.isBlank(queryString)) {
                reqUrl = url + nextSep + queryString;
            }
            get = new HttpGet(reqUrl);
            for (Map.Entry<String, String> header : headers.entrySet()) {
                get.addHeader(new BasicHeader(header.getKey(), header.getValue()));
            }

            HttpResponse resp = httpclient.execute(get);
            if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                LOGGER.error(" 发送 http-get 请求, 拿到了错误码 : " + resp.getStatusLine().getStatusCode() + ", " +
                        "参数 url : " + url + ", headers : " + JSON.toJSONString(headers) + ", params : " + JSON.toJSONString(params));
                return null;
            }

            String result = EntityUtils.toString(resp.getEntity(), respCharset);
            EntityUtils.consume(resp.getEntity());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(" 发送 http-get 请求, 发生异常 " +
                    "参数 url : {}, headers : {}, params : {}, e : {}", url, JSON.toJSONString(headers), JSON.toJSONString(params), errorMsg(e));
            return null;
        } finally {
            if (get != null) {
                get.releaseConnection();
            }
        }
    }

    public static String get(String url, Map<String, String> params) {
        String charset = Charset.defaultCharset().name();
        return get(url, Collections.<String, String>emptyMap(), params, charset);
    }

    public static String get(String url, Map<String, String> headers, Map<String, String> params) {
        String charset = Charset.defaultCharset().name();
        return get(url, headers, params, charset);
    }

    public static String get(String url, Map<String, String> params, String respCharset) {
        return get(url, Collections.<String, String>emptyMap(), params, respCharset);
    }

    /**
     * 发送一个 post 请求, 使用给定的 header, params
     *
     * @param url         url
     * @param headers     headers
     * @param params      params
     * @param reqCharset  reqCharset
     * @param respCharset respCharset
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 2018/4/8 14:19
     */
    public static String post(String url, Map<String, String> headers, Map<String, String> params, String reqCharset, String respCharset) {
        try {
            HttpRespInfo resp = postForHttpResp(url, headers, params, reqCharset, respCharset);
            if (resp == null) {
                return null;
            }
            if (Integer.valueOf(resp.getStatusCode()) != HttpStatus.SC_OK) {
                LOGGER.error(" 发送 http-post 请求, 拿到了错误码 : " + resp.getStatusCode() + ", " +
                        "参数 url : " + url + ", headers : " + JSON.toJSONString(headers) + ", params : " + JSON.toJSONString(params));
                return null;
            }

            return resp.getContent();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(" 发送 http-post 请求, 发生异常 " +
                    "参数 url : " + url + ", headers : " + JSON.toJSONString(headers) + ", params : " + JSON.toJSONString(params) + ", e : " + errorMsg(e));
            return null;
        }
    }

    public static HttpRespInfo postForHttpResp(String url, Map<String, String> headers, Map<String, String> params, String reqCharset, String respCharset) {
        LOGGER.info(" 发送 http-post 请求, url : " + url + ", headers : " + JSON.toJSONString(headers) + ", params : " + JSON.toJSONString(params));
        HttpClient httpclient = getHttpClient();
        HttpPost post = new HttpPost(url);

        List<NameValuePair> paramKvs = new ArrayList<>(params.size());
        for (Map.Entry<String, String> entry : params.entrySet()) {
            paramKvs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        try {
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(paramKvs, reqCharset);
            post.setEntity(formEntity);
            for (Map.Entry<String, String> header : headers.entrySet()) {
                post.addHeader(new BasicHeader(header.getKey(), header.getValue()));
            }

            HttpResponse resp = httpclient.execute(post);
            return encapHttpRespInfoByHttpResp(resp, respCharset);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(" 发送 http-post 请求, 发生异常 " +
                    "参数 url : " + url + ", headers : " + JSON.toJSONString(headers) + ", params : " + JSON.toJSONString(params) + ", e : " + errorMsg(e));
            return null;
        } finally {
            post.releaseConnection();
        }
    }

    public static String post(String url, Map<String, String> params) {
        String charset = Charset.defaultCharset().name();
        return post(url, Collections.<String, String>emptyMap(), params, charset, charset);
    }

    public static String post(String url, Map<String, String> headers, Map<String, String> params) {
        String charset = Charset.defaultCharset().name();
        return post(url, headers, params, charset, charset);
    }

    public static String post(String url, Map<String, String> params, String reqCharset, String respCharset) {
        return post(url, Collections.<String, String>emptyMap(), params, reqCharset, respCharset);
    }

    /**
     * 发送一个 post 请求, 使用给定的 header, params
     *
     * @param url         url
     * @param headers     headers
     * @param requestBody requestBody
     * @param reqCharset  reqCharset
     * @param respCharset respCharset
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 2018/4/8 14:19
     */
    public static String postByRequestBody(String url, Map<String, String> headers, String requestBody, String reqCharset, String respCharset) {
        try {
            HttpRespInfo resp = postByRequestBodyForHttpResp(url, headers, requestBody, reqCharset, respCharset);
            if (resp == null) {
                return null;
            }
            if (Integer.valueOf(resp.getStatusCode()) != HttpStatus.SC_OK) {
                LOGGER.error(" 发送 http-post 请求, 拿到了错误码 : " + resp.getStatusCode() + ", " + "参数 url : " + url + ", headers : " + JSON.toJSONString(headers) + ", params : " + requestBody);
                return null;
            }

            return resp.getContent();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(" 发送 http-post 请求, 发生异常 " + "参数 url : " + url + ", headers : " + JSON.toJSONString(headers) + ", params : " + requestBody + ", e : " + errorMsg(e));
            return null;
        }
    }

    public static HttpRespInfo postByRequestBodyForHttpResp(String url, Map<String, String> headers, String requestBody, String reqCharset, String respCharset) {
        LOGGER.info(" 发送 http-post 请求, url : " + url + ", headers : " + JSON.toJSONString(headers) + ", params : " + requestBody);
        HttpClient httpclient = getHttpClient();
        HttpPost post = new HttpPost(url);

        try {
            StringEntity entity = new StringEntity(requestBody, reqCharset);
            entity.setContentEncoding(reqCharset);
            entity.setContentType(StringConstants.APPLICATION_JSON);
            post.setEntity(entity);
            for (Map.Entry<String, String> header : headers.entrySet()) {
                post.addHeader(new BasicHeader(header.getKey(), header.getValue()));
            }

            HttpResponse resp = httpclient.execute(post);
            return encapHttpRespInfoByHttpResp(resp, respCharset);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(" 发送 http-post 请求, 发生异常 " + "参数 url : " + url + ", headers : " + JSON.toJSONString(headers) + ", params : " + requestBody + ", e : " + errorMsg(e));
            return null;
        } finally {
            post.releaseConnection();
        }
    }

    public static String postByRequestBody(String url, String requestBody) {
        String charset = Charset.defaultCharset().name();
        return postByRequestBody(url, Collections.<String, String>emptyMap(), requestBody, charset, charset);
    }

    public static String postByRequestBody(String url, Map<String, String> headers, String requestBody) {
        String charset = Charset.defaultCharset().name();
        return postByRequestBody(url, headers, requestBody, charset, charset);
    }

    public static String postByRequestBody(String url, String requestBody, String reqCharset, String respCharset) {
        return postByRequestBody(url, Collections.<String, String>emptyMap(), requestBody, reqCharset, respCharset);
    }

    /**
     * 封装给定的参数集合的查询字符串表示
     *
     * @param params params
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 2018/4/8 16:10
     */
    public static String encapQueryString(Map<String, String> params) {
        StringBuilder queryStrBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            queryStrBuilder.append(entry.getKey() + "=" + entry.getValue());
            queryStrBuilder.append("&");
        }

        return removeIfEndsWith(queryStrBuilder.toString(), "&");
    }


    /**
     * @param url
     * @param content
     * @param toCharset
     * @return
     * @throws Exception
     */
    public static String postXml(String url, String content, String logContent, String toCharset) throws Exception {
        String result = null;

        if (StringUtils.isBlank(logContent)) {
            logContent = content;
        }
        LOGGER.info(" 发送 http-post 请求, url : " + url + ", content : " + logContent);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost(url);
            StringEntity se = new StringEntity(content, "UTF-8");
            se.setContentType("text/xml");
            httppost.setHeader("Connection", "close");
            httppost.setEntity(se);
            CloseableHttpResponse response = httpclient.execute(httppost);
            LOGGER.info("response:{}", response.getStatusLine());
            int status = response.getStatusLine().getStatusCode();

            if (status >= HttpStatus.SC_OK && status < HttpStatus.SC_MULTIPLE_CHOICES) {
                HttpEntity entity = response.getEntity();
                result = entity != null ? EntityUtils.toString(entity, toCharset) : null;
            } else {
                LOGGER.error(" 发送 http-post 请求, 拿到了错误码 : " + status + ", " +
                        "参数 url : " + url + ", content : " + content);
                throw new ApplicationException(ServerEnum.SERVER_ERROR, " 发送 http-post 请求, 拿到了错误码 : " + status, status + "");
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(" 发送 http-post 请求, 发生异常 " + "参数 url : " + url + ",  content : " + content + ", e : " + errorMsg(e));
            throw e;
        } finally {
            httpclient.close();
        }
        return result;
    }


    /**
     * http请求返回类型是不是multipart/form-data
     *
     * @param resp
     * @return
     */
    private static boolean isFileContext(HttpResponse resp) {
        Header header = resp.getFirstHeader("Content-Type");
        if (null == header || StringUtils.isBlank(header.getValue())) {
            return false;
        }
        return header.getValue().contains("multipart/form-data");
    }

    /**
     * 根据给定的 http 响应 封装 HttpRespInfo
     *
     * @param resp        resp
     * @param respCharset respCharset
     * @return com.bst.acc.common.dto.resp.HttpRespInfo
     * @author Jerry.X.He
     * @date 2019/4/29 17:43
     */
    private static HttpRespInfo encapHttpRespInfoByHttpResp(HttpResponse resp, String respCharset) throws IOException {
        HttpRespInfo result = new HttpRespInfo();
        result.setProtocol(resp.getStatusLine().getProtocolVersion().getProtocol());
        result.setStatusCode(String.valueOf(resp.getStatusLine().getStatusCode()));
        result.setReasonPhrase(resp.getStatusLine().getReasonPhrase());
        Map<String, String> headerMap = new HashMap<>();
        for (Header header : resp.getAllHeaders()) {
            headerMap.put(header.getName(), header.getValue());
        }
        result.setHeaders(headerMap);
        result.setContent(EntityUtils.toString(resp.getEntity(), respCharset));
        return result;
    }

    /**
     * 获取错误的信息
     *
     * @param e e
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 2018/4/18 9:11
     */
    public static String errorMsg(Exception e) {
        return e.getClass().getName() + " -> " + e.getMessage();
    }


    public static String removeIfEndsWith(String str, String endsWith) {
        return str.endsWith(endsWith) ? str.substring(0, str.length() - endsWith.length()) : str;
    }

}

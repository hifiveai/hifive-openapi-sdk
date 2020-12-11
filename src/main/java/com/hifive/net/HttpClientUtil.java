package com.hifive.net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hifive.utils.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * http请求发送工具类
 * Created by huangyong on 2017/5/3.
 */
public class HttpClientUtil {
    private static int TIMEOUT = 20000;//必须大于数据源的超时间
    private static int DEFAULT_MAX_RETRY_COUNT = 0;//重试次数
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    private static RequestConfig defaultRequestConfig = RequestConfig.custom()
            .setSocketTimeout(TIMEOUT)//接口读取超时
            .setConnectTimeout(TIMEOUT)//连接超时时间
            .setConnectionRequestTimeout(TIMEOUT)//从connectManager获取Connection 超时时间
            .build();

    public static String doGet(String url, Map<String, String> header, HttpHost proxy) throws HttpException {
        return doGet(url, DEFAULT_MAX_RETRY_COUNT, header, proxy, null);
    }

    public static String doGet(String url, int maxReTryCount, Map<String, Object> param, Map<String, String> header, HttpHost proxy, Charset responseCharset) throws HttpException {
        if (null != param && param.size() > 0) {
            StringBuffer sb = new StringBuffer();
            Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value == null) {
                    continue;
                }
                if (value instanceof String) {
                    if (sb.length() > 0) {
                        sb.append('&');
                    }
                    sb.append(key).append('=').append(value);
                } else {
                    throw new HttpException("参数类型错误,get方法只能使用string");
                }
            }
            url = url + "?" + sb.toString();
        }
        return doGet(url, maxReTryCount, header, proxy, responseCharset);
    }

    public static String doGet(String url, int maxReTryCount, Map<String, String> header, HttpHost proxy, Charset responseCharset) throws HttpException {
        long beginTime = System.currentTimeMillis();
        HttpGet get = new HttpGet();
        try {
            get.setURI(new URI(url));
        } catch (URISyntaxException e) {
            logger.error("doPostURISyntaxException->url:{}", url, e);
        }
        RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).setProxy(proxy).build();
        get.setConfig(requestConfig);
        addHeader(get, header);
        String result = "";
        int tryCount = 0;
        boolean isReset = false;
        do {
            try {
                tryCount++;
                result = EntityUtils.toString(HttpConnectionManager.getHttpClient().execute(get).getEntity(), responseCharset);
                break;//如果没有抛异常直接跳出
            } catch (Exception e) {
                String msg = e.getMessage();
                if (!isReset && !StringUtils.isEmpty(msg) && msg.contains("Connection reset")) {
                    isReset = true;
                    maxReTryCount++;
                    logger.info("doPostReset->url:{} useTime:{} reTryCount:{}", url.toString(), (System.currentTimeMillis() - beginTime), tryCount);
                }
                logger.info("doGetError->url:{} useTime:{} reTryCount:{}", url, (System.currentTimeMillis() - beginTime), tryCount);
                if (tryCount >= maxReTryCount) {
                    throw new HttpException(e);
                } else {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ignore) {
                    }
                }
            }
        } while (tryCount <= maxReTryCount);
        long useTime = System.currentTimeMillis() - beginTime;
        if (StringUtils.isEmpty(result)) {
            logger.error("doGet->url:{} useTime:{} reTryCount:{} result:{}", url, useTime, tryCount, result);
        } else {//如果补偿成功不记录error日志
            logger.debug("doGet->url:{} useTime:{} reTryCount:{} result:{}", url, useTime, tryCount, result);
        }
        return result;
    }

    private static void addHeader(HttpRequestBase request, Map<String, String> header) {
        if (null != header) {
            Iterator<Map.Entry<String, String>> it = header.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                String key = entry.getKey();
                String value = entry.getValue();
                request.setHeader(key, value);
            }
        }
    }

/*    public static String doPost(String url, Map<String, Object> param, HttpHost proxy) throws HttpException {
        return doPost(url, 1, param, null, proxy, null);
    }*/

    public static String doPost(String url, Map<String, Object> param, Map<String, String> header, HttpHost proxy) throws HttpException {
        return doPost(url, 1, param, header, proxy, null);
    }

    public static String doPost(String url, Map<String, Object> param, Map<String, String> header) throws HttpException {
        return doPost(url, 1, param, header, null, null);
    }

    public static String doPost(String url, int maxReTryCount, Map<String, Object> param, Map<String, String> header, HttpHost proxy, Charset responseCharset) throws HttpException {
        long beginTime = System.currentTimeMillis();
        HttpPost post = new HttpPost();
        try {
            post.setURI(new URI(url));
        } catch (URISyntaxException e) {
            logger.error("doPostURISyntaxException->url:{}", url, e);
        }
        RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).setProxy(proxy).build();
        post.setConfig(requestConfig);

        if (null != header && "application/json".equals(header.get("Content-Type"))) {
            //SerializerFeature.WriteMapNullValue
            StringEntity entity = new StringEntity(JSON.toJSONString(param), "utf-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            post.setEntity(entity);
        } else if (null != header && "multipart/form-data".equalsIgnoreCase(header.get("Content-Type"))) {
            post.setEntity(getMultipartEntity(param));
        } else {
            try {
                post.setEntity(getUrlEncodedFormEntity(param));
            } catch (UnsupportedEncodingException e) {

            }
        }

        addHeader(post, header);
        String result = "";
        int tryCount = 0;
        boolean isReset = false;
        do {
            try {
                tryCount++;
                result = EntityUtils.toString(HttpConnectionManager.getHttpClient().execute(post).getEntity(), responseCharset);
                break;//如果没有抛异常直接跳出
            } catch (Exception e) {
                String msg = e.getMessage();
                if (!isReset && !StringUtils.isEmpty(msg) && msg.contains("Connection reset")) {
                    isReset = true;
                    maxReTryCount++;
                    logger.info("doPostReset->url:{} body{} useTime:{} reTryCount:{}", url, (System.currentTimeMillis() - beginTime), tryCount);
                }
                logger.info("doPostError->url:{} useTime:{} reTryCount:{}", url, (System.currentTimeMillis() - beginTime), tryCount);
                if (tryCount >= maxReTryCount) {
                    throw new HttpException(e);
                } else {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ignore) {
                    }
                }
            }
        } while (tryCount <= maxReTryCount);
        long useTime = System.currentTimeMillis() - beginTime;
        logger.debug("doPost->url:{} useTime:{} reTryCount:{} result:{}", url, useTime, tryCount, result);
        return result;
    }

    public static String doPost(String url, int maxReTryCount, JSONObject jsonObject, Map<String, String> header, HttpHost proxy, Charset responseCharset) throws HttpException {
        long beginTime = System.currentTimeMillis();
        HttpPost post = new HttpPost();
        try {
            post.setURI(new URI(url));
        } catch (URISyntaxException e) {
            logger.error("doPostURISyntaxException->url:{} body:{}", url, jsonObject.toString(), e);
        }
        RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).setProxy(proxy).build();
        post.setConfig(requestConfig);

        StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        logger.info(jsonObject.toString());
        addHeader(post, header);
        String result = "";
        int tryCount = 0;
        boolean isReset = false;
        do {
            try {
                tryCount++;
                result = EntityUtils.toString(HttpConnectionManager.getHttpClient().execute(post).getEntity(), responseCharset);
                break;//如果没有抛异常直接跳出
            } catch (Exception e) {
                String msg = e.getMessage();
                if (!isReset && !StringUtils.isEmpty(msg) && msg.contains("Connection reset")) {
                    isReset = true;
                    maxReTryCount++;
                    logger.info("doPostReset->url:{} body{} useTime:{} reTryCount:{}", url, jsonObject.toString(), (System.currentTimeMillis() - beginTime), tryCount);
                }
                logger.info("doPostError->url:{} body:{} useTime:{} reTryCount:{}", url, jsonObject.toString(), (System.currentTimeMillis() - beginTime), tryCount);
                if (tryCount >= maxReTryCount) {
                    throw new HttpException(e);
                } else {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ignore) {
                    }
                }
            }
        } while (tryCount <= maxReTryCount);
        long useTime = System.currentTimeMillis() - beginTime;
        logger.debug("doPost->url:{} body{} useTime:{} reTryCount:{} result:{}", url, jsonObject.toString(), useTime, tryCount, result);
        return result;
    }

    public static String doPost(String url, int maxReTryCount, String body, Map<String, String> header, HttpHost proxy, Charset responseCharset) throws HttpException, UnsupportedEncodingException {
        long beginTime = System.currentTimeMillis();
        HttpPost post = new HttpPost();
        try {
            post.setURI(new URI(url));
        } catch (URISyntaxException e) {
            logger.error("doPostURISyntaxException->url:{}", url, e);
        }
        RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).setProxy(proxy).build();
        post.setConfig(requestConfig);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        if (!StringUtils.isEmpty(body)) {
            post.setEntity(new ByteArrayEntity(body.getBytes("UTF-8")));
        }

        addHeader(post, header);
        String result = "";
        int tryCount = 0;
        boolean isReset = false;
        do {
            try {
                tryCount++;
                result = EntityUtils.toString(HttpConnectionManager.getHttpClient().execute(post).getEntity(), responseCharset);
                break;//如果没有抛异常直接跳出
            } catch (Exception e) {
                String msg = e.getMessage();
                if (!isReset && !StringUtils.isEmpty(msg) && msg.contains("Connection reset")) {
                    isReset = true;
                    maxReTryCount++;
                    logger.info("doPostReset->url:{} body{} useTime:{} reTryCount:{}", url, body, (System.currentTimeMillis() - beginTime), tryCount);
                }
                logger.info("doPostError->url:{} body{} useTime:{} reTryCount:{}", url, body, (System.currentTimeMillis() - beginTime), tryCount);
                if (tryCount >= maxReTryCount) {
                    throw new HttpException(e);
                } else {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ignore) {
                    }
                }
            }
        } while (tryCount <= maxReTryCount);
        long useTime = System.currentTimeMillis() - beginTime;
        logger.debug("doPost->url:{} body{} useTime:{} reTryCount:{} result:{}", url, body, useTime, tryCount, result);
        return result;
    }

    private static HttpEntity getUrlEncodedFormEntity(Map<String, Object> params) throws UnsupportedEncodingException {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        if (null != params) {
            Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof String) {
                    nameValuePairs.add(new BasicNameValuePair(key, (String) value));
                }
            }
        }
        return new UrlEncodedFormEntity(nameValuePairs);
    }

    private static HttpEntity getMultipartEntity(Map<String, Object> param) throws HttpException {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        if (null != param) {
            Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof String) {
                    builder.addTextBody(key, ((String) value));
                } else if (value instanceof File) {
                    builder.addBinaryBody(key, (File) value);
                } else {
                    throw new HttpException("未知的类型，如果上传文件请使用File类");
                }
            }
        }
        return builder.build();
    }

    public static void main(String[] args) {
        HttpClientUtil.logger.debug("debug test");
    }
}

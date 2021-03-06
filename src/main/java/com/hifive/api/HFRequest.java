package com.hifive.api;


import java.util.Map;

/**
 * TOP请求接口。
 *
 * @author carver.gu
 * @since 1.0, Sep 12, 2009
 */
public interface HFRequest<T extends HFResponse> {


    public static final String METHOD_POST = "POST";
    public static final String METHOD_GET = "GET";

    /**
     * 获取TOP的API名称。
     *
     * @return API名称
     */
    public String getApiMethodName();

    /**
     * 获取所有的Key-Value形式的文本请求参数集合。其中：
     * <ul>
     * <li>Key: 请求参数名</li>
     * <li>Value: 请求参数值</li>
     * </ul>
     *
     * @return 文本请求参数集合
     */
    public Map<String, String> getTextParams();


    /**
     * @return 指定或默认的时间戳
     */
    public Long getTimestamp();

    /**
     * 设置时间戳，如果不设置,发送请求时将使用当时的时间。
     *
     * @param timestamp 时间戳
     */
    public void setTimestamp(Long timestamp);

    public Class<T> getResponseClass();

    /**
     * 客户端参数检查，减少服务端无效调用
     * @throws ApiRuleException 请求异常
     */
    public void check() throws ApiRuleException;


    /**
     * 添加自定义请求参数
     * @param key 关键字
     * @param value  值
     */
    public void putOtherTextParam(String key, String value);

    /**
     * @return 获取方法
     */
    public String getMethod();

    /**
     * @return  clientId
     */
    public String getClientId();

    /**
     * @return  version
     */
    public String getVersion();

    /**
     * @return  nonce
     */
    public String getNonce();

    /**
     * @return  authorization
     */
    public String getAuthorization();

    /**
     * @return  token
     */
    public String getToken();


}

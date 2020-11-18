package com.hifive.api;

/**
 * 公用常量类。
 *
 * @author carver.gu
 * @since 1.0, Sep 12, 2009
 */
public abstract class Constants {

    /**
     * TOP默认时间格式
     **/
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * TOP Date默认时区
     **/
    public static final String DATE_TIMEZONE = "GMT+8";

    /**
     * UTF-8字符集
     **/
    public static final String CHARSET_UTF8 = "UTF-8";

    /**
     * GBK字符集
     **/
    public static final String CHARSET_GBK = "GBK";

    /**
     * TOP JSON 应格式
     */
    public static final String FORMAT_JSON = "json";
    /**
     * TOP XML 应格式
     */
    public static final String FORMAT_XML = "xml";

    /**
     * MD5签名方式
     */
    public static final String SIGN_METHOD_MD5 = "md5";
    /**
     * HMAC签名方式
     */
    public static final String SIGN_METHOD_HMAC = "hmac";

    /**
     * TQL分隔符
     */
    public static final String TQL_SEPERATOR = "top_tql_seperator";

    /**
     * 授权地址
     */
    public static final String PRODUCT_CONTAINER_URL = "http://container.open.Hifive.com/container";

    /**
     * SDK版本号
     */
    public static final String SDK_VERSION = "top-sdk-java-20131208";

    /**
     * 返回的错误码
     */
    public static final String ERROR_RESPONSE = "error_response";
    public static final String ERROR_CODE = "code";
    public static final String ERROR_MSG = "msg";
    public static final String ERROR_SUB_CODE = "sub_code";
    public static final String ERROR_SUB_MSG = "sub_msg";


    /**
     * 签名字段
     **/
    public static final String SIGNATURE = "Signature";
    /**
     * 获取action
     **/
    public static final String X_HF_ACTION = "X-HF-Action";
    /**
     * 获取版本号
     **/
    public static final String X_HF_VERSION = "X-HF-Version";
    /**
     * 获取appid
     **/
    public static final String X_HF_APP_ID = "X-HF-AppId";

    public static final String X_HF_TOKEN = "X-HF-Token";
    /**
     * 获取随机数
     **/
    public static final String X_HF_NONCE = "X-HF-Nonce";
    /**
     * 获取client
     **/
    public static final String X_HF_CLIENT_ID = "X-HF-ClientId";
    /**
     * 授权字段
     **/
    public static final String AUTHORIZATION = "Authorization";

    /**
     * 时间圈
     **/
    public static final String X_HF_TIMESTAMP = "X-HF-Timestamp";

    /**
     * 签名类型
     **/
    public static final String HMAC_SHA1 = "HmacSHA1";


    /**
     * 签名类型
     **/
    public static final String VERSION = "V4.0.1";
}

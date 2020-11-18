package com.hifive.model.constant;

/**
 * <p>
 * 网关常量
 * </p>
 *
 * @author yong.huang
 * @since 2020-06-18
 */
public class Constants {

    public static final String BLANK = " ";

    public static final String EQUALS = "=";

    public static final String POST = "POST";

    /**
     * 签名字段
     **/
    public static final String SIGN = "Signature";
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


    public static final String ECHACHE_XML = "ehcache.xml";


    public static final String ECHACHE = "ehcache";


    public static final String EH_REDIS_CACHE = "ehRedisCache";


    public static final String DEFAULT_CACHE = "defaultCache";

    /**
     * HIFIVE音乐开放平台产品ID
     */
    public static final Long OPENAPI_PRODUCT_ID = 5L;

    /**
     * QPS_间隔时间（毫秒级）
     */
    public static final Integer INTERVAL_TIME = 1000;

    public final static String CACHE_URL_PATTERNS = "patterns";

    public final static String ZUUL_SERVER_JOB_LOCK = "ZUUL_SERVER_JOB_LOCK";

    public final static String ZUUL_JOB_LOCK = "ZUUL_JOB_LOCK";
    public static final Long CALLNUM_EXPIRATEION_TIME = 1440L;


    public final static Integer ZUUL_JOB_LOCK_TIME_OUT = 60;


    public final static Integer ZUUL_JOB_TIME_OUT = 60 * 1000;

    public final static String ZUUL_COMPANY_JOB_LOCK = "ZUUL_COMPANY_JOB_LOCK";

    public final static Integer ZUUL_COMPANY_JOB_LOCK_TIME_OUT = 60;


    public final static Integer ZUUL_COMPANY_JOB_TIME_OUT = 60 * 1000;


}

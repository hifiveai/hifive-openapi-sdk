package com.hifive.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.*;
/**
 * <p>
 *   签名工具类
 * </p>
 * @author yong.huang
 *@Date:
 **/
public class SignUtil {


    /** -------------------------- 辅助函数  ----------------------------------------------- **/
    /**
     * 封装协议头
     **/
    public static String headersBase64(String method,Map<String, String> headers) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(method).append(" ");
        buffer.append(headers.get("X-HF-Action")).append(" ");
        buffer.append(headers.get("X-HF-Version")).append(" ");
        buffer.append(headers.get("X-HF-AppId")).append(" ");
        buffer.append(headers.get("X-HF-Nonce")).append(" ");
        buffer.append(headers.get("X-HF-ClientId")).append(" ");
        buffer.append(headers.get("Authorization")).append(" ");
        buffer.append(headers.get("X-HF-Timestamp"));
        return HelperUtil.base64(buffer.toString());

    }


    public static String buildParam(Map<String, String> param) {
        // 字典序排序
        List<String> keys = new ArrayList<>(param.keySet());

        Collections.sort(keys);

        List<String> result = new ArrayList<>();

        for (String key : keys) {
            if (StringUtils.isNotBlank(key) || StringUtils.isNotEmpty( param.get(key))) {
                result.add(key + "=" + param.get(key));
            }
        }

        return String.join("&", result);
    }


}

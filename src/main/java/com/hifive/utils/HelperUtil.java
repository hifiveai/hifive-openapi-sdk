package com.hifive.utils;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class HelperUtil {

    private static final Base64.Encoder encoder = Base64.getEncoder();

    /**
     * md5加密
     *
     * @param input
     * @return
     */
    public static String md5(byte[] input) {
        return DigestUtils.md5Hex(input);
    }

    public static byte[] hmacSha1(String input, String key) {
        if (Objects.isNull(key)) {
            return new byte[0];
        }

        try {
            Mac mac = Mac.getInstance("HmacSHA1");

            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(Charset.forName("UTF-8")), "HmacSHA1");
            mac.init(signingKey);

            return mac.doFinal(input.getBytes(Charset.forName("UTF-8")));
        } catch (NoSuchAlgorithmException | InvalidKeyException ignored) {

        }

        return new byte[0];
    }

    /**
     * base64编码
     *
     * @param input
     * @return
     */
    public static String base64(String input) {
        Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
        return encoder.encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    public static Integer random(int length) {
        Random seed = new Random();
        return seed.nextInt(length);
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

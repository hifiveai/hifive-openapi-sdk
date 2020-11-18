package com.hifive.utils;


import com.hifive.api.internal.util.RequestParametersHolder;
import com.hifive.exception.ApplicationException;
import com.hifive.model.constant.ServerEnum;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
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
    public static String md5Hex(byte[] input) {
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
     * 给TOP请求签名。
     *
     * @param requestHolder 所有字符型的TOP请求参数
     * @param secret        签名密钥
     * @return 签名
     * @throws IOException
     */
    public static String signTopRequestNew(RequestParametersHolder requestHolder, String secret) throws IOException {
        // 第一步：检查参数是否已经排序
        String headerBase64 = headersBase64(requestHolder.getMethod(), requestHolder.getApplicationHeaders());
        String param = buildParam(requestHolder.getAllParams());
        param = StringUtils.isEmpty(param) ? headerBase64 : param + "&" + headerBase64;
        String base64String = HelperUtil.base64(param);
        byte[] hmacSha1byte = HelperUtil.hmacSha1(base64String, secret);
        if (1 > hmacSha1byte.length) {
            throw new ApplicationException(ServerEnum.SIGN_MISMATCH);
        }
        return HelperUtil.md5Hex(hmacSha1byte).toUpperCase();
    }


    public static String headersBase64(String method, Map<String, String> headers) {
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
            if (StringUtils.isNotBlank(key) || StringUtils.isNotEmpty(param.get(key))) {
                result.add(key + "=" + param.get(key));
            }
        }

        return String.join("&", result);
    }

}

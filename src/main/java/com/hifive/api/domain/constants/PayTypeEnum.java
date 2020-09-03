package com.hifive.api.domain.constants;

import lombok.Getter;

/**
 * 支付方式
 */
@Getter
public enum PayTypeEnum {
    /**
     * 微信支付
     */
    WECHART(0),

    /**
     * 支付宝支付
     */
    ALI(1);

    private Integer code;

    PayTypeEnum(Integer code){
        this.code = code;
    }

    public static PayTypeEnum getType(Integer code) throws Exception {
        switch (code){
            case 0:
                return WECHART;
            case 1:
                return ALI;
            default:
                throw new Exception("illegal code.");
        }
    }
}

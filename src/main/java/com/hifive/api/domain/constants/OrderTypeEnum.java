package com.hifive.api.domain.constants;

import lombok.Getter;

/**
 * 订单类型
 */
@Getter
public enum OrderTypeEnum {

    MUSIC(0),
    MEMBER(1);

    private Integer code;

    OrderTypeEnum(Integer code){
        this.code = code;
    }
}

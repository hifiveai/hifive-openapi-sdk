package com.hifive.api.domain.constants;


import lombok.Getter;

/**
 * 订单状态
 */
@Getter
public enum OrderStatusEnum {
    UNPAID(0),
    FINISH(1),
    CANCEL(2),
    REFUND(3);

    private Integer code;

    OrderStatusEnum(Integer code){
        this.code = code;
    }
}

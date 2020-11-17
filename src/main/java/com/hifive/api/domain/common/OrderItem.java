package com.hifive.api.domain.common;


import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** <p>
 *  订单dto
 * </p>
 * @author yong.huang
 *@Date:
 **/
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem implements Serializable {
    @ApiField("orderId")
    private Long orderId;
    @ApiField("musiceId")
    private String musiceId;
    @ApiField("tradeNo")
    private String tradeNo;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getMusiceId() {
        return musiceId;
    }

    public void setMusiceId(String musiceId) {
        this.musiceId = musiceId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }



}

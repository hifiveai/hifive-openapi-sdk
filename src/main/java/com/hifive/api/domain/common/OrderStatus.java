package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiField;
import lombok.Data;

/** <p>
 *  订单状态
 * </p>
 * @author yong.huang
 *@Date:
 **/
public class OrderStatus extends Order {

    @ApiField("status")
    private Integer status;

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }
}

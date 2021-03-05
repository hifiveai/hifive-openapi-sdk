package com.hifive.api.domain.common;

import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** <p>
 *   订单绑定作品
 * </p>
 * @author yong.huang
 **/
@NoArgsConstructor
@AllArgsConstructor
public class OrderWord extends HFObject {

    /**
     * 订单id
     **/
    @ApiField("orderId")
    private String orderId;
    /**
     * 作品id
     **/
    @ApiField("workId")
    private String workId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }
}

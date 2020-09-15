package com.hifive.api.response;

import com.hifive.api.HifiveResponse;
import com.hifive.api.domain.common.Order;
import com.hifive.api.domain.common.OrderStatus;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HFOrderRefundResponse extends HifiveResponse {

    @ApiField("data")
    Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

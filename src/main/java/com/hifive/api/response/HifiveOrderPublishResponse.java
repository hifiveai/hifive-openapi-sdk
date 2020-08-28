package com.hifive.api.response;

import com.hifive.api.HifiveResponse;
import com.hifive.api.domain.common.Order;
import com.hifive.api.domain.common.OrderWord;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HifiveOrderPublishResponse extends HifiveResponse {

    @ApiField("data")
    OrderWord orderWord;


    public OrderWord getOrderWord() {
        return orderWord;
    }

    public void setOrderWord(OrderWord orderWord) {
        this.orderWord = orderWord;
    }
}

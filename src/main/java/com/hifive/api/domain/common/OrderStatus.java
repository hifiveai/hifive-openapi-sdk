package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiField;
import lombok.Data;

/** <p>
 *  订单状态
 * </p>
 * @author yong.huang
 *@Date:
 **/
@Data
public class OrderStatus extends Order {

    @ApiField("status")
    private Integer status;
}

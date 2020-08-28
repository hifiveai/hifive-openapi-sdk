package com.hifive.api.domain.common;

import com.hifive.api.HifiveObject;
import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** <p>
 *   订单绑定作品
 * </p>
 * @author yong.huang
 *@Date:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderWord extends HifiveObject {

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
}

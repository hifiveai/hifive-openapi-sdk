package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    @ApiField("subject")
    private String subject;
    @ApiField("clientId")
    private String clientId;
    @ApiField("orderId")
    private String orderId;
    @ApiField("totalFee")
    private Integer totalFee;
    @ApiField("outTotalFee")
    private Integer outTotalFee;
    @ApiField("outTradeNo")
    private String outTradeNo;
    @ApiField("status")
    private Integer status;
    @ApiField("deadLine")
    private String deadLine;
    @ApiField("createTime")
    private String createTime;
}

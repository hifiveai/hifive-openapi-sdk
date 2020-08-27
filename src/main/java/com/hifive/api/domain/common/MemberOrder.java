package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberOrder {
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
    @ApiField("deadLine")
    private String deadLine;
    @ApiField("qrCode")
    private String qrCode;
    @ApiField("createTime")
    private String createTime;
    @ApiField("expires")
    private Long expires;
}

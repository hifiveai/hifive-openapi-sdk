package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class OrderCallback {
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getOutTotalFee() {
        return outTotalFee;
    }

    public void setOutTotalFee(Integer outTotalFee) {
        this.outTotalFee = outTotalFee;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}

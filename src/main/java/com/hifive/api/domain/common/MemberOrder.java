package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }
}

package com.hifive.api.domain.common;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/** <p>
 *  订单dto
 * </p>
 * @author yong.huang
 *@Date:
 **/
@AllArgsConstructor
@NoArgsConstructor
public class Order extends HFObject {
    @ApiField("subject")
    private String subject;
    @ApiField("totalFee")
    private Integer totalFee;
    @ApiField("orderId")
    private String orderId;
    @ApiField("HForderId")
    @JsonProperty("HForderId")
    private String HForderId;
    @ApiField("deadline")
    private String deadline;
    @ApiField("createTime")
    private String createTime;

    @ApiField("status")
    private Integer status;
    @ApiListField("music")
    private List<HFMusicFile> music;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getHForderId() {
        return HForderId;
    }

    public void setHForderId(String HForderId) {
        this.HForderId = HForderId;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<HFMusicFile> getMusic() {
        return music;
    }

    public void setMusic(List<HFMusicFile> music) {
        this.music = music;
    }
}

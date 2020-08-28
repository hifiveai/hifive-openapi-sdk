package com.hifive.api.request;

import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HifiveOrderMusicResponse;
import com.hifive.api.response.HifiveTagSheetResponse;

import java.util.Map;

public class HifiveOrderMusicRequest extends HifiveBaseRequest<HifiveOrderMusicResponse> {


    private String clientId;

    /**
     * 标签、歌单名、歌名语言版本，英文版本数据可能空
     */

    private Integer language;

    /**
     * 音频格式
     */
    private String audioFormat;

    /**
     * 音频码率
     */
    private String audioRate;


    private String subject;

    private String orderId;


    private Integer deadline;

    private String music;

    private Integer totalFee;

    private String remark;

    private String workId;

    @Override
    public String getApiMethodName() {
        return "OrderMusic";
    }


    @Override
    public Long getTimestamp() {
        return null;
    }

    @Override
    public void setTimestamp(Long timestamp) {

    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAudioFormat() {
        return audioFormat;
    }

    public void setAudioFormat(String audioFormat) {
        this.audioFormat = audioFormat;
    }

    public String getAudioRate() {
        return audioRate;
    }

    public void setAudioRate(String audioRate) {
        this.audioRate = audioRate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getDeadline() {
        return deadline;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Map<String, String> getTextParams() {
        HifiveHashMap txtParams = new HifiveHashMap() {
            {
                put("clientId", getClientId());
                put("language", getLanguage());
                put("audioFormat", getAudioFormat());
                put("audioRate", getAudioRate());


                put("subject", getSubject());
                put("orderId", getOrderId());
                put("deadline", getDeadline());

                put("music", getMusic());
                put("totalFee", getTotalFee());
                put("remark", getRemark());
                put("workId", getWorkId());
            }
        };
        return txtParams;
    }


    public Class<HifiveOrderMusicResponse> getResponseClass() {
        return HifiveOrderMusicResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }


    @Override
    public void putOtherTextParam(String key, String value) {

    }
}

package com.hifive.api.request;

import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HifiveTrafficListenSliceResponse;

import java.util.Map;

public class HFTrafficListenSliceRequest extends HFBaseRequest<HifiveTrafficListenSliceResponse> {


    /**
     * 音乐id
     **/
    private String musicId;

    /**
     * 是否是混合音乐
     */
    private Boolean isMixed = false;

    /**
     * 音频格式 文件编码,默认mp3 可选(mp3 / aac)
     */
    private String audioFormat;

    /**
     * 音频码率 音质，音乐播放时的比特率，默认320 可选(320 / 128)
     */
    private String audioRate;

    /**
     * 试听开始时间
     */
    private Integer auditionBegin;

    /**
     * 试听结束时间
     */
    private Integer auditionEnd;


    @Override
    public String getApiMethodName() {
        return "TrafficListenSlice";
    }


    @Override
    public Long getTimestamp() {
        return null;
    }

    @Override
    public void setTimestamp(Long timestamp) {

    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
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

    public Boolean getMixed() {
        return isMixed;
    }

    public void setMixed(Boolean mixed) {
        isMixed = mixed;
    }

    public Integer getAuditionBegin() {
        return auditionBegin;
    }

    public void setAuditionBegin(Integer auditionBegin) {
        this.auditionBegin = auditionBegin;
    }

    public Integer getAuditionEnd() {
        return auditionEnd;
    }

    public void setAuditionEnd(Integer auditionEnd) {
        this.auditionEnd = auditionEnd;
    }

    public Map<String, String> getTextParams() {
        HifiveHashMap txtParams = new HifiveHashMap() {
            {
                put("musicId", getMusicId());
                put("audioFormat", getAudioFormat());
                put("audioRate", getAudioRate());
                put("isMixed", getMixed());
                put("auditionBegin", getAuditionBegin());
                put("auditionEnd", getAuditionEnd());
            }
        };
        return txtParams;
    }


    public Class<HifiveTrafficListenSliceResponse> getResponseClass() {
        return HifiveTrafficListenSliceResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }


    @Override
    public void putOtherTextParam(String key, String value) {

    }
}

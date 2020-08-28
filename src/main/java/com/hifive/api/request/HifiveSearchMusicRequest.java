package com.hifive.api.request;

import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HifiveSearchMusicResponse;
import java.util.Map;

public class HifiveSearchMusicRequest extends HifivePageRequest<HifiveSearchMusicResponse> {


    private String keyword;

    /**
     * 标签、歌单名、歌名语言版本，英文版本数据可能空
     */
    private Integer language;

    private String price;


    private String tagId;

    /**
     * BPM区间
     */
    private String bpm;

    /**
     * 时长区间,单位秒
     */
    private String duration;

    @Override
    public String getApiMethodName() {
        return "SearchMusic";
    }


    @Override
    public Long getTimestamp() {
        return null;
    }

    @Override
    public void setTimestamp(Long timestamp) {

    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Map<String, String> getTextParams() {
        HifiveHashMap txtParams = new HifiveHashMap() {
        };
        return txtParams;
    }


    public Class<HifiveSearchMusicResponse> getResponseClass() {
        return HifiveSearchMusicResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }



    @Override
    public void putOtherTextParam(String key, String value) {

    }
}

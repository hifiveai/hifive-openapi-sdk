package com.hifive.api.request;

import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HifiveSearchMusicResponse;

import java.util.List;
import java.util.Map;

public class HifiveSearchMusicRequest extends HifivePageRequest<HifiveSearchMusicResponse> {


    private String keyword;

    /**
     * 标签、歌单名、歌名语言版本，英文版本数据可能空
     */
    private Integer language;

    /**
     * 结束价格
     */
    private Long priceToCent;

    private Long priceFromCent;

    private List<String> tagIds;

    /**
     * BPM区间
     */
    private Integer bpmTo;

    /**
     * BPM区间
     */
    private Integer bpmFrom;

    /**
     * 时长区间,单位秒
     */
    private Integer durationTo;


    /**
     * 时长区间,单位秒
     */
    private Integer durationFrom;

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

    public Long getPriceToCent() {
        return priceToCent;
    }

    public void setPriceToCent(Long priceToCent) {
        this.priceToCent = priceToCent;
    }

    public Long getPriceFromCent() {
        return priceFromCent;
    }

    public void setPriceFromCent(Long priceFromCent) {
        this.priceFromCent = priceFromCent;
    }

    public List<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<String> tagIds) {
        this.tagIds = tagIds;
    }

    public Integer getBpmTo() {
        return bpmTo;
    }

    public void setBpmTo(Integer bpmTo) {
        this.bpmTo = bpmTo;
    }

    public Integer getBpmFrom() {
        return bpmFrom;
    }

    public void setBpmFrom(Integer bpmFrom) {
        this.bpmFrom = bpmFrom;
    }

    public Integer getDurationTo() {
        return durationTo;
    }

    public void setDurationTo(Integer durationTo) {
        this.durationTo = durationTo;
    }

    public Integer getDurationFrom() {
        return durationFrom;
    }

    public void setDurationFrom(Integer durationFrom) {
        this.durationFrom = durationFrom;
    }

    public Map<String, String> getTextParams() {
        HifiveHashMap txtParams = new HifiveHashMap() {
            {
                put("keyword", getKeyword());
                put("language", getLanguage());
                put("priceFromCent", getPriceFromCent());
                put("priceToCent", getPriceToCent());
                put("tagIds", getTagIds());
                put("bpmFrom", getBpmFrom());
                put("bpmTo", getBpmTo());
                put("durationFrom", getDurationFrom());
                put("durationTo", getDurationTo());
                put("Page", getPage());
                put("PageSize", getPageSize());
            }
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

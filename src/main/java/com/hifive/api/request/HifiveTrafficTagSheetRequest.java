package com.hifive.api.request;

import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HifiveTagSheetResponse;
import com.hifive.api.response.HifiveTrafficTagSheetResponse;

import java.util.Map;

public class HifiveTrafficTagSheetRequest extends HifiveBaseRequest<HifiveTrafficTagSheetResponse> {


    private String tagId;
    /**
     * 歌单类型，是否为系统歌单，当不传值则为所有歌单
     */
    private Long type;

    /**
     * 歌单类型，是否为系统歌单，当不传值则为所有歌单
     */
    private Integer recoNum;

    /**
     * 标签、歌单名、歌名语言版本，英文版本数据可能空
     */
    private Integer language;

    @Override
    public String getApiMethodName() {
        return "TrafficTagSheet";
    }


    @Override
    public Long getTimestamp() {
        return null;
    }

    @Override
    public void setTimestamp(Long timestamp) {

    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Integer getRecoNum() {
        return recoNum;
    }

    public void setRecoNum(Integer recoNum) {
        this.recoNum = recoNum;
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
                put("tagId", getTagId());
                put("type", getType());
                put("recoNum", getRecoNum());
                put("language", getLanguage());
            }
        };
        return txtParams;
    }


    public Class<HifiveTrafficTagSheetResponse> getResponseClass() {
        return HifiveTrafficTagSheetResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }


    @Override
    public void putOtherTextParam(String key, String value) {

    }
}

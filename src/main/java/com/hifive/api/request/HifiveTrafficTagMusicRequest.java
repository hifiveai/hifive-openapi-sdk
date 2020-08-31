package com.hifive.api.request;

import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HifiveTrafficTagMusicResponse;
import java.util.Map;

public class HifiveTrafficTagMusicRequest extends HifivePageRequest<HifiveTrafficTagMusicResponse> {


    /**
     * 标签、歌单名、歌名语言版本，英文版本数据可能空
     */
    private Integer language;



    private String tagId;


    @Override
    public String getApiMethodName() {
        return "TrafficTagMusic";
    }


    @Override
    public Long getTimestamp() {
        return null;
    }

    @Override
    public void setTimestamp(Long timestamp) {

    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }



    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }


    public Map<String, String> getTextParams() {
        HifiveHashMap txtParams = new HifiveHashMap() {
            {

                put("language", getLanguage());
                put("tagId", getTagId());
                put("Page", getPage());
                put("PageSize", getPageSize());
            }
        };
        return txtParams;
    }


    public Class<HifiveTrafficTagMusicResponse> getResponseClass() {
        return HifiveTrafficTagMusicResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }


    @Override
    public void putOtherTextParam(String key, String value) {

    }
}

package com.hifive.api.request;

import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HifiveOrderSheetMusicResponse;
import com.hifive.api.response.HifiveSheetMusicResponse;

import java.util.Map;

public class HifiveOrderSheetMusicRequest extends HifivePageRequest<HifiveOrderSheetMusicResponse> {

    private Long sheetId;

    private Integer language;


    @Override
    public String getApiMethodName() {
        return "OrderSheetMusic";
    }


    @Override
    public Long getTimestamp() {
        return null;
    }

    @Override
    public void setTimestamp(Long timestamp) {

    }

    public Long getSheetId() {
        return sheetId;
    }

    public void setSheetId(Long sheetId) {
        this.sheetId = sheetId;
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
                put("sheetId", getSheetId());
                put("language",getPageSize());
                put("Page", getPage());
                put("PageSize",getPageSize());
            }
        };
        return txtParams;
    }


    public Class<HifiveOrderSheetMusicResponse> getResponseClass() {
        return HifiveOrderSheetMusicResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }



    @Override
    public void putOtherTextParam(String key, String value) {

    }
}
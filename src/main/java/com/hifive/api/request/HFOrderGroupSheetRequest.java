package com.hifive.api.request;

import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HFOrderGroupSheetResponse;

import java.util.Map;

public class HFOrderGroupSheetRequest extends HFPageRequest<HFOrderGroupSheetResponse> {

    private String groupId;


    private Integer recoNum;

    private Integer language;

    @Override
    public String getApiMethodName() {
        return "OrderGroupSheet";
    }


    @Override
    public Long getTimestamp() {
        return null;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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

    @Override
    public void setTimestamp(Long timestamp) {

    }


    public Map<String, String> getTextParams() {
        HifiveHashMap txtParams = new HifiveHashMap() {
            {
                put("groupId", getGroupId());
                put("recoNum", getRecoNum());
                put("language", getLanguage());
                put("Page", getPage());
                put("PageSize", getPageSize());
            }
        };
        return txtParams;
    }


    public Class<HFOrderGroupSheetResponse> getResponseClass() {
        return HFOrderGroupSheetResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }


    @Override
    public void putOtherTextParam(String key, String value) {

    }
}

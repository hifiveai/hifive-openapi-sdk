package com.hifive.api.request;

import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HifiveRecommendResponse;

import java.util.Map;

public class HifiveRecommendRequest extends HifivePageRequest<HifiveRecommendResponse> {


    @Override
    public String getApiMethodName() {
        return "BaseFavorite";
    }


    @Override
    public Long getTimestamp() {
        return null;
    }

    @Override
    public void setTimestamp(Long timestamp) {

    }


    public Map<String, String> getTextParams() {
        HifiveHashMap txtParams = new HifiveHashMap() {
            {
                put("Page", getPage());
                put("PageSize",getPageSize());
            }
        };
        return txtParams;
    }


    public Class<HifiveRecommendResponse> getResponseClass() {
        return HifiveRecommendResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }



    @Override
    public void putOtherTextParam(String key, String value) {

    }
}

package com.hifive.api.request;

import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HFOrderTagResponse;

import java.util.Map;

public class HFOrderTagRequest extends HFBaseRequest<HFOrderTagResponse> {


    @Override
    public String getApiMethodName() {
        return "OrderTag";
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
            }
        };
        return txtParams;
    }


    public Class<HFOrderTagResponse> getResponseClass() {
        return HFOrderTagResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }



    @Override
    public void putOtherTextParam(String key, String value) {

    }
}

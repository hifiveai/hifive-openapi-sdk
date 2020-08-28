package com.hifive.api.request;

import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HifiveOrderTagResponse;
import com.hifive.api.response.HifiveTrafficTagResponse;

import java.util.Map;

public class HifiveTrafficTagRequest extends HifiveBaseRequest<HifiveTrafficTagResponse> {


    @Override
    public String getApiMethodName() {
        return "TrafficTag";
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


    public Class<HifiveTrafficTagResponse> getResponseClass() {
        return HifiveTrafficTagResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }



    @Override
    public void putOtherTextParam(String key, String value) {

    }
}

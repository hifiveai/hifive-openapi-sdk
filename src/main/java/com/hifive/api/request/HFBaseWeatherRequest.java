package com.hifive.api.request;

import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HFBaseWeatherResponse;

import java.util.Map;

public class HFBaseWeatherRequest extends HFBaseRequest<HFBaseWeatherResponse> {

    /**
     * 经纬度信息 latitude/longitude
     */
    private String location;

    @Override
    public String getApiMethodName() {
        return "BaseWeather";
    }


    @Override
    public Long getTimestamp() {
        return null;
    }

    @Override
    public void setTimestamp(Long timestamp) {

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, String> getTextParams() {
        HifiveHashMap txtParams = new HifiveHashMap() {
            {
                put("location", getLocation());
            }
        };
        return txtParams;
    }


    public Class<HFBaseWeatherResponse> getResponseClass() {
        return HFBaseWeatherResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }


    @Override
    public void putOtherTextParam(String key, String value) {

    }
}

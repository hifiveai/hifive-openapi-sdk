package com.hifive.api.request;

import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HifiveHotResponse;
import java.util.Map;

public class HifiveHotRequest extends HifivePageRequest<HifiveHotResponse> {
    /**
     * 开始时间
     **/
    private Long startTime;

    /**
     * 时间间隔
     **/
    private Integer duration;

    @Override
    public String getApiMethodName() {
        return "BaseHot";
    }


    @Override
    public Long getTimestamp() {
        return null;
    }

    @Override
    public void setTimestamp(Long timestamp) {

    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Map<String, String> getTextParams() {
        HifiveHashMap txtParams = new HifiveHashMap() {
            {
                put("Duration", getDuration());
                put("StartTime",getStartTime());
                put("Page", getPage());
                put("PageSize",getPageSize());
            }
        };
        return txtParams;
    }


    public Class<HifiveHotResponse> getResponseClass() {
        return HifiveHotResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }



    @Override
    public void putOtherTextParam(String key, String value) {

    }
}

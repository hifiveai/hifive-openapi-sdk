package com.hifive.api.domain.common.moji;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
public class WeatherDetails implements Serializable {
    private AQI aqi;
    private City city;
    private Condition condition;
    private Map<String, List<LiveIndex>> liveIndex;
    private List<Alert> alert;
    private List<Limit> limit;

    public AQI getAqi() {
        return aqi;
    }

    public void setAqi(AQI aqi) {
        this.aqi = aqi;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Map<String, List<LiveIndex>> getLiveIndex() {
        return liveIndex;
    }

    public void setLiveIndex(Map<String, List<LiveIndex>> liveIndex) {
        this.liveIndex = liveIndex;
    }

    public List<Alert> getAlert() {
        return alert;
    }

    public void setAlert(List<Alert> alert) {
        this.alert = alert;
    }

    public List<Limit> getLimit() {
        return limit;
    }

    public void setLimit(List<Limit> limit) {
        this.limit = limit;
    }
}

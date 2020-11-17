package com.hifive.api.response;

import com.hifive.api.HFResponse;
import com.hifive.api.domain.common.Weather;
import com.hifive.api.internal.mapping.ApiField;

public class HFBaseWeatherResponse extends HFResponse {

    @ApiField("data")
    Weather weather;

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}

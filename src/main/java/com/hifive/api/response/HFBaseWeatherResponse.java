package com.hifive.api.response;

import com.hifive.api.HifiveResponse;
import com.hifive.api.domain.common.Weather;
import com.hifive.api.domain.common.WeatherView;
import com.hifive.api.internal.mapping.ApiField;

public class HFBaseWeatherResponse extends HifiveResponse {

    @ApiField("data")
    Weather weather;

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}

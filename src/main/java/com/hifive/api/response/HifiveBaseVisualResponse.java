package com.hifive.api.response;

import com.hifive.api.HifiveResponse;
import com.hifive.api.domain.common.WeatherView;
import com.hifive.api.internal.mapping.ApiField;

public class HifiveBaseVisualResponse extends HifiveResponse {

    @ApiField("data")
    WeatherView weatherView;

    public WeatherView getWeatherView() {
        return weatherView;
    }

    public void setWeatherView(WeatherView weatherView) {
        this.weatherView = weatherView;
    }
}

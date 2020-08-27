package com.hifive.api.domain.common.moji;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDetails implements Serializable {
    private AQI aqi;
    private City city;
    private Condition condition;
    private Map<String, List<LiveIndex>> liveIndex;
    private List<Alert> alert;
    private List<Limit> limit;
}

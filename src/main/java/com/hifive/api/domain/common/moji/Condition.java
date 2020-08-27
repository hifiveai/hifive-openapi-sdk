package com.hifive.api.domain.common.moji;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Condition implements Serializable {
    private String condition;
    private String conditionId;
    private String humidity;
    private String icon;
    private String pressure;
    private String realFeel;
    private String sunRise;
    private String sunSet;
    private String temp;
    private String tips;
    private String updatetime;
    private String uvi;
    private String vis;
    private String windDegrees;
    private String windDir;
    private String windLevel;
    private String windSpeed;
}

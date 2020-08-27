package com.hifive.api.domain.common.moji;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AQI implements Serializable {
    @ApiField("cityName")
    private String cityName;
    @ApiField("co")
    private String co;
    @ApiField("coC")
    private String coC;
    @ApiField("no2")
    private String no2;
    @ApiField("no2C")
    private String no2C;
    @ApiField("o3")
    private String o3;
    @ApiField("o3C")
    private String o3C;
    private String pm10;
    private String pm10C;
    private String pm25;
    private String pm25C;
    private String pubtime;
    private String rank;
    private String so2;
    private String so2C;
    private String value;
}

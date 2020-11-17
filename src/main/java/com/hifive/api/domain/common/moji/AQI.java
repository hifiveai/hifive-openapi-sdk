package com.hifive.api.domain.common.moji;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getCoC() {
        return coC;
    }

    public void setCoC(String coC) {
        this.coC = coC;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getNo2C() {
        return no2C;
    }

    public void setNo2C(String no2C) {
        this.no2C = no2C;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getO3C() {
        return o3C;
    }

    public void setO3C(String o3C) {
        this.o3C = o3C;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getPm10C() {
        return pm10C;
    }

    public void setPm10C(String pm10C) {
        this.pm10C = pm10C;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getPm25C() {
        return pm25C;
    }

    public void setPm25C(String pm25C) {
        this.pm25C = pm25C;
    }

    public String getPubtime() {
        return pubtime;
    }

    public void setPubtime(String pubtime) {
        this.pubtime = pubtime;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getSo2C() {
        return so2C;
    }

    public void setSo2C(String so2C) {
        this.so2C = so2C;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

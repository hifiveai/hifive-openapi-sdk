package com.hifive.api.domain.common.moji;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class City implements Serializable {
    private Long cityId;
    private String counname;
    private String ianatimezone;
    private String name;
    private String pname;
    private String secondaryname;
    private String timezone;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCounname() {
        return counname;
    }

    public void setCounname(String counname) {
        this.counname = counname;
    }

    public String getIanatimezone() {
        return ianatimezone;
    }

    public void setIanatimezone(String ianatimezone) {
        this.ianatimezone = ianatimezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getSecondaryname() {
        return secondaryname;
    }

    public void setSecondaryname(String secondaryname) {
        this.secondaryname = secondaryname;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}

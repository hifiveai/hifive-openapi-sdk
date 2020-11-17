package com.hifive.api.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather extends HFObject {
    /**
     * 所在城市
     */
    @ApiField("price")
    private String city;

    /**
     * 天气
     */
    @ApiField("weatherName")
    private String weatherName;

    /**
     * 气温
     */
    @ApiField("temperature")
    private String temperature;

    /**
     * 风向
     */
    @ApiField("windDirection")
    private String windDirection;

    /**
     * 湿度
     */
    @ApiField("humidity")
    private String humidity;

    /**
     * 空气指数
     */
    @ApiField("airQuality")
    private String airQuality;

    /**
     * 空气指数等级
     */
    @ApiField("airQualityLevel")
    private Integer airQualityLevel;

    /**
     * 天气图标
     */
    @ApiField("weatherIcon")
    private String weatherIcon;

    /**
     * 天气标题
     */
    @ApiField("weatherTitle")
    private String weatherTitle;

    /**
     * 天气描述
     */
    @ApiField("weatherDesc")
    private String weatherDesc;

    /**
     * 小贴士
     */
    @ApiListField("tips")
    @JsonIgnore
    private List<Tip> tips;
}

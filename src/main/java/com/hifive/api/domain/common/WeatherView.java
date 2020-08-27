package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherView implements Serializable {
    /**
     * 背景图地址
     */
    @ApiField("background")
    private String background;
    /**
     * 小贴士内容
     */
    @ApiListField("tips")
    private List<Tip> tips;
}

package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @ApiField("id")
    private Long id;
    @ApiField("level")
    private Integer level;
    @ApiField("days")
    private Integer days;
    @ApiField("originalPrice")
    private Integer originalPrice;
    @ApiField("discountPrice")
    private Integer discountPrice;
    @ApiField("firstPrice")
    private Integer firstPrice;
    @ApiField("desc")
    private String desc;
}

package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Integer discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(Integer firstPrice) {
        this.firstPrice = firstPrice;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

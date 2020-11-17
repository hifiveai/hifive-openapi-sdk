package com.hifive.api.domain.common;

import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class HFMusicVersion extends HFObject {
    @ApiField("name")
    private String name;
    /**
     * 版本名称
     */
    @ApiField("musicId")
    private String musicId;
    /**
     * 是否免费 0 收费  1 免费
     */
    @ApiField("free")
    private Integer free;
    /**
     * 单价 单位 分
     */
    @ApiField("price")
    private Long price;


    /**
     * 是否为主版本，1：是 0：否
     */
    @ApiField("majorVersion")
    private Boolean majorVersion;


    /**
     * 时长（秒）
     */
    @ApiField("duration")
    private Integer duration;
    
    /**
     * 推荐试听开始时间
     */
    @ApiField("auditionBegin")
    private Integer auditionBegin;
    /**
     * 推荐试听结束时间
     */
    @ApiField("auditionEnd")
    private  Integer auditionEnd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(Boolean majorVersion) {
        this.majorVersion = majorVersion;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getAuditionBegin() {
        return auditionBegin;
    }

    public void setAuditionBegin(Integer auditionBegin) {
        this.auditionBegin = auditionBegin;
    }

    public Integer getAuditionEnd() {
        return auditionEnd;
    }

    public void setAuditionEnd(Integer auditionEnd) {
        this.auditionEnd = auditionEnd;
    }
}

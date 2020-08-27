package com.hifive.api.domain.common;

import com.hifive.api.HifiveObject;
import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HifiveMusicVersion extends HifiveObject {
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
}

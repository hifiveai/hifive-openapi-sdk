package com.hifive.api.domain.ai;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hifive.api.domain.common.es.EsCover;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties({"artistName", "authorName", "composerName", "arrangerName", "coverUrl"})
public class RecommendHifiveMusicDetail extends RecommendHifiveMusic {

    /**
     * 歌曲介绍
     */
    @ApiField("intro")
    private String intro;

    /**
     * 推荐试听开始时间
     */
    @ApiField("auditionBegin")
    private Integer auditionBegin;
    /**
     * 推荐试听结束时间
     */
    @ApiField("auditionEnd")
    private Integer auditionEnd;
    /**
     * 封面列表
     **/
    @ApiListField("cover")
    private List<EsCover> cover;

    /**
     * 版本
     */
    @ApiListField("version")
    private List version;

}

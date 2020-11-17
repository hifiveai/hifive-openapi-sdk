package com.hifive.api.domain.ai;

import com.hifive.api.domain.common.HFMusicVersion;
import com.hifive.api.domain.common.es.EsCover;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class HFRecommendMusicDetail extends HFRecommendMusic {

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
    private List<HFMusicVersion> version;


    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public List<EsCover> getCover() {
        return cover;
    }

    public void setCover(List<EsCover> cover) {
        this.cover = cover;
    }

    public List<HFMusicVersion> getVersion() {
        return version;
    }

    public void setVersion(List<HFMusicVersion> version) {
        this.version = version;
    }
}

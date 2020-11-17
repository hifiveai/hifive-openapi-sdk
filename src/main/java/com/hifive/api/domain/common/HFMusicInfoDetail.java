package com.hifive.api.domain.common;

import com.hifive.api.domain.common.base.HFBaseMusic;
import com.hifive.api.domain.common.es.EsCover;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.Data;

import java.util.List;

@Data
public class HFMusicInfoDetail extends HFBaseMusic {

    /**
     * 歌曲介绍
     */
    @ApiField("intro")
    private String intro;

    /**
     * 标签
     */
    @ApiListField("tag")
    private List<Tag> tag;

    /**
     * 版本
     */
    @ApiListField("version")
    private List<HFMusicVersion> version;


    /**
     * 封面列表
     **/
    @ApiListField("cover")
    private List<EsCover> cover;


    /**
     * 表演者
     */
    @ApiListField("artist")
    private List<HifiveMusicPerson> artist;
    /**
     * 作词
     */
    @ApiListField("author")
    private List<HifiveMusicPerson> author;
    /**
     * 作曲
     */
    @ApiListField("composer")
    private List<HifiveMusicPerson> composer;
    /**
     * 编曲
     */
    @ApiListField("arranger")
    private List<HifiveMusicPerson> arranger;

}


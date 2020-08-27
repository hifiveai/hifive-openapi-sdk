package com.hifive.api.domain.common.v0;

import com.hifive.api.domain.common.HifiveMusicPerson;
import com.hifive.api.domain.common.Tag;
import com.hifive.api.domain.common.base.HifiveBaseMusicSize;
import com.hifive.api.domain.common.base.HifiveBaseVersion;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class HifiveMusicSize extends HifiveBaseMusicSize {
    /**
     * 歌曲编号
     */
    @ApiField("musicId")
    private String musicId;
    /**
     * 歌曲名称
     */
    @ApiField("musicName")
    private String musicName;
    /**
     * 专辑编号
     */
    @ApiField("albumId")
    private String albumId;
    /**
     * 专辑名称
     */
    @ApiField("albumName")
    private String albumName;

    /**
     * 表演者
     */
    @ApiField("artistName")
    private String artistName;
    /**
     * 作词
     */
    @ApiField("authorName")
    private String authorName;
    /**
     * 作曲
     */
    @ApiField("composerName")
    private String composerName;
    /**
     * 编曲
     */
    @ApiField("arrangerName")
    private String arrangerName;

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

    /**
     * 时长 单位 秒
     */
    @ApiField("duration")
    private Integer duration;

    /**
     * 每分钟节拍
     */
    @ApiField("bpm")
    private Integer bpm;

    /**
     * 标签
     */
    @ApiListField("tag")
    private List<Tag> tag;

    /**
     * 版本
     */
    @ApiListField("version")
    private List<HifiveBaseVersion> version;
}

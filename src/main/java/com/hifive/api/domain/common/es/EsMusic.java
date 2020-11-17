package com.hifive.api.domain.common.es;

import com.hifive.api.domain.common.HFMusicVersion;
import com.hifive.api.domain.common.HifiveMusicPerson;
import com.hifive.api.domain.common.Tag;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsMusic {
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
     * 封面列表
     **/
    @ApiListField("cover")
    private List<EsCover> cover;
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
     * 封面
     */
    @ApiField("coverUrl")
    private String coverUrl;

    /**
     * 文件地址
     */
    @ApiField("fileUrl")
    private String fileUrl;

    /**
     * 波形图地址
     */
    @ApiField("waveUrl")
    private String waveUrl;

    /**
     * 时长 单位 秒
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
    private Integer auditionEnd;

    /**
     * 每分钟节拍
     */
    @ApiField("bpm")
    private Integer bpm;

    /**
     * 概要
     */
    @ApiField("intro")
    private String intro;

    /**
     * 标签
     */
    @ApiListField("tag")
    private List<Tag> tag;


    /** <p>
     * 企业用户 服务实现类 版本2
     * </p>
     * @author yong.huang
     *@Date:
     **/
    @ApiListField("version")
    private List<HFMusicVersion> version;
}

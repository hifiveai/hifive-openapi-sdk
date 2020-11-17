package com.hifive.api.domain.common;

import com.hifive.api.domain.common.base.HFBaseMusic;
import com.hifive.api.domain.common.es.EsCover;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.Data;

import java.util.List;

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


    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    public List<HFMusicVersion> getVersion() {
        return version;
    }

    public void setVersion(List<HFMusicVersion> version) {
        this.version = version;
    }

    public List<EsCover> getCover() {
        return cover;
    }

    public void setCover(List<EsCover> cover) {
        this.cover = cover;
    }

    public List<HifiveMusicPerson> getArtist() {
        return artist;
    }

    public void setArtist(List<HifiveMusicPerson> artist) {
        this.artist = artist;
    }

    public List<HifiveMusicPerson> getAuthor() {
        return author;
    }

    public void setAuthor(List<HifiveMusicPerson> author) {
        this.author = author;
    }

    public List<HifiveMusicPerson> getComposer() {
        return composer;
    }

    public void setComposer(List<HifiveMusicPerson> composer) {
        this.composer = composer;
    }

    public List<HifiveMusicPerson> getArranger() {
        return arranger;
    }

    public void setArranger(List<HifiveMusicPerson> arranger) {
        this.arranger = arranger;
    }
}


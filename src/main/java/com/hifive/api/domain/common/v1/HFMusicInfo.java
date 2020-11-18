package com.hifive.api.domain.common.v1;

import com.hifive.api.domain.common.HifiveMusicPerson;
import com.hifive.api.domain.common.Tag;
import com.hifive.api.domain.common.base.HFBaseMusicInfo;
import com.hifive.api.domain.common.base.HFBaseVersion;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import java.util.List;


public class HFMusicInfo extends HFBaseMusicInfo {
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
    private List<HFBaseVersion> version;

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    public List<HFBaseVersion> getVersion() {
        return version;
    }

    public void setVersion(List<HFBaseVersion> version) {
        this.version = version;
    }
}

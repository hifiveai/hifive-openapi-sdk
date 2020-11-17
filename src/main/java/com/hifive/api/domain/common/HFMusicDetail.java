package com.hifive.api.domain.common;

import com.hifive.api.domain.common.base.HFBaseMusicDetails;
import com.hifive.api.domain.common.base.HFBaseVersion;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class HFMusicDetail extends HFBaseMusicDetails {
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

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getComposerName() {
        return composerName;
    }

    public void setComposerName(String composerName) {
        this.composerName = composerName;
    }

    public String getArrangerName() {
        return arrangerName;
    }

    public void setArrangerName(String arrangerName) {
        this.arrangerName = arrangerName;
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

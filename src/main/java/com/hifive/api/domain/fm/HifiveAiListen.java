package com.hifive.api.domain.fm;

import com.hifive.api.HifiveObject;
import com.hifive.api.internal.mapping.ApiField;


public class HifiveAiListen extends HifiveObject {
    /**
     * 歌曲唯一标识
     */
    @ApiField("songId")
    private String songId;

    /**
     * 歌曲名称
     */
    @ApiField("songName")
    private String songName;

    /**
     * 歌曲作者
     */
    @ApiField("songAuthor")
    private String songAuthor;

    /**
     * 歌曲编码
     */
    @ApiField("songUid")
    private String songUid;

    /**
     * 歌曲描述
     */
    @ApiField("songDescription")
    private String songDescription;

    /**
     * 歌曲流派
     */
    @ApiField("songGenre")
    private String songGenre;

    /**
     * 歌曲情绪
     */
    @ApiField("songMood")
    private String songMood;

    /**
     * 歌曲器乐
     */
    @ApiField("songInstrument")
    private String songInstrument;

    /**
     * 歌曲场景
     */
    @ApiField("songScene")
    private String songScene;

    /**
     * 歌曲时长
     */
    @ApiField("duration")
    private Integer duration;

    /**
     * 歌曲url
     */
    @ApiField("songUrl")
    private String songUrl;

    /**
     * 歌曲封面
     */
    @ApiField("coverUrl")
    private String coverUrl;
    @ApiField("waveUrl")
    private String waveUrl;

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongAuthor() {
        return songAuthor;
    }

    public void setSongAuthor(String songAuthor) {
        this.songAuthor = songAuthor;
    }

    public String getSongUid() {
        return songUid;
    }

    public void setSongUid(String songUid) {
        this.songUid = songUid;
    }

    public String getSongDescription() {
        return songDescription;
    }

    public void setSongDescription(String songDescription) {
        this.songDescription = songDescription;
    }

    public String getSongGenre() {
        return songGenre;
    }

    public void setSongGenre(String songGenre) {
        this.songGenre = songGenre;
    }

    public String getSongMood() {
        return songMood;
    }

    public void setSongMood(String songMood) {
        this.songMood = songMood;
    }

    public String getSongInstrument() {
        return songInstrument;
    }

    public void setSongInstrument(String songInstrument) {
        this.songInstrument = songInstrument;
    }

    public String getSongScene() {
        return songScene;
    }

    public void setSongScene(String songScene) {
        this.songScene = songScene;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getWaveUrl() {
        return waveUrl;
    }

    public void setWaveUrl(String waveUrl) {
        this.waveUrl = waveUrl;
    }
}

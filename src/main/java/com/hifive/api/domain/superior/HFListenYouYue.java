package com.hifive.api.domain.superior;

import com.hifive.api.HFObject;


/**
 * TOP API: hifive.user.get request
 *
 * @author yong.huang
 * @since 1.0, 2013-12-08 16:51:41
 */
public class HFListenYouYue extends HFObject {

    private String id;
    /**
     * 歌曲名称
     */
    private String songName;

    /**
     * 歌曲作者
     */
    private String songAuthor;

    /**
     * 歌曲编码
     */
    private String songUid;

    /**
     * 歌曲描述
     */
    private String songDescription;

    /**
     *  歌曲流派
     */
    private String songGenre;

    /**
     * 歌曲情绪
     */
    private String songMood;

    /**
     * 歌曲乐器
     */
    private String songInstrument;

    /**
     * 歌曲场景
     */
    private String songScene;

    /**
     * 歌曲时长
     */
    private Integer duration;

    /**
     *  歌曲播放地址
     */
    private String songUrl;

    /**
     *  歌曲封⾯
     */
    private String coverUrl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


}

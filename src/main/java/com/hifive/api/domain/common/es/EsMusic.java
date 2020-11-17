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

    public List<EsCover> getCover() {
        return cover;
    }

    public void setCover(List<EsCover> cover) {
        this.cover = cover;
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

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getWaveUrl() {
        return waveUrl;
    }

    public void setWaveUrl(String waveUrl) {
        this.waveUrl = waveUrl;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }

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
}

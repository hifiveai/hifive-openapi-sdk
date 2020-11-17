package com.hifive.api.domain.common;


import com.hifive.api.HFObject;
import com.hifive.api.domain.common.base.HFBaseMusicInfo;
import com.hifive.api.domain.common.es.EsCover;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class TrafficTagSheet<T extends HFBaseMusicInfo> extends HFObject {
    /**
     * 歌单编号
     */
    @ApiField("sheetId")
    private Long sheetId;
    /**
     * 歌单名称
     */
    @ApiField("sheetName")
    private String sheetName;

    /**
     * 歌单总数
     */
    @ApiField("musicTotal")
    private Integer musicTotal;


    /**
     * 歌单类型，是否为系统歌单，当不传值则为所有歌单
     */
    @ApiField("type")
    private Integer type;
    /**
     * 歌单描述
     */
    @ApiField("describe")
    private String describe;
    /**
     * 是否免费 0:收费 1:免费
     */
    @ApiField("free")
    private Integer free;
    /**
     * 歌单价格 单位 分
     */
    @ApiField("price")
    private Long price;

    /**
     * 歌单标签
     */
    @ApiListField("tag")
    private List<Tag> tag;
    /**
     * 歌曲信息
     */
    @ApiListField("music")
    private List<T> music;

    /**
     * 封面列表
     **/
    @ApiListField("cover")
    private List<EsCover> cover;


    public Long getSheetId() {
        return sheetId;
    }

    public void setSheetId(Long sheetId) {
        this.sheetId = sheetId;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Integer getMusicTotal() {
        return musicTotal;
    }

    public void setMusicTotal(Integer musicTotal) {
        this.musicTotal = musicTotal;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    public List<T> getMusic() {
        return music;
    }

    public void setMusic(List<T> music) {
        this.music = music;
    }

    public List<EsCover> getCover() {
        return cover;
    }

    public void setCover(List<EsCover> cover) {
        this.cover = cover;
    }
}

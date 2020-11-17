package com.hifive.api.domain.common.es;

import com.hifive.api.domain.common.Tag;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class EsSheet {
    /**
     * 歌单编号
     */
    @ApiField("listId")
    private Long listId;

    /**
     * 0-系统/1-自定义
     */
    @ApiField("type")
    private Integer type;


    /**
     * 歌曲总数
     */
    @ApiField("musicCount")
    private Integer musicCount;

    /**
     * 歌单名称
     */
    @ApiField("sheetName")
    private String sheetName;

    /**
     * 运营组编号
     */
    @ApiListField("runnerGroupId")
    private List<String> runnerGroupId;

    /**
     * 封面地址
     */
    @ApiField("coverUrl")
    private String coverUrl;

    /**
     * 标签
     */
    @ApiListField("tag")
    private List<Tag> tag;

    /**
     * 标签id 数组   **为了适配优越的接口
     */
    @ApiListField("tagId")
    private List<Long> tagId;

    /**
     * 排序
     */
    @ApiListField("music")
    private List<EsSort> music;

    /**
     * 歌单描述
     */
    @ApiField("describe")
    private String describe;

    /**
     * 封面尺寸
     **/
    @ApiField("size")
    private String size ;

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMusicCount() {
        return musicCount;
    }

    public void setMusicCount(Integer musicCount) {
        this.musicCount = musicCount;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<String> getRunnerGroupId() {
        return runnerGroupId;
    }

    public void setRunnerGroupId(List<String> runnerGroupId) {
        this.runnerGroupId = runnerGroupId;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    public List<Long> getTagId() {
        return tagId;
    }

    public void setTagId(List<Long> tagId) {
        this.tagId = tagId;
    }

    public List<EsSort> getMusic() {
        return music;
    }

    public void setMusic(List<EsSort> music) {
        this.music = music;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

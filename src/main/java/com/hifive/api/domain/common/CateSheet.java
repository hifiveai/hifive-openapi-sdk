package com.hifive.api.domain.common;

import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CateSheet extends HFObject {
    /**
     * 分类编号
     */
    @ApiField("cateId")
    private String cateId;
    /**
     * 歌单编号
     */
    @ApiField("listId")
    private Long listId;
    /**
     * 歌单名称
     */
    @ApiField("listName")
    private String listName;
    /**
     * 歌单封面地址
     */
    @ApiField("coverUrl")
    private String coverUrl;
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

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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
}

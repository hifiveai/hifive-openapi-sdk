package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor                 //无参构造
@AllArgsConstructor
public class RunnerCate implements Serializable {
    /**
     * 类别id
     *
     */
    @ApiField("cateId")
    private String cateId;

    /**
     * 类别名称
     */
    @ApiField("cateName")
    private String cateName;

    /**
     * 封面
     */
    @ApiField("coverUrl")
    private String coverUrl;

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}

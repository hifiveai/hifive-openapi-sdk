package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Tag implements Serializable {
    /**
     * 标签编号
     */
    @ApiField("tagId")
    private Long tagId;

    /**
     * 标签名称
     */
    @ApiField("tagName")
    private String tagName;
    /**
     * 子标签
     */
    @ApiListField("child")
    private List<Tag> child;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Tag> getChild() {
        return child;
    }

    public void setChild(List<Tag> child) {
        this.child = child;
    }
}

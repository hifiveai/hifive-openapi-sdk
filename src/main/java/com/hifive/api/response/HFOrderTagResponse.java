package com.hifive.api.response;

import com.hifive.api.HFResponse;
import com.hifive.api.domain.common.Tag;
import com.hifive.api.internal.mapping.ApiListField;

import java.util.List;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HFOrderTagResponse extends HFResponse {
    @ApiListField("data")
    List<Tag> tagList;

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }
}

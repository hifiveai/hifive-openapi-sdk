package com.hifive.api.response;

import com.hifive.api.HFResponse;
import com.hifive.api.domain.common.TagPrice;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HFMusicConfigResponse extends HFResponse {

    @ApiField("data")
    TagPrice tagPrice;

    public TagPrice getTagPrice() {
        return tagPrice;
    }

    public void setTagPrice(TagPrice tagPrice) {
        this.tagPrice = tagPrice;
    }
}

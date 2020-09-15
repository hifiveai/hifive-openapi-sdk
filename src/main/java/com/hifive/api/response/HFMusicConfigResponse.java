package com.hifive.api.response;

import com.hifive.api.HifiveResponse;
import com.hifive.api.domain.common.RecomandMusicPage;
import com.hifive.api.domain.common.TagPrice;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HFMusicConfigResponse extends HifiveResponse {

    @ApiField("data")
    TagPrice tagPrice;

    public TagPrice getTagPrice() {
        return tagPrice;
    }

    public void setTagPrice(TagPrice tagPrice) {
        this.tagPrice = tagPrice;
    }
}

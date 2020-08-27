package com.hifive.api.response;

import com.hifive.api.HifiveResponse;
import com.hifive.api.domain.common.RecomandMusicPage;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HifiveRecommendResponse extends HifiveResponse {

    @ApiField("data")
    RecomandMusicPage page;

    public RecomandMusicPage getPage() {
        return page;
    }

    public void setPage(RecomandMusicPage page) {
        this.page = page;
    }
}

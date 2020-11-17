package com.hifive.api.response;

import com.hifive.api.HFResponse;
import com.hifive.api.domain.ai.HFRecommendMusicDetail;
import com.hifive.api.domain.common.HFPage;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HFBaseFavoriteResponse extends HFResponse {

    @ApiField("data")
    HFPage<HFRecommendMusicDetail> hifivePage;

    public HFPage<HFRecommendMusicDetail> getHifivePage() {
        return hifivePage;
    }

    public void setHifivePage(HFPage<HFRecommendMusicDetail> hifivePage) {
        this.hifivePage = hifivePage;
    }
}

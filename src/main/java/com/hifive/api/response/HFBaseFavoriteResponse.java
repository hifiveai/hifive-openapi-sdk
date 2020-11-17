package com.hifive.api.response;

import com.hifive.api.HFResponse;
import com.hifive.api.domain.ai.HFRecommendMusicDetail;
import com.hifive.api.domain.common.HifivePage;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HFBaseFavoriteResponse extends HFResponse {

    @ApiField("data")
    HifivePage<HFRecommendMusicDetail> hifivePage;

    public HifivePage<HFRecommendMusicDetail> getHifivePage() {
        return hifivePage;
    }

    public void setHifivePage(HifivePage<HFRecommendMusicDetail> hifivePage) {
        this.hifivePage = hifivePage;
    }
}

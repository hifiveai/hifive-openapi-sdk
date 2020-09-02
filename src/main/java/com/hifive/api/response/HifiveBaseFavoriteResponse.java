package com.hifive.api.response;

import com.hifive.api.HifiveResponse;
import com.hifive.api.domain.ai.HifiveRecommendMusicDetail;
import com.hifive.api.domain.common.HifiveMusicInfoDetail;
import com.hifive.api.domain.common.HifivePage;
import com.hifive.api.domain.common.RecomandMusicPage;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HifiveBaseFavoriteResponse extends HifiveResponse {

    @ApiField("data")
    HifivePage<HifiveRecommendMusicDetail> hifivePage;

    public HifivePage<HifiveRecommendMusicDetail> getHifivePage() {
        return hifivePage;
    }

    public void setHifivePage(HifivePage<HifiveRecommendMusicDetail> hifivePage) {
        this.hifivePage = hifivePage;
    }
}

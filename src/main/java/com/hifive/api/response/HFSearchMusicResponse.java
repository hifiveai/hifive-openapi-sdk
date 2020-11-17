package com.hifive.api.response;

import com.hifive.api.HFResponse;
import com.hifive.api.domain.common.HFMusicInfoDetail;
import com.hifive.api.domain.common.HFPage;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HFSearchMusicResponse extends HFResponse {

    @ApiField("data")
    HFPage<HFMusicInfoDetail> hifivePage;

    public HFPage<HFMusicInfoDetail> getHifivePage() {
        return hifivePage;
    }

    public void setHifivePage(HFPage<HFMusicInfoDetail> hifivePage) {
        this.hifivePage = hifivePage;
    }
}

package com.hifive.api.response;

import com.hifive.api.HifiveResponse;
import com.hifive.api.domain.common.HifiveMusicInfoDetail;
import com.hifive.api.domain.common.HifivePage;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HFTrafficTagMusicResponse extends HifiveResponse {

    @ApiField("data")
    HifivePage<HifiveMusicInfoDetail> hifivePage;

    public HifivePage<HifiveMusicInfoDetail> getHifivePage() {
        return hifivePage;
    }

    public void setHifivePage(HifivePage<HifiveMusicInfoDetail> hifivePage) {
        this.hifivePage = hifivePage;
    }
}

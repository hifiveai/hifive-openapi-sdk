package com.hifive.api.response;

import com.hifive.api.HifiveResponse;
import com.hifive.api.domain.common.HifiveMusicInfoDetail;
import com.hifive.api.domain.common.HifivePage;
import com.hifive.api.domain.common.TrafficTagSheet;
import com.hifive.api.internal.mapping.ApiField;


/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HFTrafficGroupSheetResponse extends HifiveResponse {

    @ApiField("data")
    HifivePage<TrafficTagSheet<HifiveMusicInfoDetail>> hifivePage;


    public HifivePage<TrafficTagSheet<HifiveMusicInfoDetail>> getHifivePage() {
        return hifivePage;
    }

    public void setHifivePage(HifivePage<TrafficTagSheet<HifiveMusicInfoDetail>> hifivePage) {
        this.hifivePage = hifivePage;
    }
}

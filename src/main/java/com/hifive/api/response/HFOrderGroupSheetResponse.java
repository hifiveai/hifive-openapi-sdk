package com.hifive.api.response;

import com.hifive.api.HFResponse;
import com.hifive.api.domain.common.HFMusicInfoDetail;
import com.hifive.api.domain.common.HifivePage;
import com.hifive.api.domain.common.TrafficTagSheet;
import com.hifive.api.internal.mapping.ApiField;


/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HFOrderGroupSheetResponse extends HFResponse {

    @ApiField("data")
    HifivePage<TrafficTagSheet<HFMusicInfoDetail>> hifivePage;


    public HifivePage<TrafficTagSheet<HFMusicInfoDetail>> getHifivePage() {
        return hifivePage;
    }

    public void setHifivePage(HifivePage<TrafficTagSheet<HFMusicInfoDetail>> hifivePage) {
        this.hifivePage = hifivePage;
    }
}

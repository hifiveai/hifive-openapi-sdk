package com.hifive.api.response;

import com.hifive.api.HFResponse;
import com.hifive.api.domain.common.*;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HFTagSheetResponse extends HFResponse {


    @ApiField("data")
    HifivePage<TrafficTagSheet<HFMusicInfoDetail>> hifivePage;

    public HifivePage<TrafficTagSheet<HFMusicInfoDetail>> getHifivePage() {
        return hifivePage;
    }

    public void setHifivePage(HifivePage<TrafficTagSheet<HFMusicInfoDetail>> hifivePage) {
        this.hifivePage = hifivePage;
    }
}

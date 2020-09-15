package com.hifive.api.response;

import com.hifive.api.HifiveResponse;
import com.hifive.api.domain.common.*;
import com.hifive.api.domain.common.base.HifiveBaseMusicInfo;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;

import java.util.List;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HFTagSheetResponse extends HifiveResponse {



    @ApiField("data")
    HifivePage<TrafficTagSheet<HifiveMusicInfoDetail>> hifivePage;

    public HifivePage<TrafficTagSheet<HifiveMusicInfoDetail>> getHifivePage() {
        return hifivePage;
    }

    public void setHifivePage(HifivePage<TrafficTagSheet<HifiveMusicInfoDetail>> hifivePage) {
        this.hifivePage = hifivePage;
    }
}

package com.hifive.api.response;

import com.hifive.api.HFResponse;
import com.hifive.api.domain.common.HFMusicInfoDetail;
import com.hifive.api.domain.common.HifivePage;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HFOrderSheetMusicResponse extends HFResponse {

    @ApiField("data")
    HifivePage<HFMusicInfoDetail> hifivePage;

    public HifivePage<HFMusicInfoDetail> getHifivePage() {
        return hifivePage;
    }

    public void setHifivePage(HifivePage<HFMusicInfoDetail> hifivePage) {
        this.hifivePage = hifivePage;
    }


}

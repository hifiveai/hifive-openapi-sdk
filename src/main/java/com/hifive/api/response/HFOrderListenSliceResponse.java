package com.hifive.api.response;

import com.hifive.api.HFResponse;
import com.hifive.api.domain.common.HFMusicListenSlice;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HFOrderListenSliceResponse extends HFResponse {



    @ApiField("data")
    HFMusicListenSlice hifiveMusicListenSlice;

    public HFMusicListenSlice getHifiveMusicListenSlice() {
        return hifiveMusicListenSlice;
    }

    public void setHifiveMusicListenSlice(HFMusicListenSlice hifiveMusicListenSlice) {
        this.hifiveMusicListenSlice = hifiveMusicListenSlice;
    }
}

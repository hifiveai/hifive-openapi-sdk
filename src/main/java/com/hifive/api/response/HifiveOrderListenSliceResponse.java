package com.hifive.api.response;

import com.hifive.api.HifiveResponse;
import com.hifive.api.domain.common.HifiveMusicListenSlice;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HifiveOrderListenSliceResponse extends HifiveResponse {



    @ApiField("data")
    HifiveMusicListenSlice hifiveMusicListenSlice;

    public HifiveMusicListenSlice getHifiveMusicListenSlice() {
        return hifiveMusicListenSlice;
    }

    public void setHifiveMusicListenSlice(HifiveMusicListenSlice hifiveMusicListenSlice) {
        this.hifiveMusicListenSlice = hifiveMusicListenSlice;
    }
}

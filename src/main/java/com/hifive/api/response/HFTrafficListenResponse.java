package com.hifive.api.response;

import com.hifive.api.HFResponse;
import com.hifive.api.domain.HFMusicSize;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HFTrafficListenResponse extends HFResponse {


    @ApiField("data")
    HFMusicSize hifiveMusicSize;

    public HFMusicSize getHifiveMusicSize() {
        return hifiveMusicSize;
    }

    public void setHifiveMusicSize(HFMusicSize hifiveMusicSize) {
        this.hifiveMusicSize = hifiveMusicSize;
    }
}

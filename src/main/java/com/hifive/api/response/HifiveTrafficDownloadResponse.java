package com.hifive.api.response;

import com.hifive.api.HifiveResponse;
import com.hifive.api.domain.HifiveMusicSize;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HifiveTrafficDownloadResponse extends HifiveResponse {



    @ApiField("data")
    HifiveMusicSize hifiveMusicSize;

    public HifiveMusicSize getHifiveMusicSize() {
        return hifiveMusicSize;
    }

    public void setHifiveMusicSize(HifiveMusicSize hifiveMusicSize) {
        this.hifiveMusicSize = hifiveMusicSize;
    }
}

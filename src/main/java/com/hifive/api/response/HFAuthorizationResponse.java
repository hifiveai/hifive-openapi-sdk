package com.hifive.api.response;

import com.hifive.api.HFResponse;
import com.hifive.api.domain.common.HFMusicAuthPDF;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HFAuthorizationResponse extends HFResponse {

    @ApiField("data")
    HFMusicAuthPDF hifiveMusicAuthPDF;

    public HFMusicAuthPDF getHifiveMusicAuthPDF() {
        return hifiveMusicAuthPDF;
    }

    public void setHifiveMusicAuthPDF(HFMusicAuthPDF hifiveMusicAuthPDF) {
        this.hifiveMusicAuthPDF = hifiveMusicAuthPDF;
    }
}

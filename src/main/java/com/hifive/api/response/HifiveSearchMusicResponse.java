package com.hifive.api.response;

import com.hifive.api.HifiveResponse;
import com.hifive.api.domain.common.MusicPage;
import com.hifive.api.domain.common.TagPrice;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HifiveSearchMusicResponse extends HifiveResponse {

    @ApiField("data")
    MusicPage musicPage;

    public MusicPage getMusicPage() {
        return musicPage;
    }

    public void setMusicPage(MusicPage musicPage) {
        this.musicPage = musicPage;
    }
}

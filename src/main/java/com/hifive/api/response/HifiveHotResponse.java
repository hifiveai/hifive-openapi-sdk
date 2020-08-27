package com.hifive.api.response;

import com.hifive.api.HifiveResponse;
import com.hifive.api.domain.common.HotMusicPage;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HifiveHotResponse extends HifiveResponse {
    @ApiField("data")
    HotMusicPage page;

    public HotMusicPage getPage() {
        return page;
    }

    public void setPage(HotMusicPage page) {
        this.page = page;
    }
}

package com.hifive.api.response;

import com.hifive.api.HifiveResponse;
import com.hifive.api.domain.common.HifiveMusicInfoDetail;
import com.hifive.api.domain.common.Page;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HifiveHotResponse extends HifiveResponse {
    @ApiField("data")
    Page<HifiveMusicInfoDetail> page;

    public Page<HifiveMusicInfoDetail> getPage() {
        return page;
    }

    public void setPage(Page<HifiveMusicInfoDetail> page) {
        this.page = page;
    }
}

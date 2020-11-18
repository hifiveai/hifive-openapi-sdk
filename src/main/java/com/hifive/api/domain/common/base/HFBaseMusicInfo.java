package com.hifive.api.domain.common.base;

import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 音乐基本信息
 *
 * @author yong.huang
 * @since 1.0, Sep 13, 2009
 */
public abstract class HFBaseMusicInfo extends HFObject {
    /**
     * 封面
     */
    @ApiField("coverUrl")
    private String coverUrl;

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}

package com.hifive.api.domain.common.base;

import com.hifive.api.HifiveObject;
import com.hifive.api.internal.mapping.ApiField;

public abstract class HifiveBaseMusicInfo extends HifiveObject {
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

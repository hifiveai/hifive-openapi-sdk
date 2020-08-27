package com.hifive.api.domain.common.base;

import com.hifive.api.HifiveObject;
import com.hifive.api.internal.mapping.ApiField;

public class HifiveBaseDownload extends HifiveObject {
    @ApiField("fileUrl")
    private String fileUrl;
    /**
     * 过期时间戳
     */
    @ApiField("expires")
    private Long expires;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }
}

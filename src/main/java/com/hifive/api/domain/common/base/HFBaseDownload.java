package com.hifive.api.domain.common.base;

import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;

public class HFBaseDownload extends HFObject {
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

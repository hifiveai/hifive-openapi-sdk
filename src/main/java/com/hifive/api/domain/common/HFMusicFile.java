package com.hifive.api.domain.common;

import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class HFMusicFile extends HFObject {
    @ApiField("musicId")
    private String musicId;
    @ApiField("fileUrl")
    private String fileUrl;
    /**
     * 过期时间戳
     */
    @ApiField("expires")
    private Long expires;

    /**
     * 文件大小
     */
    @ApiField("fileSize")
    private Long fileSize;

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

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

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}

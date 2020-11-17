package com.hifive.api.domain.common;

import com.hifive.api.domain.common.base.HFBaseDownload;
import com.hifive.api.internal.mapping.ApiField;
import lombok.Data;

/**
 * <p>
 * 下载歌曲
 * </p>
 *
 * @author yong.huang
 * @Date:
 **/
public class DownloadHF extends HFBaseDownload {

    /** 音乐id **/
    @ApiField("musicId")
    private String musicId;

    /** 过期时间 **/
    @ApiField("fileSize")
    private Long fileSize;

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}

package com.hifive.api.domain.common.base;


import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;

/**
 * 音乐基本信息，带文件大小
 *
 * @author yong.huang
 * @since 1.0, Sep 13, 2009
 */
public class HFBaseMusicSize extends HFObject {

    /**
     * 文件大小
     */
    @ApiField("fileSize")
    private Long fileSize;
    /**
     * 封面
     */
    @ApiField("coverUrl")
    private String coverUrl;

    /**
     * 文件地址
     */
    @ApiField("fileUrl")
    private String fileUrl;

    /**
     * 波形图地址
     */
    @ApiField("waveUrl")
    private String waveUrl;

    /**
     * 过期时间戳
     */
    @ApiField("expires")
    private Long expires;

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getWaveUrl() {
        return waveUrl;
    }

    public void setWaveUrl(String waveUrl) {
        this.waveUrl = waveUrl;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }
}

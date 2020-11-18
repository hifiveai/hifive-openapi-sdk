package com.hifive.api.domain.common.base;


import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;
/**
 * 音乐详情
 *
 * @author yong.huang
 * @since 1.0, Sep 13, 2009
 */
public abstract class HFBaseMusicDetails extends HFObject {
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

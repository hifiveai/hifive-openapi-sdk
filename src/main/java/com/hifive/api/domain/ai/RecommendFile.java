package com.hifive.api.domain.ai;

import com.hifive.api.internal.mapping.ApiField;

import java.io.Serializable;

/**
 * 推荐歌曲
 *
 * @author yong.huang
 * @since 1.0, Sep 13, 2009
 */
public class RecommendFile implements Serializable {
    @ApiField("songId")
    private String songId;
    @ApiField("rmdRate")
    private String rmdRate;

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getRmdRate() {
        return rmdRate;
    }

    public void setRmdRate(String rmdRate) {
        this.rmdRate = rmdRate;
    }
}

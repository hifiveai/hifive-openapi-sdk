package com.hifive.api.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hifive.api.domain.common.base.HFBaseMusicSize;
import com.hifive.api.internal.mapping.ApiField;
import lombok.Data;

/**
 * <p>
 * 音乐试听实体
 * </p>
 *
 * @author yong.huang
 */
@JsonIgnoreProperties({"coverUrl","waveUrl"})
public class HFMusicListenSlice extends HFBaseMusicSize {

    /**
     * 歌曲编号
     */
    @ApiField("musicId")
    private String musicId;

    /** "range":bytes=0-29999,文件分片起止字节 **/
    @ApiField("range")
    private String range;

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}

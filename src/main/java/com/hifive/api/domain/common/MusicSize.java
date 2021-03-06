package com.hifive.api.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hifive.api.domain.common.base.HFBaseMusicSize;
import com.hifive.api.internal.mapping.ApiField;
import lombok.Data;


@JsonIgnoreProperties({"coverUrl"})
public class MusicSize extends HFBaseMusicSize {

    /** 歌曲ID **/
    @ApiField("musicId")
    private String musicId;

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }
}

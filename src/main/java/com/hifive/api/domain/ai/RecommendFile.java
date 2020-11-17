package com.hifive.api.domain.ai;

import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.Data;

import java.io.Serializable;

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

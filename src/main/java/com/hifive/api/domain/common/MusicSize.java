package com.hifive.api.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hifive.api.domain.common.base.HifiveBaseMusicSize;
import com.hifive.api.internal.mapping.ApiField;
import lombok.Data;


@Data
@JsonIgnoreProperties({"coverUrl"})
public class MusicSize extends HifiveBaseMusicSize {

    /** 歌曲ID **/
    @ApiField("musicId")
    private String musicId;
}

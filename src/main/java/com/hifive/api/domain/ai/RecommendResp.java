package com.hifive.api.domain.ai;

import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RecommendResp implements Serializable {
    @ApiField("songListId")
    private String songListId;
    @ApiField("appId")
    private String appId;
    @ApiField("scene")
    private String scene;
    @ApiListField("songList")
    private List<RecommendFile> songList;
}

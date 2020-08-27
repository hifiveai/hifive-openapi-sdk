package com.hifive.api.domain.ai;

import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.Data;

import java.io.Serializable;

@Data
public class RecommendFile implements Serializable {
    @ApiField("songId")
    private String songId;
    @ApiField("rmdRate")
    private String rmdRate;
}

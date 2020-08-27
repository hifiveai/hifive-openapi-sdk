package com.hifive.api.domain.ai;

import com.hifive.api.internal.mapping.ApiField;
import lombok.Data;

import java.io.Serializable;

@Data
public class HifiveHotFile implements Serializable {
    @ApiField("id")
    private String id;
    @ApiField("hot")
    private Double hot;
}

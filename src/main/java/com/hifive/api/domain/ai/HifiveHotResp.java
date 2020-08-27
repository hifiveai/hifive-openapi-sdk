package com.hifive.api.domain.ai;

import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HifiveHotResp implements Serializable {
    @ApiListField("hotSpotSongs")
    private List<HifiveHotFile> hotSpotSongs;
}

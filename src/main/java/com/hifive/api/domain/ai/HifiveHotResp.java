package com.hifive.api.domain.ai;

import com.hifive.api.internal.mapping.ApiListField;

import java.io.Serializable;
import java.util.List;

public class HifiveHotResp implements Serializable {
    @ApiListField("hotSpotSongs")
    private List<HifiveHotFile> hotSpotSongs;

    public List<HifiveHotFile> getHotSpotSongs() {
        return hotSpotSongs;
    }

    public void setHotSpotSongs(List<HifiveHotFile> hotSpotSongs) {
        this.hotSpotSongs = hotSpotSongs;
    }
}

package com.hifive.api.domain.ai;

import com.hifive.api.internal.mapping.ApiListField;

import java.io.Serializable;
import java.util.List;

/**
 * 热门音乐
 *
 * @author yong.huang
 * @since 1.0, Sep 13, 2009
 */
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

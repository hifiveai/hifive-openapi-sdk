package com.hifive.api.domain.ai;

import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;

import java.io.Serializable;
import java.util.List;

/**
 * 推荐歌曲响应
 *
 * @author yong.huang
 * @since 1.0, Sep 13, 2009
 */
public class RecommendResp implements Serializable {
    @ApiField("songListId")
    private String songListId;
    @ApiField("appId")
    private String appId;
    @ApiField("scene")
    private String scene;
    @ApiListField("songList")
    private List<RecommendFile> songList;


    public String getSongListId() {
        return songListId;
    }

    public void setSongListId(String songListId) {
        this.songListId = songListId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public List<RecommendFile> getSongList() {
        return songList;
    }

    public void setSongList(List<RecommendFile> songList) {
        this.songList = songList;
    }
}

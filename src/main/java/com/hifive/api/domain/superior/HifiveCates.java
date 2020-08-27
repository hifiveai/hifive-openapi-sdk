package com.hifive.api.domain.superior;

import com.hifive.api.HifiveObject;
import com.hifive.api.internal.mapping.ApiField;
/**
 * TOP API: hifive.user.get request
 *
 * @author yong.huang
 * @since 1.0, 2013-12-08 16:51:41
 */
public class HifiveCates extends HifiveObject {
    /**
     * 歌单id
     */
    @ApiField("id")
    private Long id;
    /**
     * 频道名称
     */
    @ApiField("channelName")
    private String channelName;
    /**
     * 频道场景标签
     */
    @ApiField("scene")
    private String scene;

    /**
     * 频道情绪标签
     */
    @ApiField("mood")
    private String mood;

    /**
     * 频道其他标签
     */
    @ApiField("otherTag")
    private String otherTag;

    /**
     * 频道描述
     */
    @ApiField("description")
    private String description;

    /**
     * 频道封⾯
     */
    @ApiField("coverUrl")
    private String coverUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }


    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }


}

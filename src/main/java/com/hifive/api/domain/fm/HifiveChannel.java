package com.hifive.api.domain.fm;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class HifiveChannel {

    /**
     * 频道列表id
     */
    @ApiField("channelId")
    private Long channelId;

    /**
     * 频道列表名称
     */
    @ApiField("channelName")
    private String channelName;

    /**
     * 场景标签
     */
    @ApiField("sceneTag")
    private String sceneTag;

    /**
     * 频道情绪标签
     */
    @ApiField("moodTag")
    private String moodTag;

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
     * 频道封面
     */
    @ApiField("coverUrl")
    private String coverUrl;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getSceneTag() {
        return sceneTag;
    }

    public void setSceneTag(String sceneTag) {
        this.sceneTag = sceneTag;
    }

    public String getMoodTag() {
        return moodTag;
    }

    public void setMoodTag(String moodTag) {
        this.moodTag = moodTag;
    }

    public String getOtherTag() {
        return otherTag;
    }

    public void setOtherTag(String otherTag) {
        this.otherTag = otherTag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}

package com.hifive.api.domain.fm;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
}

package com.hifive.api.domain.superior;


import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;
/**
 * TOP API: hifive.user.get request
 *
 * @author yong.huang
 * @since 1.0, 2013-12-08 16:51:41
 */
public class MusicListTag extends HFObject {
    /**
     * 频道类别id
     */
    @ApiField("id")
    private Long id;
    /**
     *  频道类别名称
     */
    @ApiField("channelCategoryName")
    private String channelCategoryName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelCategoryName() {
        return channelCategoryName;
    }

    public void setChannelCategoryName(String channelCategoryName) {
        this.channelCategoryName = channelCategoryName;
    }

}

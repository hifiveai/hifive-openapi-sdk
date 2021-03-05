package com.hifive.api.domain.common;

import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** <p>
 *   运营组
 * </p>
 * @author yong.huang
 **/
@AllArgsConstructor
@NoArgsConstructor
public class RunnerGroup extends HFObject {
    /**
     * 类别id
     *
     */
    @ApiField("groupId")
    private String groupId;

    /**
     * 类别名称
     */
    @ApiField("groupName")
    private String groupName;

    /**
     * 封面
     */
    @ApiField("coverUrl")
    private String coverUrl;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}

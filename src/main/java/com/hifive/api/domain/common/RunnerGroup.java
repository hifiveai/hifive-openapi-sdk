package com.hifive.api.domain.common;

import com.hifive.api.HifiveObject;
import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** <p>
 *   运营组
 * </p>
 * @author yong.huang
 * @Date:  2020/06/29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RunnerGroup extends HifiveObject {
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
}

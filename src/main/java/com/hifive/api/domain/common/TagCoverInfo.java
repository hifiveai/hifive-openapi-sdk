package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** <p>
 * 包含封面地址的标签
 * </p>
 * @author yong.huang
 *@Date:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagCoverInfo extends Tag {

    @ApiField("coverUrl")
    private String coverUrl;
}

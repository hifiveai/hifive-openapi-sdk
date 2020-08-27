package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagPrice implements Serializable {
    /**
     * 子标签
     */
    @ApiListField("tagList")
    private List<Tag> tagList;

    /**
     * <p>
     * 价格列表
     * </p>
     **/
    @ApiField("prices")
    private Long[] prices;
}

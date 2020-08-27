package com.hifive.api.domain.common.es;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Es 中的标签
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsTag {
    /**
     * id
     */
    @ApiField("id")
    private Long id;

    /**
     * 父级编号
     */
    @ApiField("pid")
    private Long pid;

    /**
     * 图标
     */
    @ApiField("icon")
    private String icon;

    /**
     * 标签名
     */
    @ApiField("name")
    private String name;
}

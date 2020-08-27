package com.hifive.api.domain.common.es;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Es 艺人头像文件信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsAvatar {

    /**
     * 文件编号编号
     */
    @ApiField("id")
    private Long id;

    /**
     * 文件类型
     */
    @ApiField("type")
    private String type;

    /**
     * 文件地址
     */
    @ApiField("url")
    private String url;
}

package com.hifive.api.domain.common.es;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Es中的文件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EsFile {

    /**
     * 文件地址
     */
    @ApiField("url")
    private String url;

    /**
     * 文件扩展名
     */
    @ApiField("ext")
    private String ext;
}

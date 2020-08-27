package com.hifive.api.domain.common.es;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ES中的歌单封面
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsCover implements Serializable {
    /**
     * 封面地址
     */
    @ApiField("url")
    private String url;

    /**
     * 封面尺寸
     */
    @ApiField("size")
    private String size;
}

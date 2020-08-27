package com.hifive.api.domain.common.es;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Es 中的歌曲、歌单排序
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsSort implements Serializable {

    /**
     * 歌曲id
     */
    @ApiField("id")
    private String id;

    /**
     * 排序
     */
    @ApiField("sort")
    private Integer sort;
}

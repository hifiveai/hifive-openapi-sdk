package com.hifive.api.domain.common.es;

import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Es中的运营组
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsRunnerGroup {
    /**
     * 编号
     */
    @ApiField("id")
    private Long id;

    /**
     * 名称
     */
    @ApiField("groupName")
    private String groupName;

    /**
     * 标识
     */
    @ApiField("mark")
    private String mark;

    /**
     * 介绍
     */
    @ApiField("intro")
    private String intro;

    /**
     * 封面
     */
    @ApiField("cover")
    private EsCover cover;

    /**
     * 歌单
     */
    @ApiListField("sheet")
    private List<EsSort> sheet;
}

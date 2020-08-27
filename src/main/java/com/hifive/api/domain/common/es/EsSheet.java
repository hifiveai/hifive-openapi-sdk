package com.hifive.api.domain.common.es;

import com.hifive.api.domain.common.Tag;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsSheet {
    /**
     * 歌单编号
     */
    @ApiField("listId")
    private Long listId;

    /**
     * 0-系统/1-自定义
     */
    @ApiField("type")
    private Integer type;


    /**
     * 歌曲总数
     */
    @ApiField("musicCount")
    private Integer musicCount;

    /**
     * 歌单名称
     */
    @ApiField("sheetName")
    private String sheetName;

    /**
     * 运营组编号
     */
    @ApiListField("runnerGroupId")
    private List<String> runnerGroupId;

    /**
     * 封面地址
     */
    @ApiField("coverUrl")
    private String coverUrl;

    /**
     * 标签
     */
    @ApiListField("tag")
    private List<Tag> tag;

    /**
     * 标签id 数组   **为了适配优越的接口
     */
    @ApiListField("tagId")
    private List<Long> tagId;

    /**
     * 排序
     */
    @ApiListField("music")
    private List<EsSort> music;

    /**
     * 歌单描述
     */
    @ApiField("describe")
    private String describe;

    /**
     * 封面尺寸
     **/
    @ApiField("size")
    private String size ;
}

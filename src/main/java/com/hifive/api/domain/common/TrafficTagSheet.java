package com.hifive.api.domain.common;


import com.hifive.api.domain.common.base.HifiveBaseMusicInfo;
import com.hifive.api.domain.common.base.HifiveBaseMusicSize;
import com.hifive.api.domain.common.es.EsCover;
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
public class TrafficTagSheet<T extends HifiveBaseMusicInfo> implements Serializable {
    /**
     * 歌单编号
     */
    @ApiField("sheetId")
    private Long sheetId;
    /**
     * 歌单名称
     */
    @ApiField("sheetName")
    private String sheetName;

    /**
     * 歌单总数
     */
    @ApiField("musicTotal")
    private Integer musicTotal;


    /**
     * 歌单类型，是否为系统歌单，当不传值则为所有歌单
     */
    @ApiField("type")
    private Integer type;
    /**
     * 歌单描述
     */
    @ApiField("describe")
    private String describe;
    /**
     * 是否免费 0:收费 1:免费
     */
    @ApiField("free")
    private Integer free;
    /**
     * 歌单价格 单位 分
     */
    @ApiField("price")
    private Long price;

    /**
     * 歌单标签
     */
    @ApiListField("tag")
    private List<Tag> tag;
    /**
     * 歌曲信息
     */
    @ApiListField("music")
    private List<T> music;

    /**
     * 封面列表
     **/
    @ApiListField("cover")
    private List<EsCover> cover;
}

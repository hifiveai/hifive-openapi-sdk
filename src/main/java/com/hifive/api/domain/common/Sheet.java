package com.hifive.api.domain.common;

import com.hifive.api.domain.common.base.HFBaseMusicInfo;
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
public class Sheet<T extends HFBaseMusicInfo> implements Serializable {
    /**
     * 歌单编号
     */
    @ApiField("listId")
    private Long listId;
    /**
     * 歌单名称
     */
    @ApiField("listName")
    private String listName;
    /**
     * 运营组编号，数组，可能会有多个
     */
    @ApiListField("groupId")
    private List<String> groupId;
    /**
     * 歌单封面地址
     */
    @ApiField("coverUrl")
    private String coverUrl;
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
}

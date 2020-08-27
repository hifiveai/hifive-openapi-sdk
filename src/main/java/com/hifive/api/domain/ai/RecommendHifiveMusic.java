package com.hifive.api.domain.ai;

import com.hifive.api.domain.common.v0.HifiveMusicInfo;
import com.hifive.api.internal.mapping.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecommendHifiveMusic extends HifiveMusicInfo {
    /**
     * 歌曲匹配度
     */
    @ApiField("match")
    private Double match;
}

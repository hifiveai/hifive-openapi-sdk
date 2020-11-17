package com.hifive.api.domain.ai;

import com.hifive.api.domain.common.v0.HFMusicInfo;
import com.hifive.api.internal.mapping.ApiField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class HFRecommendMusic extends HFMusicInfo {
    /**
     * 歌曲匹配度
     */
    @ApiField("match")
    private Double match;
}

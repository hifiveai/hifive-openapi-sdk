package com.hifive.api.domain;

import com.hifive.api.domain.common.base.HFBaseMusicSize;
import com.hifive.api.internal.mapping.ApiField;
import lombok.Data;

/**
 * <p>
 * 企业用户 服务实现类
 * </p>
 *
 * @author yong.huang
 * @Date: 2020/07/01$ $
 */
@Data
public class HFMusicSize extends HFBaseMusicSize {

    /** 歌曲ID **/
    @ApiField("musicId")
    private String musicId;
}

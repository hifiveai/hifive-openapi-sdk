package com.hifive.api.domain;

import com.hifive.api.domain.common.base.HFBaseMusicSize;
import com.hifive.api.internal.mapping.ApiField;
/**
 * <p>
 * 企业用户 服务实现类
 * </p>
 *
 * @author yong.huang
 */
public class HFMusicSize extends HFBaseMusicSize {

    /** 歌曲ID **/
    @ApiField("musicId")
    private String musicId;

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }
}

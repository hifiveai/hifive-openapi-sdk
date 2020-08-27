package com.hifive.api.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hifive.api.domain.common.base.HifiveBaseMusicSize;
import com.hifive.api.internal.mapping.ApiField;
import lombok.Data;

/**
 * <p>
 * 音乐试听实体
 * </p>
 *
 * @author yong.huang
 * @Date: 2020/07/02
 */
@Data
@JsonIgnoreProperties({"coverUrl","waveUrl"})
public class HifiveMusicListenSlice extends HifiveBaseMusicSize {

    /**
     * 歌曲编号
     */
    @ApiField("musicId")
    private String musicId;

    /** "range":bytes=0-29999,文件分片起止字节 **/
    @ApiField("range")
    private String range;
}

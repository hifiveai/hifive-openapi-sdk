package com.hifive.api.domain.common;

import com.hifive.api.domain.common.base.HFBaseDownload;
import com.hifive.api.internal.mapping.ApiField;
import lombok.Data;

/**
 * <p>
 * 下载歌曲
 * </p>
 *
 * @author yong.huang
 * @Date:
 **/
@Data
public class DownloadHF extends HFBaseDownload {

    /** 音乐id **/
    @ApiField("musicId")
    private String musicId;

    /** 过期时间 **/
    @ApiField("fileSize")
    private Long fileSize;
}

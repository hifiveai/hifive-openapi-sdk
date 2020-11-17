package com.hifive.api.domain.common;

import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HFMusicFile extends HFObject {
    @ApiField("musicId")
    private String musicId;
    @ApiField("fileUrl")
    private String fileUrl;
    /**
     * 过期时间戳
     */
    @ApiField("expires")
    private Long expires;

    /**
     * 文件大小
     */
    @ApiField("fileSize")
    private Long fileSize;
}

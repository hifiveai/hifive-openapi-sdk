package com.hifive.api.domain.common.es;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ES中的歌单封面
 */
@AllArgsConstructor
@NoArgsConstructor
public class EsCover implements Serializable {
    /**
     * 封面地址
     */
    @ApiField("url")
    private String url;

    /**
     * 封面尺寸
     */
    @ApiField("size")
    private String size;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

package com.hifive.api.domain.common.es;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Es中的歌词
 */
@AllArgsConstructor
@NoArgsConstructor
public class EsLyric {
    /**
     * 动态歌词
     */
    @ApiField("dynamicUrl")
    private String dynamicUrl;

    /**
     * 静态歌词
     */
    @ApiField("staticUrl")
    private String staticUrl;

    public String getDynamicUrl() {
        return dynamicUrl;
    }

    public void setDynamicUrl(String dynamicUrl) {
        this.dynamicUrl = dynamicUrl;
    }

    public String getStaticUrl() {
        return staticUrl;
    }

    public void setStaticUrl(String staticUrl) {
        this.staticUrl = staticUrl;
    }
}

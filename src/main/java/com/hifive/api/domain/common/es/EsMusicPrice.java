package com.hifive.api.domain.common.es;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class EsMusicPrice {
    /**
     * 歌曲编号
     */
    @ApiField("musicId")
    private String musicId;
    /**
     * 单价 单位 分
     */
    @ApiField("price")
    private Long price;

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}

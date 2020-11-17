package com.hifive.api.domain.common;

import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
@AllArgsConstructor
public class MusicPage extends HFObject {

    @ApiListField("record")
    @ApiField("hifive_music_info_detail")
    private List<HFMusicInfoDetail> record;
    @ApiField("meta")
    private HifiveMeta meta;

    public MusicPage(){
        record = new ArrayList<>();
        meta = new HifiveMeta();
        meta.setCurrentPage(1L);
        meta.setTotalCount(0L);
    }

    public List<HFMusicInfoDetail> getRecord() {
        return record;
    }

    public void setRecord(List<HFMusicInfoDetail> record) {
        this.record = record;
    }

    public HifiveMeta getMeta() {
        return meta;
    }

    public void setMeta(HifiveMeta meta) {
        this.meta = meta;
    }

    public void setMeta(Long total, Long currentPage) {
        if (Objects.isNull(meta)) {
            this.meta = new HifiveMeta();
        }

        if (Objects.isNull(currentPage)) {
            currentPage = 1L;
        }

        meta.setCurrentPage(currentPage);
        meta.setTotalCount(total);
    }
}

package com.hifive.api.domain.common;

import com.hifive.api.HifiveObject;
import com.hifive.api.domain.ai.HifiveRecommendMusicDetail;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
@Data
@AllArgsConstructor
public class RecomandMusicPage extends HifiveObject{

    @ApiListField("record")
    @ApiField("hifive_recommend_music_detail")
    private List<HifiveRecommendMusicDetail> record;
    @ApiField("meta")
    private HifiveMeta meta;

    public RecomandMusicPage(){
        record = new ArrayList<>();
        meta = new HifiveMeta();
        meta.setCurrentPage(1L);
        meta.setTotalCount(0L);
    }

    public List<HifiveRecommendMusicDetail> getRecord() {
        return record;
    }

    public void setRecord(List<HifiveRecommendMusicDetail> record) {
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

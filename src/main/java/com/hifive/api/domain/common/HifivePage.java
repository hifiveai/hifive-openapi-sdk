package com.hifive.api.domain.common;

import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class HifivePage<T> extends HFObject {
    @ApiListField("record")
    private List<T> record;
    @ApiField("meta")
    private HifiveMeta meta;

    public HifivePage(){
        record = new ArrayList<>();
        meta = new HifiveMeta();
        meta.setCurrentPage(1L);
        meta.setTotalCount(0L);
    }

    public List<T> getRecord() {
        return record;
    }

    public void setRecord(List<T> record) {
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

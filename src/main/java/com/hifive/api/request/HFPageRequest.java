package com.hifive.api.request;

import com.hifive.api.HifiveResponse;

public abstract class HFPageRequest<T extends HifiveResponse> extends HFBaseRequest<T> {

    protected Integer page;

    protected Integer pageSize;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

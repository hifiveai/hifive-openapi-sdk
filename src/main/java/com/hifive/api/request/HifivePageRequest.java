package com.hifive.api.request;

import com.hifive.api.HifiveResponse;

public abstract class HifivePageRequest<T extends HifiveResponse> extends HifiveBaseRequest<T> {

    protected Long page;

    protected Long pageSize;

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }
}

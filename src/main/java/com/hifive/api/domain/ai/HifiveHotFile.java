package com.hifive.api.domain.ai;

import com.hifive.api.internal.mapping.ApiField;

import java.io.Serializable;

public class HifiveHotFile implements Serializable {
    @ApiField("id")
    private String id;
    @ApiField("hot")
    private Double hot;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getHot() {
        return hot;
    }

    public void setHot(Double hot) {
        this.hot = hot;
    }
}

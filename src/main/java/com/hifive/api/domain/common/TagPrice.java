package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiListField;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class TagPrice implements Serializable {
    /**
     * 子标签
     */
    @ApiListField("tagList")
    private List<Tag> tagList;

    /**
     * <p>
     * 价格列表
     * </p>
     **/
    @ApiListField("prices")
    private List<Long> prices;

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<Long> getPrices() {
        return prices;
    }

    public void setPrices(List<Long> prices) {
        this.prices = prices;
    }
}

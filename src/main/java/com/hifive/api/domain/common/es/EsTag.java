package com.hifive.api.domain.common.es;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Es 中的标签
 */
@AllArgsConstructor
@NoArgsConstructor
public class EsTag {
    /**
     * id
     */
    @ApiField("id")
    private Long id;

    /**
     * 父级编号
     */
    @ApiField("pid")
    private Long pid;

    /**
     * 图标
     */
    @ApiField("icon")
    private String icon;

    /**
     * 标签名
     */
    @ApiField("name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

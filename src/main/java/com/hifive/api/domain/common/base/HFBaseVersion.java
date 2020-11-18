package com.hifive.api.domain.common.base;


import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;
/**
 * 音乐版本信息
 *
 * @author yong.huang
 * @since 1.0, Sep 13, 2009
 */
public class HFBaseVersion extends HFObject {
    @ApiField("id")
    private Integer id;
    @ApiField("name")
    private String name;
    /**
     * 版本编号
     */
    @ApiField("versionId")
    private String versionId;
    /**
     * 版本名称
     */
    @ApiField("versionName")
    private String versionName;
    /**
     * 是否免费 0 收费  1 免费
     */
    @ApiField("free")
    private Integer free;
    /**
     * 单价 单位 分
     */
    @ApiField("price")
    private Long price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}

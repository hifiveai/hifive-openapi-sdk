package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 艺人
 */
@AllArgsConstructor
@NoArgsConstructor
public class HifiveMusicPerson implements Serializable {
    /**
     * 艺人名
     */
    @ApiField("name")
    private String name;

    /**
     * 编码
     */
    @ApiField("code")
    private String code;

    /**
     * 艺人头像文件
     */
    @ApiField("avatar")
    private String avatar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

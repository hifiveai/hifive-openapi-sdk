package com.hifive.api.domain.common.moji;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class Alert implements Serializable {
    @ApiField("content")
    private String content;
    @ApiField("infoid")
    private Integer infoid;
    @ApiField("level")
    private String level;
    @ApiField("name")
    private String name;
    @ApiField("pub_time")
    @JsonProperty("pub_time")
    private String pubTime;
    @ApiField("title")
    private String title;
    @ApiField("type")
    private String type;
    @ApiField("update_time")
    @JsonProperty("update_time")
    private String updateTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getInfoid() {
        return infoid;
    }

    public void setInfoid(Integer infoid) {
        this.infoid = infoid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
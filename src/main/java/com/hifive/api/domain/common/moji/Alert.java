package com.hifive.api.domain.common.moji;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
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
}
package com.hifive.api.domain.ai;

import com.hifive.api.internal.mapping.ApiField;
import lombok.Data;

import java.io.Serializable;

@Data
public class Response<T> implements Serializable {
    @ApiField("msg")
    private String msg;
    @ApiField("data")
    private T data;
}

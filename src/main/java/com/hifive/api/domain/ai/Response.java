package com.hifive.api.domain.ai;

import com.hifive.api.internal.mapping.ApiField;
import java.io.Serializable;

/**
 * 响应数据
 *
 * @author yong.huang
 * @since 1.0, Sep 13, 2009
 */
public class Response<T> implements Serializable {
    @ApiField("msg")
    private String msg;
    @ApiField("data")
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

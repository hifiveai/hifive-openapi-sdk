package com.hifive.api;


import com.hifive.api.internal.mapping.ApiField;

import java.io.Serializable;
import java.util.Map;

/**
 * TOPAPI基础响应信息。
 *
 * @author fengsheng
 */
public abstract class HifiveResponse implements Serializable {

    private static final long serialVersionUID = 5014379068811962022L;

    @ApiField("code")
    private String code;

    @ApiField("msg")
    private String msg;


    @ApiField("taskId")
    private String taskId;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean isSuccess() {
        return "10200".equals(code);
    }

}

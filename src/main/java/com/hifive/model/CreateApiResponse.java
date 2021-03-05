package com.hifive.model;

import java.io.Serializable;

/**
 * <p>
 * 企业用户 服务实现类
 * </p>
 *
 * @author yong.huang
 */
public class CreateApiResponse implements Serializable {

    /**
     * <p>
     * 响应码
     * </p>
     *
     * @author yong.huang
     **/
    private String code;
    /**
     * <p>
     * 响应消息
     * </p>
     *
     * @author yong.huang
     **/
    private String msg;


    private Object data;


    /**
     * <p>
     * 任务Id
     * </p>
     *
     * @author yong.huang
     **/
    private String taskId;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}

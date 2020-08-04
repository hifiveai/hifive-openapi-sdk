package com.hifive.exception;

import com.hifive.model.constant.ServerEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class ApplicationException extends RuntimeException {

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String msg;

    public ApplicationException(ServerEnum error, String... message) {
        super(error.getMsg());
        this.code = error.getCode();
        if (Objects.isNull(message)) {
            this.msg = error.getMsg();
        } else {
            this.msg = StringUtils.join(error.getMsg(), "-", StringUtils.join(message, "-"));
        }
    }

    public ApplicationException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
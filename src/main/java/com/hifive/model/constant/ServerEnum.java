package com.hifive.model.constant;


public enum ServerEnum {

    STATUS_ERROR(403, "AppId不存在 或 App状态异常"),
    PARAM_MISSING(400, "参数缺失"),
    REPEAT_REQUEST(412, "请不要重复请求接口"),
    EXPIRE_REQUEST(413, "签名已过期"),
    IP_ILLEGAL(402, "ip地址不在白名单内"),
    SIGN_MISMATCH(401, "签名错误"),
    NOT_FOUND(404, "找不到请求记录"),
    ACCESS_DENY(405, "您的服务暂未开通，请检查接口的授权状态"),


    FUNCTION_NOTFOUND(422,"找不到服务模块，请检查url是否正确"),
    FUNCITON_CALLNUM_TOOMORE(423,"已达到今天调用上限"),

    SERVER_ERROR(500, "系统繁忙，请稍后重试"),
    PARAM_ILLEGAL(503, "非法参数"),
    SERVER_BUSY(504,"当前排队人数过多，请稍后再试"),


    ;
    /**
     * 错误码
     */
    public int code;
    /**
     * 消息
     */
    public String msg;

    ServerEnum(int code, String msg) {
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

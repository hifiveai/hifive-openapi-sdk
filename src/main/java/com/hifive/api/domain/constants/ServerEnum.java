package com.hifive.api.domain.constants;

import lombok.Getter;

@Getter
public enum ServerEnum {

    REPEAT_REQUEST(10412, "请不要重复请求接口"),
    EXPIRE_REQUEST_V1(413, "请求已过期"),
    EXPIRE_REQUEST(10413, "请求已过期"),
    IP_ILLEGAL(10414, "ip地址不在白名单内"),
    SIGN_MISMATCH(10415, "签名错误"),
    PARAM_NOT_VALID(10501, "参数验证错误"),
    PARAM_NOT_BIND(10502, "数据绑定错误"),
    PARAM_ILLEGAL(10503, "参数不合法"),
    TRANSFORM_ERROR(10504, "事务提交失败"),
    VERSION_NOT_FOUND(10506, "找不到对应版本"),
    PARAM_LIST_ERROR(10507, "AuditionEnd大于等于auditionBegin，小于等于歌曲时长的整数，单位秒"),

    /**
     * 读取邮件模板出错
     */
    READ_FILE_TO_STRING_ERROR(10901, "读取邮件模板出错"),
    /**
     * 邮箱发送失败
     */
    EMAIL_SEND_FAIL(10809, "邮件发送失败，请核对邮箱地址"),
    MISSING_PARAMETERS(400, "参数缺失或参数错误"),
    SIGN_MISMATCH_V1(401, "签名错误"),
    IP_ILLEGAL_V1(402, "ip地址不在白名单内"),
    COMPANY_NOT_EXISTS(403, "无效的appId"),
    DATA_NO_EXIST(404, "找不到请求记录"),
    ACCESS_DENY(405, "没有权限访问"),
    LOG_OUT_OF_DATE(406, "登录过期,请重新登录"),
    AUTH_OUT_OF_DATE(407, "接口授权已过期"),
    ORDER_OUT_OF_DATE(408, "订单授权已过期"),
    LOCATION_ERROR(414, "位置信息不正确"),
    PRODUCT_NOT_FOUND(415, "产品不存在"),
    ORDER_EXIST(416, "订单号重复"),
    ORDER_NOT_EXIST(417, "订单不存在/状态异常"),
    CAN_CANCEL_ORDER_NOT_EXIST(418, "不存在可取消的订单"),
    CANCEL_ORDER_ERROR(419, "取消订单失败"),
    FOR_MEMBER_OR_BUY(420, "会员专享"),
    HAS_UN_PAY_ORDER(421, "存在未支付订单"),
    MUSIC_DATA_NO_EXIST(422, "歌曲未授权"),
    SERVER_ERROR(500, "系统繁忙，请稍后重试"),
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

}

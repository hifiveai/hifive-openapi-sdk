package com.hifive.api.domain.constants;


/***
 * @author yong.huang
 */
public enum LangageEnum {

    //
    EN(0),
    // 默认
    CN(1);

    /**
     * 音频格式
     */
    Integer value;


    LangageEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

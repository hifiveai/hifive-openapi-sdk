package com.hifive.api.domain.constants;


/***
 * 音频格式，todo:　应该做到系统配置中，而不应该写死，这样不利于扩展
 * @author superchen
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

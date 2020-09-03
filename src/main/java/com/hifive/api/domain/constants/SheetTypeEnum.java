package com.hifive.api.domain.constants;

public enum  SheetTypeEnum {

    //系统歌单
    SYS(0),
    // 自定义歌单
    CUSTOM(1);

    /**
     * 音频格式
     */
    Integer value;


    SheetTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

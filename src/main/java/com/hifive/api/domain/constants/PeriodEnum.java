package com.hifive.api.domain.constants;


/***
 * 音频格式，todo:　应该做到系统配置中，而不应该写死，这样不利于扩展
 * @author superchen
 */
public enum PeriodEnum {

    //半年
    HALF_YEAR("0"),
    // 一年
    ONE_YEAR("1"),
    // 2年
    TOW_YEAR("2"),
    // 3年
    THREE_YEAR("3"),

    FOREVER("4");
    /**
     * 音频格式
     */
    String value;


    PeriodEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

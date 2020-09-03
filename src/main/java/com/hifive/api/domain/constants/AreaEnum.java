package com.hifive.api.domain.constants;


/***
 * 音频格式，todo:　应该做到系统配置中，而不应该写死，这样不利于扩展
 * @author superchen
 */
public enum AreaEnum {

    //中国
    CN("0"),
    // 大中华
    ZH("1"),
    // 全球
    GLOBLE("2");


    /**
     * 音频格式
     */
    String value;


    AreaEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

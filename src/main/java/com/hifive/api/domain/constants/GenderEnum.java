package com.hifive.api.domain.constants;
/***
 * @author yong.huang
 */
public enum GenderEnum {

    UNKNOWN(0),
    MAN(1),
    WOMAN(2);

    Integer value;


    GenderEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

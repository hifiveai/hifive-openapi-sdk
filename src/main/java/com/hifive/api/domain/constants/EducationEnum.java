package com.hifive.api.domain.constants;

/***
 *
 * @author yong.huang
 */
public enum EducationEnum {

    UN_COLLECTED(0), //未采集

    PRIMARY_SCHO0LE(1), //小学

    MIDDLE_SCHOOLE(2),//初中

    HIGH_SCHOOLE(3),//高中

    COLLEGE(4),//大学

    MASTER(5),//硕士
    ;

    Integer value;


    EducationEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

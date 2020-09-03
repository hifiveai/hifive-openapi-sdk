package com.hifive.api.domain.constants;

public enum TagEnum {
    SCENE(1,"场景"),
    MOOD(2,"情绪"),
    OTHER(3,"其他"),
    GENRE(4,"流派"),
    INSTRUMENT(5,"器乐"),
    ;
    private int index;
    private String desc;

    TagEnum(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

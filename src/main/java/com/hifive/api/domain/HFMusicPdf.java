package com.hifive.api.domain;

import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;

/**

 * @author yong.huang
 */
public class HFMusicPdf extends HFObject {
    @ApiField("id")
    private String id;
    @ApiField("name")
    private String name;
    @ApiField("author")
    private String author;
    @ApiField("code")
    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

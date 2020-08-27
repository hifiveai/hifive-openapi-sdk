package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class HifiveMusicPersonData {

    @ApiField("names")
    private String names;
    @ApiListField("person")
    private List<HifiveMusicPerson> person;

    public HifiveMusicPersonData(){
        names = "";
        person = new ArrayList<>();
    }
}

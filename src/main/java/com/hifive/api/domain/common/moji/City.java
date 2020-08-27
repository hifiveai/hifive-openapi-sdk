package com.hifive.api.domain.common.moji;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City implements Serializable {
    private Long cityId;
    private String counname;
    private String ianatimezone;
    private String name;
    private String pname;
    private String secondaryname;
    private String timezone;
}

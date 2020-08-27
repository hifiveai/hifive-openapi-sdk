package com.hifive.api.domain.common.moji;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LiveIndex implements Serializable {
    private Long code;
    private String day;
    private String desc;
    private String level;
    private String name;
    private String status;
    private String updatetime;
}

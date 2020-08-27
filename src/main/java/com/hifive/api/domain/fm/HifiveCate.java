package com.hifive.api.domain.fm;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HifiveCate implements Serializable {
    /**
     * 类别id
     *
     */
    @ApiField("cateId")
    private Long cateId;

    /**
     * 类别名称
     */
    @ApiField("cateName")
    private String cateName;
}

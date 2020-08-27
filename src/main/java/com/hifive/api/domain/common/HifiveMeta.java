package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HifiveMeta implements Serializable {
    @ApiField("totalCount")
    private Long totalCount;
    @ApiField("currentPage")
    private Long currentPage;


}

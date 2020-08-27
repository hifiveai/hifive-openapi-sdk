package com.hifive.api.domain.common.es;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Es中的专辑
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsAlbum {
    @ApiField("id")
    private String id;
    @ApiField("code")
    private String code;
    @ApiField("name")
    private String name;
}

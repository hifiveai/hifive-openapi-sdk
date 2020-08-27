package com.hifive.api.domain.common.es;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Es中的厂商 厂牌
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsSource {
    @ApiField("id")
    private Long id;
    @ApiField("code")
    private String code;
    @ApiField("name")
    private String name;
}

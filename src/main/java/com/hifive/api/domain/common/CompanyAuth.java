package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAuth implements Serializable {
    @ApiField("companyId")
    private Long companyId;
    @ApiField("appId")
    private String appId;
    @ApiField("appSecret")
    private String appSecret;
    @ApiField("secretCode")
    private String secretCode;
    @ApiField("whiteIpConfig")
    private String whiteIpConfig;
}

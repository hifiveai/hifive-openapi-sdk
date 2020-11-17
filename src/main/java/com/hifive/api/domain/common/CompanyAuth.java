package com.hifive.api.domain.common;

import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    public String getWhiteIpConfig() {
        return whiteIpConfig;
    }

    public void setWhiteIpConfig(String whiteIpConfig) {
        this.whiteIpConfig = whiteIpConfig;
    }
}

package com.hifive.api.domain;

import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;


/**
 * 登陆token
 */
public class LoginToken extends HFObject {
    @ApiField("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

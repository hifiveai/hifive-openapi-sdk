package com.hifive.api.response;


import com.hifive.api.HifiveResponse;
import com.hifive.api.domain.LoginToken;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;

/**
 * TOP API: alipay.user.get response.
 *
 * @author auto create
 * @since 1.0, null
 */
public class HifiveUserGetResponse extends HifiveResponse {

    private static final long serialVersionUID = 8416376835315756311L;

    @ApiField("data")
    private LoginToken data;

    public LoginToken getData() {
        return data;
    }

    public void setData(LoginToken data) {
        this.data = data;
    }
}

package com.hifive.api.request;

import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HifiveOrderDetailResponse;
import com.hifive.api.response.HifiveOrderPublishResponse;

import java.util.Map;

public class HifiveOrderDetailRequest extends HifiveBaseRequest<HifiveOrderDetailResponse> {


    /**
     * 订单id
     *
     **/
    private String orderId;



    @Override
    public String getApiMethodName() {
        return "OrderDetail";
    }


    @Override
    public Long getTimestamp() {
        return null;
    }

    @Override
    public void setTimestamp(Long timestamp) {

    }



    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public Map<String, String> getTextParams() {
        HifiveHashMap txtParams = new HifiveHashMap() {
            {
                put("clientId", getClientId());
                put("orderId", getOrderId());
            }
        };
        return txtParams;
    }


    public Class<HifiveOrderDetailResponse> getResponseClass() {
        return HifiveOrderDetailResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }


    @Override
    public void putOtherTextParam(String key, String value) {

    }
}

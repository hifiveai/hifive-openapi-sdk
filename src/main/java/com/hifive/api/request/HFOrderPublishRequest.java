package com.hifive.api.request;

import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HifiveOrderPublishResponse;

import java.util.Map;

public class HFOrderPublishRequest extends HFBaseRequest<HifiveOrderPublishResponse> {


    /**
     * 订单id
     *
     **/
    private String orderId;

    /**
     * workId
     *
     **/
    private String workId;

    @Override
    public String getApiMethodName() {
        return "OrderPublish";
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


    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public Map<String, String> getTextParams() {
        HifiveHashMap txtParams = new HifiveHashMap() {
            {
                put("clientId", getClientId());
                put("orderId", getOrderId());
                put("workId", getWorkId());
            }
        };
        return txtParams;
    }


    public Class<HifiveOrderPublishResponse> getResponseClass() {
        return HifiveOrderPublishResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }


    @Override
    public void putOtherTextParam(String key, String value) {

    }
}

package com.hifive.api.request;

import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HifiveAuthorizationResponse;
import java.util.Map;

public class HFOrderAuthorizationRequest extends HFBaseRequest<HifiveAuthorizationResponse> {


    private String companyName;


    /**
     * 项目名称
     */

    private String projectName;

    /**
     * 项目品牌
     */

    private String brand;

    /**
     * 授权期限（0:半年、1:1年、2:2年、3:3年、4:随片永久)
     */

    private String period;


    /**
     * 授权地区（0:中国、1:大中华、2:全球）
     */
    private String area;

    /**
     * 歌曲ID列表，多个ID用","隔开
     */
    private String orderIds;

    private String musicIds;

    @Override
    public String getApiMethodName() {
        return "OrderAuthorization";
    }


    @Override
    public Long getTimestamp() {
        return null;
    }

    @Override
    public void setTimestamp(Long timestamp) {

    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(String orderIds) {
        this.orderIds = orderIds;
    }

    public String getMusicIds() {
        return musicIds;
    }

    public void setMusicIds(String musicIds) {
        this.musicIds = musicIds;
    }

    public Map<String, String> getTextParams() {
        HifiveHashMap txtParams = new HifiveHashMap() {
            {
                put("companyName", getCompanyName());
                put("projectName", getProjectName());
                put("brand", getBrand());
                put("period", getPeriod());

                put("area", getArea());
                put("orderIds", getOrderIds());
            }
        };
        return txtParams;
    }


    public Class<HifiveAuthorizationResponse> getResponseClass() {
        return HifiveAuthorizationResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }


    @Override
    public void putOtherTextParam(String key, String value) {

    }
}

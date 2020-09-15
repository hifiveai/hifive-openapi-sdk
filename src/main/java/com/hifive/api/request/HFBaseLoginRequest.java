package com.hifive.api.request;


import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HifiveUserGetResponse;

import java.util.Map;

/**
 * TOP API: hifive.user.get request
 *
 * @author auto create
 * @since 1.0, 2013-12-08 16:51:41
 */
public class HFBaseLoginRequest extends HFBaseRequest<HifiveUserGetResponse> {

    private HifiveHashMap udfParams; // add user-defined text parameters
    private Long timestamp;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别，0-未知（默认）1-男2-女
     */
    private int gender;

    /**
     * 10位时间戳，可根据年龄粗略计算
     */
    private int birthday;

    /**
     * 所在城市
     */
    private String location;

    /**
     * 所在省分
     */
    private String province;

    /**
     * 所在国家
     */
    private String country;

    /**
     * 所受教育水平
     */
    private int education;

    /**
     * 职业
     */
    private int profession;

    /**
     * 是否属于组织机构类型用户（to B），默认false
     */
    private boolean isOrganization;

    /**
     * json字符串，保留字段用于保存平台特定用户其他信息
     */
    private String reserve;

    /**
     * 喜欢的歌手名集合
     */
    private String favoriteSinger;

    /**
     * 喜欢的音乐流派集合
     */
    private String favoriteGenre;


    public HifiveHashMap getUdfParams() {
        return udfParams;
    }

    public void setUdfParams(HifiveHashMap udfParams) {
        this.udfParams = udfParams;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public int getProfession() {
        return profession;
    }

    public void setProfession(int profession) {
        this.profession = profession;
    }

    public boolean isOrganization() {
        return isOrganization;
    }

    public void setIsOrganization(boolean organization) {
        isOrganization = organization;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public String getFavoriteSinger() {
        return favoriteSinger;
    }

    public void setFavoriteSinger(String favoriteSinger) {
        this.favoriteSinger = favoriteSinger;
    }

    public String getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(String favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getApiMethodName() {
        return "BaseLogin";
    }

    public Map<String, String> getTextParams() {
        HifiveHashMap txtParams = new HifiveHashMap() {
            {
                put("nickname", getNickname());
                put("gender", getGender());
                put("birthday", getBirthday());
                put("location", getLocation());
                put("education", getEducation());
                put("profession", getProfession());
                put("isOrganization", isOrganization());
                put("favoriteSinger", getFavoriteSinger());
                put("favoriteGenre", getFavoriteGenre());
                put("country", getCountry());
                put("location", getLocation());
                put("province", getProvince());
                put("reserve", getReserve());
            }
        };
        if (this.udfParams != null) {
            txtParams.putAll(this.udfParams);
        }
        return txtParams;
    }




    public void putOtherTextParam(String key, String value) {
        if (this.udfParams == null) {
            this.udfParams = new HifiveHashMap();
        }
        this.udfParams.put(key, value);
    }



    public Class<HifiveUserGetResponse> getResponseClass() {
        return HifiveUserGetResponse.class;
    }

    public void check() throws ApiRuleException {
    }

}

package com.hifive.api;

import com.alibaba.fastjson.JSON;
import com.hifive.api.request.HifiveBehaviorRequest;
import com.hifive.api.request.HifiveHotRequest;
import com.hifive.api.request.HifiveUserGetRequest;
import com.hifive.api.response.HifiveBehaviorResponse;
import com.hifive.api.response.HifiveHotResponse;
import com.hifive.api.response.HifiveUserGetResponse;

public class DefaultClientTest {


    public static void main(String[] args) {
        String url = "https://hifive-gateway-test.hifiveai.com";
        String appkey = "d1cb7d272fe8460c8ad6b114e3977f03";
        String secret = "8b7089e4112f4391";
        System.out.println(System.currentTimeMillis());
        HifiveClient client = new DefaultHifiveClient(url, appkey, secret);
        hotRequest(client);
    }

    private static void UserGetRequest(HifiveClient client) {
        HifiveUserGetRequest request = new HifiveUserGetRequest();
        request.setMethod(HifiveUserGetRequest.METHOD_POST);
        request.setBirthday(202012121);
        request.setCountry("乐山");
        request.setEducation(2);
        request.setGender(1);
        request.setNickname("谎言");
        request.setClientId("1234567");
        request.setProfession(8);
        request.setOrganization(true);
        request.setFavoriteSinger("周杰伦");
        request.setFavoriteGenre("1");
        try {
            HifiveUserGetResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void behaviorRequest(HifiveClient client) {
        HifiveBehaviorRequest request = new HifiveBehaviorRequest();
        request.setMethod(HifiveUserGetRequest.METHOD_POST);
        request.setAction(1009);
        request.setTargetId("B75C80A41E3A");
        request.setClientId("1234567");
        request.setToken("59c0393a44e6642f397bc24f5994e10c");
        try {
            HifiveBehaviorResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hotRequest(HifiveClient client) {
        HifiveHotRequest request = new HifiveHotRequest();
        request.setMethod(HifiveUserGetRequest.METHOD_GET);
        request.setPage(1L);
        request.setPageSize(20L);
        request.setStartTime(1594639058L);
        request.setDuration(183);
        request.setClientId("12345678");
        request.setToken("bf5d538eef34a212924b617fa1869ca3");
        try {
            HifiveHotResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}

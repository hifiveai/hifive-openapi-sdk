package com.hifive.api;

import com.alibaba.fastjson.JSON;
import com.hifive.api.domain.constants.AudioFormatEnum;
import com.hifive.api.domain.constants.LangageEnum;
import com.hifive.api.request.*;
import com.hifive.api.response.*;

public class DefaultClientTest {


    public static void main(String[] args) {
        String url = "https://hifive-openapi-qa.hifiveai.com";
        String appkey = "5216d02806d5464b943492838b7e4390";
        String secret = "2d241e8f934d47d5";
        System.out.println(System.currentTimeMillis());
        HifiveClient client = new DefaultHifiveClient(url, appkey, secret);
        hifiveUserGetRequest(client);
       // HifiveBaseFavoriteRequest(client);
    }

    private static void hifiveUserGetRequest(HifiveClient client) {
        HifiveUserGetRequest request = new HifiveUserGetRequest();
        request.setMethod(HifiveRequest.METHOD_POST);
        request.setBirthday(202012121);
        request.setCountry("乐山");
        request.setEducation(2);
        request.setGender(1);
        request.setNickname("谎言");
        request.setClientId("1223234343");
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

    private static void hifiveBehaviorRequest(HifiveClient client) {
        HifiveBehaviorRequest request = new HifiveBehaviorRequest();
        request.setMethod(HifiveRequest.METHOD_POST);
        request.setAction(1009);
        request.setTargetId("B75C80A41E3A");
        request.setClientId("1234567");
        request.setToken("466e3cb5fc45231eb7f39de739e0d228");
        try {
            HifiveBehaviorResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveHotRequest(HifiveClient client) {
        HifiveHotRequest request = new HifiveHotRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setPage(1L);
        request.setPageSize(20L);
        request.setStartTime(1594639058L);
        request.setDuration(183);
        request.setClientId("12345678");
        request.setToken("466e3cb5fc45231eb7f39de739e0d228");
        try {
            HifiveHotResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveBaseFavoriteRequest(HifiveClient client) {
        HifiveBaseFavoriteRequest request = new HifiveBaseFavoriteRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setPage(1L);
        request.setPageSize(20L);
        request.setClientId("1234567");
        request.setToken("7e348a0da6c133d2c231099dff1c09bd");
        try {
            HifiveBaseFavoriteResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveBaseVisualRequest(HifiveClient client) {
        HifiveBaseVisualRequest request = new HifiveBaseVisualRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setLocation("30.779164,103.94547");
        request.setToken("394427b702825d59222c71d15ceab720");
        try {
            HifiveBaseVisualResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveBaseWeatherRequest(HifiveClient client) {
        HifiveBaseWeatherRequest request = new HifiveBaseWeatherRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setLocation("30.779164,103.94547");
        request.setToken("394427b702825d59222c71d15ceab720");
        try {
            HifiveBaseWeatherResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveMusicConfigRequest(HifiveClient client) {
        HifiveMusicConfigRequest request = new HifiveMusicConfigRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        try {
            HifiveMusicConfigResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveSearchMusicRequest(HifiveClient client) {
        HifiveSearchMusicRequest request = new HifiveSearchMusicRequest();
        request.setMethod(HifiveRequest.METHOD_POST);
        request.setPage(1L);
        request.setPageSize(20L);
        request.setPrice("1-100000");
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        try {
            HifiveSearchMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveSheetTagRequest(HifiveClient client) {
        HifiveSheetTagRequest request = new HifiveSheetTagRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        try {
            HifiveSheetTagResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTagSheetRequest(HifiveClient client) {
        HifiveTagSheetRequest request = new HifiveTagSheetRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        request.setRecoNum(2);
        try {
            HifiveTagSheetResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveSheetMusicRequest(HifiveClient client) {
        HifiveSheetMusicRequest request = new HifiveSheetMusicRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        request.setSheetId(1203L);
        try {
            HifiveSheetMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveAuthorizationRequest(HifiveClient client) {
        HifiveAuthorizationRequest request = new HifiveAuthorizationRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");

        request.setCompanyName("嗨翻屋d1g");
        request.setArea("2");
        request.setBrand("HIFIVE音乐开放平台");
        request.setPeriod("3");
        request.setProjectName("小嗨nbdb");
        request.setOrderIds("hifive1598530734587");
        try {
            HifiveAuthorizationResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveChannelRequest(HifiveClient client) {
        HifiveChannelRequest request = new HifiveChannelRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");

        try {
            HifiveChannelResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveChannelSheetRequest(HifiveClient client) {
        HifiveChannelSheetRequest request = new HifiveChannelSheetRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        request.setGroupId("csa0t86qv24");
        request.setLanguage(LangageEnum.CN.getValue());
        request.setRecoNum(10);
        request.setPage(1L);
        request.setPageSize(10L);

        try {
            HifiveChannelSheetResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveHQListenRequest(HifiveClient client) {
        HifiveHQListenRequest request = new HifiveHQListenRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1223234343");
        request.setMusicId("B7B810AABADF");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);
        request.setTimestamp(1598940639847L);
        request.setNonce("kev6i53p1r0znfuhojl487xtbawqgcsm");
        try {
            HifiveHQListenResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveHQListenSliceRequest(HifiveClient client) {
        HifiveHQListenSliceRequest request = new HifiveHQListenSliceRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1223234343");
        request.setToken("394427b702825d59222c71d15ceab720");

        request.setMusicId("B7B810AABADF");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);
        request.setMixed(true);
        request.setAuditionBegin(2);
        request.setAuditionEnd(100);
        try {
            HifiveHQListenSliceResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveOrderMusicRequest(HifiveClient client) {
        HifiveOrderMusicRequest request = new HifiveOrderMusicRequest();
        request.setMethod(HifiveRequest.METHOD_POST);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        request.setOrderId("1434556569145");
        request.setSubject("nYyple");
        request.setClientId("DXQOY");
        request.setWorkId("uEC00xeWbExGNilHpSN7MoM3AalWqwUp");
        request.setMusic("[{\"versionId\":\"B7B810AABADF\",\"price\":20,\"num\":1}]");
        request.setTotalFee(1556);
        request.setDeadline(50);
        request.setLanguage(LangageEnum.CN.getValue());
        request.setAudioFormat(AudioFormatEnum.AAC_320.format);
        request.setAudioRate(AudioFormatEnum.AAC_320.rate);
        try {
            HifiveOrderMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveOrderPublishRequest(HifiveClient client) {
        HifiveOrderPublishRequest request = new HifiveOrderPublishRequest();
        request.setMethod(HifiveRequest.METHOD_POST);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        request.setOrderId("1434556569145");
        request.setClientId("DXQOY");
        request.setWorkId("uEC00xeWbExGNilHpSN7MoM3AalWqwUp1");
        try {
            HifiveOrderPublishResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveOrderDetailRequest(HifiveClient client) {
        HifiveOrderDetailRequest request = new HifiveOrderDetailRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        request.setOrderId("1434556569145");
        try {
            HifiveOrderDetailResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveOrderRefundRequest(HifiveClient client) {
        HifiveOrderRefundRequest request = new HifiveOrderRefundRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        request.setOrderId("1434556569145");
        try {
            HifiveOrderRefundResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }



    private static void hifiveOrderTagRequest(HifiveClient client) {
        HifiveOrderTagRequest request = new HifiveOrderTagRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        try {
            HifiveOrderTagResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTrafficTagRequest(HifiveClient client) {
        HifiveTrafficTagRequest request = new HifiveTrafficTagRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        try {
            HifiveTrafficTagResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTrafficGroupRequest(HifiveClient client) {
        HifiveTrafficGroupRequest request = new HifiveTrafficGroupRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");

        try {
            HifiveTrafficGroupResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveOrderGroupRequest(HifiveClient client) {
        HifiveOrderGroupRequest request = new HifiveOrderGroupRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");

        try {
            HifiveOrderGroupResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }



    private static void hifiveTrafficTagSheetRequest(HifiveClient client) {
        HifiveTrafficTagSheetRequest request = new HifiveTrafficTagSheetRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        request.setRecoNum(2);
        try {
            HifiveTrafficTagSheetResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveOrderTagSheetRequest(HifiveClient client) {
        HifiveOrderTagSheetRequest request = new HifiveOrderTagSheetRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        request.setRecoNum(2);
        try {
            HifiveOrderTagSheetResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }



    private static void hifiveTrafficGroupSheetRequest(HifiveClient client) {
        HifiveTrafficGroupSheetRequest request = new HifiveTrafficGroupSheetRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        request.setGroupId("csa0t86qv24");
        request.setLanguage(LangageEnum.CN.getValue());
        request.setRecoNum(10);
        request.setPage(1L);
        request.setPageSize(10L);

        try {
            HifiveTrafficGroupSheetResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveOrderGroupSheetRequest(HifiveClient client) {
        HifiveOrderGroupSheetRequest request = new HifiveOrderGroupSheetRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        request.setGroupId("csa0t86qv24");
        request.setLanguage(LangageEnum.CN.getValue());
        request.setRecoNum(10);
        request.setPage(1L);
        request.setPageSize(10L);

        try {
            HifiveOrderGroupSheetResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTrafficSheetMusicRequest(HifiveClient client) {
        HifiveTrafficSheetMusicRequest request = new HifiveTrafficSheetMusicRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        request.setSheetId(1203L);
        try {
            HifiveTrafficSheetMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveOrderSheetMusicRequest(HifiveClient client) {
        HifiveOrderSheetMusicRequest request = new HifiveOrderSheetMusicRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        request.setSheetId(1203L);
        try {
            HifiveOrderSheetMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }




    private static void hifiveOrderListenRequest(HifiveClient client) {
        HifiveOrderListenRequest request = new HifiveOrderListenRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");

        request.setMusicId("B7B810AABADF");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);

        try {
            HifiveOrderListenResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTrafficListenRequest(HifiveClient client) {
        HifiveTrafficListenRequest request = new HifiveTrafficListenRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");

        request.setMusicId("B7B810AABADF");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);

        try {
            HifiveTrafficListenResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }



    private static void hifiveOrderListenSliceRequest(HifiveClient client) {
        HifiveOrderListenSliceRequest request = new HifiveOrderListenSliceRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");

        request.setMusicId("B7B810AABADF");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);
        request.setMixed(true);
        request.setAuditionBegin(2);
        request.setAuditionEnd(100);
        try {
            HifiveOrderListenSliceResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }



    private static void hifiveTrafficListenSliceRequest(HifiveClient client) {
        HifiveTrafficListenSliceRequest request = new HifiveTrafficListenSliceRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");

        request.setMusicId("B7B810AABADF");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);
        request.setMixed(true);
        request.setAuditionBegin(2);
        request.setAuditionEnd(100);
        try {
            HifiveTrafficListenSliceResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }



    private static void hifiveOrderListenMixedRequest(HifiveClient client) {
        HifiveOrderListenMixedRequest request = new HifiveOrderListenMixedRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");

        request.setMusicId("B7B810AABADF");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);

        try {
            HifiveOrderListenMixedResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void TrafficListenMixedRequest(HifiveClient client) {
        HifiveTrafficListenMixedRequest request = new HifiveTrafficListenMixedRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");

        request.setMusicId("B7B810AABADF");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);
        try {
            HifiveTrafficListenMixedResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void OrderSearchMusicRequest(HifiveClient client) {
        HifiveOrderSearchMusicRequest request = new HifiveOrderSearchMusicRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");

        request.setKeyword("a");


        try {
            HifiveOrderSearchMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void TrafficSearchMusicRequest(HifiveClient client) {
        HifiveTrafficSearchMusicRequest request = new HifiveTrafficSearchMusicRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");

        request.setKeyword("a");


        try {
            HifiveTrafficSearchMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void TrafficTagMusicRequest(HifiveClient client) {
        HifiveTrafficTagMusicRequest request = new HifiveTrafficTagMusicRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");

        request.setTagId("5440");


        try {
            HifiveTrafficTagMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTrafficDownloadRequest(HifiveClient client) {
        HifiveTrafficDownloadRequest request = new HifiveTrafficDownloadRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");

        request.setMusicId("B7B810AABADF");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);


        try {
            HifiveTrafficDownloadResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}

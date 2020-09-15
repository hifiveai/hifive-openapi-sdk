package com.hifive.api;

import com.alibaba.fastjson.JSON;
import com.hifive.api.domain.constants.*;
import com.hifive.api.request.*;
import com.hifive.api.response.*;

public class DefaultClientTest {


    public static void main(String[] args) {
        String url = "https://hifive-gateway-test.hifiveai.com";
        String appkey = "25861e5063284e38a40bc960070b34ab";
        String secret = "7a4e2914d1b647b98a";
        System.out.println(System.currentTimeMillis());
        HifiveClient client = new DefaultHifiveClient(url, appkey, secret);
        //hifiveUserGetRequest(client);
        hifiveHQListenRequestTest(client);
    }

    private static void HifiveBaseLoginRequest(HifiveClient client) {
        HifiveBaseLoginRequest request = new HifiveBaseLoginRequest();
        request.setMethod(HifiveRequest.METHOD_POST);
        request.setBirthday(202012121);
        request.setCountry("乐山");
        request.setEducation(EducationEnum.MIDDLE_SCHOOLE.ordinal());
        request.setGender(GenderEnum.MAN.ordinal());
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

    private static void hifiveBaseReportRequest(HifiveClient client) {
        HifiveBaseReportRequest request = new HifiveBaseReportRequest();
        request.setMethod(HifiveRequest.METHOD_POST);
        request.setAction(1009);
        request.setTargetId("B75C80A41E3A");
        request.setClientId("1223234343");
        request.setToken("64f432bb3bf580c9977813d16b9ba76b");
        try {
            HifiveBehaviorResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveBaseHotRequestTest(HifiveClient client) {
        HifiveBaseHotRequest request = new HifiveBaseHotRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setPage(1);
        request.setPageSize(20);
        request.setStartTime(1594639058L);
        request.setDuration(183);
        request.setClientId("1223234343");
        request.setToken("64f432bb3bf580c9977813d16b9ba76b");
        try {
            HifiveHotResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveBaseFavoriteRequestTest(HifiveClient client) {
        HifiveBaseFavoriteRequest request = new HifiveBaseFavoriteRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setPage(1);
        request.setPageSize(20);
        request.setClientId("1223234343");
        request.setToken("64f432bb3bf580c9977813d16b9ba76b");
        try {
            HifiveBaseFavoriteResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveBaseVisualRequestTest(HifiveClient client) {
        HifiveBaseVisualRequest request = new HifiveBaseVisualRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setLocation("30.779164,103.94547");
        request.setToken("64f432bb3bf580c9977813d16b9ba76b");
        try {
            HifiveBaseVisualResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveBaseWeatherRequestTest(HifiveClient client) {
        HifiveBaseWeatherRequest request = new HifiveBaseWeatherRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setLocation("30.779164,103.94547");
        request.setToken("64f432bb3bf580c9977813d16b9ba76b");
        try {
            HifiveBaseWeatherResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveMusicConfigRequestTest(HifiveClient client) {
        HifiveMusicConfigRequest request = new HifiveMusicConfigRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("64f432bb3bf580c9977813d16b9ba76b");
        try {
            HifiveMusicConfigResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveSearchMusicRequestTest(HifiveClient client) {
        HifiveSearchMusicRequest request = new HifiveSearchMusicRequest();
        request.setMethod(HifiveRequest.METHOD_POST);
        request.setPage(1);
        request.setPageSize(20);
        request.setTagIds("123,123");
        request.setPriceFromCent(1L);
        request.setPriceToCent(1000000L);
        request.setBpmFrom(1);
        request.setBpmTo(100);
        request.setClientId("1234567");
        request.setToken("64f432bb3bf580c9977813d16b9ba76b");
        try {
            HifiveSearchMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveSheetTagRequestTest(HifiveClient client) {
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


    private static void hifiveTagSheetRequestTest(HifiveClient client) {
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

    private static void hifiveSheetMusicRequestTest(HifiveClient client) {
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


    private static void HifiveOrderAuthorizationRequestTest(HifiveClient client) {
        HifiveOrderAuthorizationRequest request = new HifiveOrderAuthorizationRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");

        request.setCompanyName("嗨翻屋d1g");
        request.setArea(AreaEnum.GLOBLE.getValue());
        request.setBrand("HIFIVE音乐开放平台");
        request.setPeriod(PeriodEnum.THREE_YEAR.getValue());
        request.setProjectName("小嗨nbdb");
        request.setOrderIds("1434556569145");
        try {
            HifiveAuthorizationResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveChannelRequestTest(HifiveClient client) {
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


    private static void hifiveChannelSheetRequestTest(HifiveClient client) {
        HifiveChannelSheetRequest request = new HifiveChannelSheetRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        request.setGroupId("csa0t86qv24");
        request.setLanguage(LangageEnum.CN.getValue());
        request.setRecoNum(10);
        request.setPage(1);
        request.setPageSize(10);

        try {
            HifiveChannelSheetResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveHQListenRequestTest(HifiveClient client) {
        HifiveHQListenRequest request = new HifiveHQListenRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1223234343");
        request.setMusicId("CF0A80E575FB");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);
        try {
            HifiveHQListenResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveHQListenSliceRequestTest(HifiveClient client) {
        HifiveHQListenSliceRequest request = new HifiveHQListenSliceRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1223234343");
        request.setToken("394427b702825d59222c71d15ceab720");

        request.setMusicId("B7B810AABADF");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);
        request.setIsMixed(true);
        request.setAuditionBegin(2);
        request.setAuditionEnd(100);
        try {
            HifiveHQListenSliceResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveOrderMusicRequestTest(HifiveClient client) {
        HifiveOrderMusicRequest request = new HifiveOrderMusicRequest();
        request.setMethod(HifiveRequest.METHOD_POST);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        request.setOrderId("14345565455656gd");
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


    private static void hifiveOrderPublishRequestTest(HifiveClient client) {
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


    private static void hifiveOrderDetailRequestTest(HifiveClient client) {
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

    private static void hifiveOrderRefundRequestTest(HifiveClient client) {
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


    private static void hifiveOrderTagRequestTest(HifiveClient client) {
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


    private static void hifiveTrafficTagRequestTest(HifiveClient client) {
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


    private static void hifiveTrafficGroupRequestTest(HifiveClient client) {
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

    private static void hifiveOrderGroupRequestTest(HifiveClient client) {
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


    private static void hifiveTrafficTagSheetRequestTest(HifiveClient client) {
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


    private static void hifiveOrderTagSheetRequestTest(HifiveClient client) {
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


    private static void hifiveTrafficGroupSheetRequestTest(HifiveClient client) {
        HifiveTrafficGroupSheetRequest request = new HifiveTrafficGroupSheetRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        request.setGroupId("csa0t86qv24");
        request.setLanguage(LangageEnum.CN.getValue());
        request.setRecoNum(10);
        request.setPage(1);
        request.setPageSize(10);

        try {
            HifiveTrafficGroupSheetResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveOrderGroupSheetRequestTest(HifiveClient client) {
        HifiveOrderGroupSheetRequest request = new HifiveOrderGroupSheetRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");
        request.setGroupId("csa0t86qv24");
        request.setLanguage(LangageEnum.CN.getValue());
        request.setRecoNum(10);
        request.setPage(2);
        request.setPageSize(10);

        try {
            HifiveOrderGroupSheetResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTrafficSheetMusicRequestTest(HifiveClient client) {
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

    private static void hifiveOrderSheetMusicRequestTest(HifiveClient client) {
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


    private static void hifiveOrderListenRequestTest(HifiveClient client) {
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


    private static void hifiveTrafficListenRequestTest(HifiveClient client) {
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


    private static void hifiveOrderListenSliceRequestTest(HifiveClient client) {
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


    private static void hifiveTrafficListenSliceRequestTest(HifiveClient client) {
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


    private static void hifiveOrderListenMixedRequestTest(HifiveClient client) {
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


    private static void hifiveTrafficListenMixedRequestTest(HifiveClient client) {
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


    private static void hifiveOrderSearchMusicRequestTest(HifiveClient client) {
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


    private static void hifiveTrafficSearchMusicRequestTest(HifiveClient client) {
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

    private static void hifiveTrafficTagMusicRequestTest(HifiveClient client) {
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


    private static void hifiveOrderTagMusicRequestTest(HifiveClient client) {
        HifiveOrderTagMusicRequest request = new HifiveOrderTagMusicRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setToken("394427b702825d59222c71d15ceab720");

        request.setTagId("5440");


        try {
            HifiveOrderTagMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTrafficDownloadRequestTest(HifiveClient client) {
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

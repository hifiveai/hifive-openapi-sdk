package com.hifive.api;

import com.alibaba.fastjson.JSON;
import com.hifive.api.domain.constants.*;
import com.hifive.api.request.*;
import com.hifive.api.response.*;

public class DefaultClientTest {


    public static void main(String[] args) {
        String url = "https://hifive-openapi-qa.hifiveai.com";
        String appkey = "5216d02806d5464b943492838b7e4390";
        String secret = "2d241e8f934d47d5";
        System.out.println(System.currentTimeMillis());
        HifiveClient client = new DefaultHifiveClient(url, appkey, secret);
        //HifiveBaseLoginRequest(client);
        hifiveOrderDetailRequestTest(client);
    }

    private static void HifiveBaseLoginRequest(HifiveClient client) {
        HFBaseLoginRequest request = new HFBaseLoginRequest();
        request.setMethod(HifiveRequest.METHOD_POST);
        request.setBirthday(202012121);
        request.setCountry("乐山");
        request.setEducation(EducationEnum.MIDDLE_SCHOOLE.ordinal());
        request.setGender(GenderEnum.MAN.ordinal());
        request.setNickname("谎言");
        request.setClientId("1223234343");
        request.setProfession(8);
        request.setIsOrganization(true);
        request.setFavoriteSinger("周杰伦");
        request.setFavoriteGenre("1");
        try {
            HFUserGetResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveBaseReportRequest(HifiveClient client) {
        HFBaseReportRequest request = new HFBaseReportRequest();
        request.setMethod(HifiveRequest.METHOD_POST);
        request.setAction(1009);
        request.setTargetId("B75C80A41E3A");
        request.setClientId("1223234343");
        request.setToken("a0ef39d567c08eadb3da1f1b60728e67");
        try {
            HFBehaviorResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveBaseHotRequestTest(HifiveClient client) {
        HFBaseHotRequest request = new HFBaseHotRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setPage(1);
        request.setPageSize(20);
        request.setStartTime(1594639058L);
        request.setDuration(183);
        request.setClientId("1223234343");
        request.setToken("a0ef39d567c08eadb3da1f1b60728e67");
        try {
            HFHotResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveBaseFavoriteRequestTest(HifiveClient client) {
        HFBaseFavoriteRequest request = new HFBaseFavoriteRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setPage(1);
        request.setPageSize(20);
        request.setClientId("1223234343");
        request.setToken("4f6d6841972afd2ea9ccebad56143ac8");
        try {
            HFBaseFavoriteResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveBaseVisualRequestTest(HifiveClient client) {
        HFBaseVisualRequest request = new HFBaseVisualRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setLocation("30.779164,103.94547");
        request.setToken("4f6d6841972afd2ea9ccebad56143ac8");
        try {
            HFBaseVisualResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveBaseWeatherRequestTest(HifiveClient client) {
        HFBaseWeatherRequest request = new HFBaseWeatherRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setLocation("30.779164,103.94547");
        request.setToken("hifiveBaseWeatherRequestTest");
        try {
            HFBaseWeatherResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveMusicConfigRequestTest(HifiveClient client) {
        HFMusicConfigRequest request = new HFMusicConfigRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        try {
            HFMusicConfigResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveSearchMusicRequestTest(HifiveClient client) {
        HFSearchMusicRequest request = new HFSearchMusicRequest();
        request.setMethod(HifiveRequest.METHOD_POST);
        request.setPage(1);
        request.setPageSize(20);
        request.setTagIds("123,123");
        request.setPriceFromCent(1L);
        request.setPriceToCent(1000000L);
        request.setBpmFrom(1);
        request.setBpmTo(100);
        request.setClientId("1234567");
        try {
            HFSearchMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveSheetTagRequestTest(HifiveClient client) {
        HFSheetTagRequest request = new HFSheetTagRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        try {
            HFSheetTagResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTagSheetRequestTest(HifiveClient client) {
        HFTagSheetRequest request = new HFTagSheetRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setRecoNum(2);
        try {
            HFTagSheetResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveSheetMusicRequestTest(HifiveClient client) {
        HFSheetMusicRequest request = new HFSheetMusicRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setSheetId(1203L);
        try {
            HFSheetMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void HifiveOrderAuthorizationRequestTest(HifiveClient client) {
        HFOrderAuthorizationRequest request = new HFOrderAuthorizationRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");

        request.setCompanyName("嗨翻屋d1g");
        request.setArea(AreaEnum.GLOBLE.getValue());
        request.setBrand("HIFIVE音乐开放平台");
        request.setPeriod(PeriodEnum.THREE_YEAR.getValue());
        request.setProjectName("小嗨nbdb");
        request.setOrderIds("1434556569145");
        try {
            HFAuthorizationResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveChannelRequestTest(HifiveClient client) {
        HFChannelRequest request = new HFChannelRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");

        try {
            HFChannelResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveChannelSheetRequestTest(HifiveClient client) {
        HFChannelSheetRequest request = new HFChannelSheetRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setGroupId("csa0t86qv24");
        request.setLanguage(LangageEnum.CN.getValue());
        request.setRecoNum(10);
        request.setPage(1);
        request.setPageSize(10);

        try {
            HFChannelSheetResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveHQListenRequestTest(HifiveClient client) {
        HFHQListenRequest request = new HFHQListenRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1223234343");
        request.setMusicId("CF0A80E575FB");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);
        try {
            HFHQListenResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveHQListenSliceRequestTest(HifiveClient client) {
        HFHQListenSliceRequest request = new HFHQListenSliceRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1223234343");

        request.setMusicId("B7B810AABADF");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);
        request.setIsMixed(true);
        request.setAuditionBegin(2);
        request.setAuditionEnd(100);
        try {
            HFHQListenSliceResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveOrderMusicRequestTest(HifiveClient client) {
        HFOrderMusicRequest request = new HFOrderMusicRequest();
        request.setMethod(HifiveRequest.METHOD_POST);
        request.setClientId("1234567");
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
            HFOrderMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveOrderPublishRequestTest(HifiveClient client) {
        HFOrderPublishRequest request = new HFOrderPublishRequest();
        request.setMethod(HifiveRequest.METHOD_POST);
        request.setClientId("1234567");
        request.setOrderId("1434556569145");
        request.setClientId("DXQOY");
        request.setWorkId("uEC00xeWbExGNilHpSN7MoM3AalWqwUp1");
        try {
            HFOrderPublishResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveOrderDetailRequestTest(HifiveClient client) {
        HFOrderDetailRequest request = new HFOrderDetailRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setOrderId("1434556569145");
        try {
            HFOrderDetailResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveOrderRefundRequestTest(HifiveClient client) {
        HFOrderRefundRequest request = new HFOrderRefundRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setOrderId("1434556569145");
        try {
            HFOrderRefundResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveOrderTagRequestTest(HifiveClient client) {
        HFOrderTagRequest request = new HFOrderTagRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        try {
            HFOrderTagResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTrafficTagRequestTest(HifiveClient client) {
        HFTrafficTagRequest request = new HFTrafficTagRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        try {
            HFTrafficTagResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTrafficGroupRequestTest(HifiveClient client) {
        HFTrafficGroupRequest request = new HFTrafficGroupRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");

        try {
            HFTrafficGroupResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveOrderGroupRequestTest(HifiveClient client) {
        HFOrderGroupRequest request = new HFOrderGroupRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");

        try {
            HFOrderGroupResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTrafficTagSheetRequestTest(HifiveClient client) {
        HFTrafficTagSheetRequest request = new HFTrafficTagSheetRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setRecoNum(2);
        try {
            HFTrafficTagSheetResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveOrderTagSheetRequestTest(HifiveClient client) {
        HFOrderTagSheetRequest request = new HFOrderTagSheetRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setRecoNum(2);
        try {
            HFOrderTagSheetResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTrafficGroupSheetRequestTest(HifiveClient client) {
        HFTrafficGroupSheetRequest request = new HFTrafficGroupSheetRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setGroupId("csa0t86qv24");
        request.setLanguage(LangageEnum.CN.getValue());
        request.setRecoNum(10);
        request.setPage(1);
        request.setPageSize(10);

        try {
            HFTrafficGroupSheetResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveOrderGroupSheetRequestTest(HifiveClient client) {
        HFOrderGroupSheetRequest request = new HFOrderGroupSheetRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setGroupId("csa0t86qv24");
        request.setLanguage(LangageEnum.CN.getValue());
        request.setRecoNum(10);
        request.setPage(2);
        request.setPageSize(10);

        try {
            HFOrderGroupSheetResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTrafficSheetMusicRequestTest(HifiveClient client) {
        HFTrafficSheetMusicRequest request = new HFTrafficSheetMusicRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setSheetId(1203L);
        try {
            HFTrafficSheetMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveOrderSheetMusicRequestTest(HifiveClient client) {
        HFOrderSheetMusicRequest request = new HFOrderSheetMusicRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");
        request.setSheetId(1203L);
        try {
            HFOrderSheetMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveOrderListenRequestTest(HifiveClient client) {
        HFOrderListenRequest request = new HFOrderListenRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");

        request.setMusicId("B7B810AABADF");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);

        try {
            HFOrderListenResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTrafficListenRequestTest(HifiveClient client) {
        HFTrafficListenRequest request = new HFTrafficListenRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");

        request.setMusicId("B7B810AABADF");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);

        try {
            HFTrafficListenResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveOrderListenSliceRequestTest(HifiveClient client) {
        HFOrderListenSliceRequest request = new HFOrderListenSliceRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");

        request.setMusicId("B7B810AABADF");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);
        request.setMixed(true);
        request.setAuditionBegin(2);
        request.setAuditionEnd(100);
        try {
            HFOrderListenSliceResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTrafficListenSliceRequestTest(HifiveClient client) {
        HFTrafficListenSliceRequest request = new HFTrafficListenSliceRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");

        request.setMusicId("B7B810AABADF");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);
        request.setMixed(true);
        request.setAuditionBegin(2);
        request.setAuditionEnd(100);
        try {
            HFTrafficListenSliceResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveOrderListenMixedRequestTest(HifiveClient client) {
        HFOrderListenMixedRequest request = new HFOrderListenMixedRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");

        request.setMusicId("B7B810AABADF");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);

        try {
            HFOrderListenMixedResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTrafficListenMixedRequestTest(HifiveClient client) {
        HFTrafficListenMixedRequest request = new HFTrafficListenMixedRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");

        request.setMusicId("B7B810AABADF");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);
        try {
            HFTrafficListenMixedResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveOrderSearchMusicRequestTest(HifiveClient client) {
        HFOrderSearchMusicRequest request = new HFOrderSearchMusicRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");

        request.setKeyword("a");


        try {
            HFOrderSearchMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTrafficSearchMusicRequestTest(HifiveClient client) {
        HFTrafficSearchMusicRequest request = new HFTrafficSearchMusicRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");

        request.setKeyword("a");


        try {
            HFTrafficSearchMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void hifiveTrafficTagMusicRequestTest(HifiveClient client) {
        HFTrafficTagMusicRequest request = new HFTrafficTagMusicRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");

        request.setTagId("5440");


        try {
            HFTrafficTagMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveOrderTagMusicRequestTest(HifiveClient client) {
        HFOrderTagMusicRequest request = new HFOrderTagMusicRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");

        request.setTagId("5440");


        try {
            HFOrderTagMusicResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void hifiveTrafficDownloadRequestTest(HifiveClient client) {
        HFTrafficDownloadRequest request = new HFTrafficDownloadRequest();
        request.setMethod(HifiveRequest.METHOD_GET);
        request.setClientId("1234567");

        request.setMusicId("B7B810AABADF");
        request.setAudioFormat(AudioFormatEnum.MP3_128.format);
        request.setAudioRate(AudioFormatEnum.MP3_128.rate);


        try {
            HFTrafficDownloadResponse response = client.execute(request);
            System.out.println(JSON.toJSON(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}

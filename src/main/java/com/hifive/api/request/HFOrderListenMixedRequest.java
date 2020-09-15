package com.hifive.api.request;

import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HifiveOrderListenMixedResponse;

import java.util.Map;

public class HFOrderListenMixedRequest extends HFBaseRequest<HifiveOrderListenMixedResponse> {


    /**
     * 音乐id
     **/
    private String musicId;

    /**
     * 音频格式 文件编码,默认mp3 可选(mp3 / aac)
     */
    private String audioFormat;

    /**
     * 音频码率 音质，音乐播放时的比特率，默认320 可选(320 / 128)
     */
    private String audioRate;

    @Override
    public String getApiMethodName() {
        return "OrderListenMixed";
    }


    @Override
    public Long getTimestamp() {
        return null;
    }

    @Override
    public void setTimestamp(Long timestamp) {

    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }



    public String getAudioFormat() {
        return audioFormat;
    }

    public void setAudioFormat(String audioFormat) {
        this.audioFormat = audioFormat;
    }

    public String getAudioRate() {
        return audioRate;
    }

    public void setAudioRate(String audioRate) {
        this.audioRate = audioRate;
    }


    public Map<String, String> getTextParams() {
        HifiveHashMap txtParams = new HifiveHashMap() {
            {
                put("musicId", getMusicId());
                put("audioFormat", getAudioFormat());
                put("audioRate", getAudioRate());
            }
        };
        return txtParams;
    }


    public Class<HifiveOrderListenMixedResponse> getResponseClass() {
        return HifiveOrderListenMixedResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }


    @Override
    public void putOtherTextParam(String key, String value) {

    }
}

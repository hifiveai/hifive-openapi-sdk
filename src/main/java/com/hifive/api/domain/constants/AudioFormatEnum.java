package com.hifive.api.domain.constants;



/***
 * 音频格式，todo:　应该做到系统配置中，而不应该写死，这样不利于扩展
 * @author superchen
 */
public enum AudioFormatEnum {

    //
    WAV_320("wav", "320"),
    // 默认
    DEFAULT("mp3", "320"),
    MP3_128("mp3", "128"),
    AAC_320("aac", "320");

    /**
     * 音频格式
     */
    public String format;

    /**
     * 音频码率
     */
    public String rate;

    AudioFormatEnum(String format, String rate) {
        this.format = format;
        this.rate = rate;
    }

    public static AudioFormatEnum get(String format, String rate) {
        if (format == null || rate == null) {
            return AudioFormatEnum.DEFAULT;
        }

        String normalFormat = format.toLowerCase().trim();
        String normalRate = rate.toLowerCase().trim();
        for (AudioFormatEnum audioFormatEnum : AudioFormatEnum.values()) {
            if (audioFormatEnum.format.equals(normalFormat) && audioFormatEnum.rate.equals(normalRate)) {
                return audioFormatEnum;
            }
        }


        return AudioFormatEnum.DEFAULT;
    }

    public static AudioFormatEnum get(String quality){
        switch (quality){
            case "normal":
                return AudioFormatEnum.MP3_128;
            case "high":
                return AudioFormatEnum.DEFAULT;
            default:
                return AudioFormatEnum.DEFAULT;
        }
    }
}

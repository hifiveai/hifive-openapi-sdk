package com.hifive.api.request;

import com.hifive.api.ApiRuleException;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.response.HifiveBehaviorResponse;
import java.util.Map;

public class HifiveBehaviorRequest extends HifiveBaseRequest<HifiveBehaviorResponse> {

    /**
     * 描述⽤户⾏为
     */
    private Integer action;

    /**
     * ⾏为操作的对象歌曲或频道id
     */
    private String targetId;

    /**
     * 产⽣⾏为的秒级时间戳
     */
    private Long timestamp;

    /**
     * ⾏为内容
     */
    private String content;

    /**
     * ⾏为上下⽂
     * 可空，可传⼊GPS经纬度，纬度在前若南纬⻄经值前加-，如：23.03,113.75 或 -23.03,113.75
     */
    private String context;

    @Override
    public String getApiMethodName() {
        return "BaseReport";
    }


    @Override
    public Long getTimestamp() {
        return null;
    }

    @Override
    public void setTimestamp(Long timestamp) {

    }


    public Map<String, String> getTextParams() {
        HifiveHashMap txtParams = new HifiveHashMap() {
            {
                put("Action", "1009");
                put("TargetId", "B75C80A41E3A");
            }
        };
        return txtParams;
    }


    public Class<HifiveBehaviorResponse> getResponseClass() {
        return HifiveBehaviorResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {

    }


    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public void putOtherTextParam(String key, String value) {

    }
}

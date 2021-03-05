package com.hifive.api;

/**
 * TOP响应解释器接口。响应格式可以是JSON, XML等等。
 *
 * @author carver.gu
 * @since 1.0, Apr 11, 2010
 */
public interface HFParser<T extends HFResponse> {

    /**
     * 把响应字符串解释成相应的领域对象。
     *
     * @param rsp 响应字符串
     * @return 返回结果
     * @throws HFApiException 请求异常
     */
    public T parse(String rsp) throws ApiException;

    /**
     * 获取响应类类型。
     * @return 返回结果
     * @throws HFApiException 请求异常
     */
    public Class<T> getResponseClass() throws ApiException;

}

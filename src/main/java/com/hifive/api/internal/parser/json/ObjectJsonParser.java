package com.hifive.api.internal.parser.json;


import com.hifive.api.ApiException;
import com.hifive.api.HFParser;
import com.hifive.api.HFResponse;
import com.hifive.api.internal.mapping.Converter;

/**
 * 单个JSON对象解释器。
 * 
 * @author carver.gu
 * @since 1.0, Apr 11, 2010
 */
public class ObjectJsonParser<T extends HFResponse> implements HFParser<T> {

	private Class<T> clazz;

	public ObjectJsonParser(Class<T> clazz) {
		this.clazz = clazz;
	}

	public T parse(String rsp) throws ApiException {
		Converter converter = new JsonConverter();
		return converter.toResponse(rsp, clazz);
	}

	public Class<T> getResponseClass() {
		return clazz;
	}

}

package com.hifive.api.internal.parser.xml;


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
public class ObjectXmlParser<T extends HFResponse> implements HFParser<T> {

	private Class<T> clazz;

	public ObjectXmlParser(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public T parse(String rsp) throws ApiException {
		Converter converter = new XmlConverter();
		return converter.toResponse(rsp, clazz);
	}

	@Override
	public Class<T> getResponseClass() {
		return clazz;
	}

}

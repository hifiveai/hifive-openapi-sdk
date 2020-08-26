package com.hifive.api.internal.translate;



import com.hifive.api.ApiRuleException;
import com.hifive.api.Constants;
import com.hifive.api.HifiveRequest;
import com.hifive.api.HifiveResponse;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.internal.util.RequestCheckUtils;

import java.util.Map;

public class TqlRequest<T extends HifiveResponse> implements HifiveRequest<T> {

	private HifiveHashMap udfParams;
	private Long timestamp;
	private String tql;
	private Class<T> rspClass;
	private Map<String,String> headerMap=new HifiveHashMap();
	public TqlRequest(Class<T> rspClass) {
		this.rspClass = rspClass;
	}

	public String getTql() {
		return tql;
	}

	public void setTql(String tql) {
		this.tql = tql;
	}

	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getApiMethodName() {
		return null;
	}

	public Map<String, String> getTextParams() {
		HifiveHashMap txtParams = new HifiveHashMap();
		txtParams.put("ql", this.tql);
		txtParams.put(Constants.TQL_SEPERATOR, true);
		if (this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public void putOtherTextParam(String key, String value) {
		if (this.udfParams == null) {
			this.udfParams = new HifiveHashMap();
		}
		this.udfParams.put(key, value);
	}

	@Override
	public String getMethod() {
		return null;
	}

	@Override
	public String getClientId() {
		return null;
	}

	@Override
	public String getVersion() {
		return null;
	}

	@Override
	public String getNonce() {
		return null;
	}

	@Override
	public String getAuthorization() {
		return null;
	}

	public Class<T> getResponseClass() {
		return rspClass;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(tql, "ql");
	}

	public Map<String,String> getHeaderMap() {
		return headerMap;
	}
}

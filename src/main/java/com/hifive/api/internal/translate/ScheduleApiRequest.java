package com.hifive.api.internal.translate;



import com.hifive.api.ApiRuleException;
import com.hifive.api.Constants;
import com.hifive.api.HFResponse;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.HFRequest;
import com.hifive.api.internal.util.RequestCheckUtils;

import java.util.Date;
import java.util.Map;

public class ScheduleApiRequest implements HFRequest<ScheduleApiResponse> {

	private HifiveHashMap udfParams;
	private Long timestamp;
	private String tql;
	private Date schedule;
	private HFRequest<? extends HFResponse> HifiveRequest;
	private Map<String,String> headerMap=new HifiveHashMap();
	
	
	public void setTql(String tql) {
		this.tql = tql;
	}

	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}

	public void setHifiveRequest(HFRequest<? extends HFResponse> HFRequest) throws ApiRuleException {
		this.HifiveRequest = HFRequest;
		HFRequest.check();
	}

	public String getApiMethodName() {
		return null;
	}

	public Map<String, String> getTextParams() {
		HifiveHashMap txtParams = new HifiveHashMap();
		txtParams.put("isTql", false);
		txtParams.put("schedule", schedule);
		if (HifiveRequest != null) {
			if (HifiveRequest instanceof TqlRequest) {
				txtParams.put("isTql", true);
				txtParams.put(Constants.TQL_SEPERATOR, true);
			} else {
				txtParams.put("method", HifiveRequest.getApiMethodName());
			}
			txtParams.putAll(HifiveRequest.getTextParams());
		} else if (null != this.tql) {
			txtParams.put("ql", this.tql);
			txtParams.put(Constants.TQL_SEPERATOR, true);
			txtParams.put("isTql", true);
		}
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

	@Override
	public String getToken() {
		return null;
	}

	public Class<ScheduleApiResponse> getResponseClass() {
		return ScheduleApiResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(schedule, "schedule");
	}

	public Map<String,String> getHeaderMap() {
		return headerMap;
	}
}

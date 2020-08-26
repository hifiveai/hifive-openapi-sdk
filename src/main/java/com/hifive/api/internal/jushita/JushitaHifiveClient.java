package com.hifive.api.internal.jushita;



import com.hifive.api.*;
import com.hifive.api.internal.util.HifiveHashMap;

import java.util.Map;

/**
 * 聚石塔专用TOP客户端。
 * 
 * @author fengsheng
 * @since 1.0, May 22, 2012
 */
public class JushitaHifiveClient {

	private static final String SYNC_CENTER_URL = "http://eai.tmall.com/api";

	private DefaultHifiveClient client;

	public JushitaHifiveClient(String serverUrl, String appKey, String appSecret) {
		this.client = new DefaultHifiveClient(serverUrl, appKey, appSecret);
	}

	public JushitaHifiveClient(String appKey, String appSecret) {
		this(SYNC_CENTER_URL, appKey, appSecret);
	}

	public JushitaHifiveClient(String serverUrl, String appKey, String appSecret, int connectTimeout, int readTimeout) {
		this.client = new DefaultHifiveClient(serverUrl, appKey, appSecret, null, connectTimeout, readTimeout);
	}

	public JushitaHifiveClient(String serverUrl, String appKey, String appSecret,String format ,int connectTimeout, int readTimeout) {
		this.client = new DefaultHifiveClient(serverUrl, appKey, appSecret, format, connectTimeout, readTimeout);
	}
	
	public JushitaHifiveClient(String serverUrl, String appKey, String appSecret,String format ,int connectTimeout, int readTimeout,String signMethod) {
		this.client = new DefaultHifiveClient(serverUrl, appKey, appSecret, format, connectTimeout, readTimeout,signMethod);
	}
	
	public void execute(String apiName, Map<String, String> params, String session) throws ApiException {
		this.client.setNeedCheckRequest(false);
		this.client.setNeedEnableParser(false);
		JushitaRequest<JushitaResponse> request = new JushitaRequest<JushitaResponse>(apiName, params, JushitaResponse.class);
		JushitaResponse response = this.client.execute(request, session);
		return ;
	}

	public <T extends HifiveResponse> T execute(HifiveRequest<T> request, String session) throws ApiException {
		this.client.setNeedCheckRequest(true);
		this.client.setNeedEnableParser(true);
		return client.execute(request, session);
	}
	
	public <T extends HifiveResponse> T execute(JushitaRequest<T> request, String session) throws ApiException {
		this.client.setNeedCheckRequest(true);
		this.client.setNeedEnableParser(true);
		return client.execute(request, session);
	}
	
	public static class JushitaRequest<T extends HifiveResponse> implements HifiveRequest<T> {
		private String apiName;
		private Map<String, String> params;
		private Class<T> clazz;
		private Map<String,String> headerMap=new HifiveHashMap();
		public JushitaRequest(String apiName, Map<String, String> params ) {
			this(apiName, params, null);
		}
		
		public JushitaRequest(String apiName, Map<String, String> params, Class<T> clazz) {
			this.apiName = apiName;
			this.params = params;
			this.clazz = clazz;
		}

		public String getApiMethodName() {
			return this.apiName;
		}

		public Map<String, String> getTextParams() {
			return this.params;
		}

		public Long getTimestamp() {
			return System.currentTimeMillis();
		}

		public void setTimestamp(Long timestamp) {
		}

	 
		public void check() throws ApiRuleException {
		}

		public Map<String,String> getHeaderMap() {
			return headerMap;
		}

		public Class<T> getResponseClass() {
			return clazz;
		}

		public void putOtherTextParam(String key, String value) {
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
	}

	public static class JushitaResponse extends HifiveResponse {
		private static final long serialVersionUID = -4167445591102791347L;
	}

}

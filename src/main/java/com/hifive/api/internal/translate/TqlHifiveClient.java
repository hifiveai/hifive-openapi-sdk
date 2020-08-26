package com.hifive.api.internal.translate;



import com.hifive.api.*;
import com.hifive.api.internal.parser.json.ObjectJsonParser;
import com.hifive.api.internal.parser.xml.ObjectXmlParser;
import com.hifive.api.internal.util.HifiveHashMap;
import com.hifive.api.internal.util.HifiveLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 基于Tql的TOP客户端。
 */
public class TqlHifiveClient {

	private final static Pattern CRLF_SEPARTOR = Pattern.compile("\r\n");

	private DefaultHifiveClient client;
	private String appSecret;
	private String format = Constants.FORMAT_JSON;
	private boolean needCheckRequest = true;
	private boolean needEnableParser = true;

	public TqlHifiveClient(String serverUrl, String appKey, String appSecret) {
		this.appSecret = appSecret;
		this.client = new DefaultHifiveClient(serverUrl, appKey, appSecret);
	}

	public TqlHifiveClient(String serverUrl, String appKey, String appSecret, String format) {
		this.format = format;
		this.appSecret = appSecret;
		this.client = new DefaultHifiveClient(serverUrl, appKey, appSecret, format);
	}

	public TqlHifiveClient(String serverUrl, String appKey, String appSecret, String format, int connectTimeout, int readTimeout) {
		this.format = format;
		this.appSecret = appSecret;
		this.client = new DefaultHifiveClient(serverUrl, appKey, appSecret, format, connectTimeout, readTimeout);
	}

	public TqlHifiveClient(String serverUrl, String appKey, String appSecret, String format, int connectTimeout, int readTimeout, String signMethod) {
		this.format = format;
		this.appSecret = appSecret;
		this.client = new DefaultHifiveClient(serverUrl, appKey, appSecret, format, connectTimeout, readTimeout, signMethod);
	}

	public <T extends HifiveResponse> List<T> execute(HifiveRequest<T> request) throws ApiException {
		return execute(request, null);
	}

	public <T extends HifiveResponse> List<T> execute(HifiveRequest<T> request, String session) throws ApiException {
		List<T> results = new ArrayList<T>();
		HifiveParser<T> parser = null;
		boolean needParser = this.needEnableParser;
		if (needParser && request.getResponseClass().equals(TqlResponse.class)) {
			needParser = false;
		}
		if (needParser) {
			if (Constants.FORMAT_XML.equals(this.format)) {
				parser = new ObjectXmlParser<T>(request.getResponseClass());
			} else {
				parser = new ObjectJsonParser<T>(request.getResponseClass());
			}
		}
		if (this.needCheckRequest) {
			try {
				request.check();// if check failed,will throw ApiRuleException.
			} catch (ApiRuleException e) {
				T localResponse = null;
				try {
					localResponse = request.getResponseClass().newInstance();
				} catch (InstantiationException e2) {
					throw new ApiException(e2);
				} catch (IllegalAccessException e3) {
					throw new ApiException(e3);
				}
				localResponse.setCode(e.getErrCode());
				localResponse.setMsg(e.getErrMsg());
				results.add(localResponse);
				return results;
			}
		}

		Map<String, Object> rt = client.doExcute(request, session);
		if (rt == null)
			return null;

		T tRsp = null;
		try {
			String rsp = (String) rt.get("rsp");
			String[] lines = CRLF_SEPARTOR.split(rsp);
			for (int i = 0; i < lines.length; i++) {
				if (needParser) {
					tRsp = parser.parse(lines[i]);
				} else {
					tRsp = request.getResponseClass().newInstance();
				}
				results.add(tRsp);
			}
		} catch (Exception e) {
			HifiveLogger.logBizError((String) rt.get("rsp"));
			throw new ApiException(e);
		}

		if (!tRsp.isSuccess()) {
			HifiveLogger.logErrorScene(rt, tRsp, appSecret);
		}
		return results;
	}

	public void setNeedCheckRequest(boolean needCheckRequest) {
		this.needCheckRequest = needCheckRequest;
	}

	public void setNeedEnableParser(boolean needEnableParser) {
		this.needEnableParser = needEnableParser;
	}

	public void setNeedEnableLogger(boolean needEnableLogger) {
		HifiveLogger.setNeedEnableLogger(needEnableLogger);
	}

}

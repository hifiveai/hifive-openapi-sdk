package com.hifive.api.internal.tdc;



import com.hifive.api.ApiException;
import com.hifive.api.Constants;
import com.hifive.api.internal.tdc.parser.*;
import com.hifive.api.internal.util.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * TDC 查询客户端的实现类
 * @author haofeng 2013-1-18
 * @date 2013-1-18 上午11:30:29
 */
public class DefaultTdcClient implements TdcClient{

	private static final String APP_KEY = "app_key";
	private static final String FORMAT = "format";
	private static final String TIMESTAMP = "timestamp";
	private static final String SIGN = "sign";
	private static final String SIGN_METHOD = "sign_method";
	private static final String PARTNER_ID = "partner_id";
	private static final String SESSION = "session";
	private static final String PATH_INFO = "path_info";
	
	private String serverUrl;
	private String appKey;
	private String appSecret;
	private String signMethod = Constants.SIGN_METHOD_MD5;
	private int connectTimeout = 3000;//3秒
	private int readTimeout = 15000;//15秒
	private boolean needEnableParser = true;
	
	public DefaultTdcClient(String serverUrl, String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.serverUrl = serverUrl;
	}
	
	public DefaultTdcClient(String serverUrl, String appKey, String appSecret, int connectTimeout, int readTimeout) {
		this(serverUrl, appKey, appSecret);
		this.connectTimeout = connectTimeout;
		this.readTimeout = readTimeout;
	}

	public DefaultTdcClient(String serverUrl, String appKey, String appSecret, int connectTimeout, int readTimeout, String signMethod) {
		this(serverUrl, appKey, appSecret, connectTimeout, readTimeout);
		this.signMethod = signMethod;
	}
	
	public CsvTdcResponse csvExecute(TdcRequest request) throws ApiException {
		return csvExecute(request, null);
	}

	public CsvTdcResponse csvExecute(TdcRequest request, String session)
			throws ApiException {
		TdcParser parser = null;
		if (needEnableParser) {
			parser = new TdcCsvParser();
		}
		return (CsvTdcResponse) _execute(request, session, parser, "csv");
	}

	public JXTdcResponse jsonExecute(TdcRequest request) throws ApiException {
		return jsonExecute(request, null);
	}

	public JXTdcResponse jsonExecute(TdcRequest request, String session)
			throws ApiException {
		TdcParser parser = null;
		if (needEnableParser) {
			parser = new TdcJsonParser();
		}
		return (JXTdcResponse) _execute(request, session, parser, Constants.FORMAT_JSON);
	}
	
	public JXTdcResponse xmlExecute(TdcRequest request) throws ApiException {
		return xmlExecute(request, null);
	}

	public JXTdcResponse xmlExecute(TdcRequest request, String session)
			throws ApiException {
		TdcParser parser = null;
		if (needEnableParser) {
			parser = new TdcXmlParser();
		}
		return (JXTdcResponse) _execute(request, session, parser, Constants.FORMAT_XML);
	}
	
	private TdcResponse _execute(TdcRequest request, String session, TdcParser parser, String format) throws ApiException {
		TdcResponse tdcRsp = null;
		String rsp = doPost(request, session, format);
		if (needEnableParser) {
			tdcRsp = parser.parse(rsp);
		} else {
			tdcRsp = new TdcResponse();
			tdcRsp.setBody(rsp);
		}
		return tdcRsp;
	}

	private String doPost(TdcRequest request, String session, String format) throws ApiException {
		RequestParametersHolder requestHolder = new RequestParametersHolder();
		HifiveHashMap appParams = request.getParams();
		requestHolder.setApplicationParams(appParams);
		
		// 添加协议级请求参数
		HifiveHashMap protocalMustParams = new HifiveHashMap();
		protocalMustParams.put(APP_KEY, appKey);
		Long timestamp = System.currentTimeMillis();
		DateFormat df = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
		df.setTimeZone(TimeZone.getTimeZone(Constants.DATE_TIMEZONE));
		protocalMustParams.put(TIMESTAMP, df.format(new Date(timestamp)));
		protocalMustParams.put(PATH_INFO, request.getPathInfo());
		requestHolder.setProtocalMustParams(protocalMustParams);
		HifiveHashMap protocalOptParams = new HifiveHashMap();
		protocalOptParams.put(FORMAT, format);
		protocalOptParams.put(SIGN_METHOD, signMethod);
		protocalOptParams.put(SESSION, session);
		protocalOptParams.put(PARTNER_ID, Constants.SDK_VERSION);
		requestHolder.setProtocalOptParams(protocalOptParams);

		// 添加签名参数
		try {
			if (Constants.SIGN_METHOD_MD5.equals(signMethod)) {
				protocalMustParams.put(SIGN, HifiveUtils.signTopRequestNew(requestHolder, appSecret, false));
			} else if (Constants.SIGN_METHOD_HMAC.equals(signMethod)) {
				protocalMustParams.put(SIGN, HifiveUtils.signTopRequestNew(requestHolder, appSecret, true));
			} else {
				protocalMustParams.put(SIGN, HifiveUtils.signTopRequest(requestHolder, appSecret));
			}
		} catch (IOException e) {
			throw new ApiException(e);
		}

		protocalMustParams.remove(PATH_INFO);
		StringBuffer urlSb = new StringBuffer(serverUrl + request.getPathInfo());
		try {
			String sysMustQuery = WebUtils.buildQuery(requestHolder.getProtocalMustParams(), Constants.CHARSET_UTF8);
			String sysOptQuery = WebUtils.buildQuery(requestHolder.getProtocalOptParams(), Constants.CHARSET_UTF8);

			urlSb.append("?");
			urlSb.append(sysMustQuery);
			if (sysOptQuery != null & sysOptQuery.length() > 0) {
				urlSb.append("&");
				urlSb.append(sysOptQuery);
			}
		} catch (IOException e) {
			throw new ApiException(e);
		}
		String rsp = null;
		try {
			rsp = WebUtils.doPost(urlSb.toString(), appParams, Constants.CHARSET_UTF8, connectTimeout, readTimeout);
		} catch (IOException e) {
			throw new ApiException(e);
		}
		return rsp;
	}
	
	public void setNeedEnableParser(boolean needEnableParser) {
		this.needEnableParser = needEnableParser;
	}

	public void setNeedEnableLogger(boolean needEnableLogger) {
		HifiveLogger.setNeedEnableLogger(needEnableLogger);
	}

	public TdcResponse execute(TdcReflowRequest request, String session)
			throws ApiException {
		String rsp = doBackflowPost(request, session, Constants.FORMAT_JSON);
		TdcParser parser = new TdcMbpBackParser();
		return parser.parse(rsp);
	}
	
	private String doBackflowPost(TdcReflowRequest request, String session, String format) throws ApiException {
		RequestParametersHolder requestHolder = new RequestParametersHolder();
		HifiveHashMap appParams = request.getParams();
		requestHolder.setApplicationParams(appParams);
		
		// 添加协议级请求参数
		HifiveHashMap protocalMustParams = new HifiveHashMap();
		protocalMustParams.put(APP_KEY, appKey);
		Long timestamp = System.currentTimeMillis();
		DateFormat df = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
		df.setTimeZone(TimeZone.getTimeZone(Constants.DATE_TIMEZONE));
		protocalMustParams.put(TIMESTAMP, df.format(new Date(timestamp)));
		protocalMustParams.put(PATH_INFO, request.getPathInfo());
		requestHolder.setProtocalMustParams(protocalMustParams);
		HifiveHashMap protocalOptParams = new HifiveHashMap();
		protocalOptParams.put(FORMAT, format);
		protocalOptParams.put(SIGN_METHOD, signMethod);
		protocalOptParams.put(SESSION, session);
		protocalOptParams.put(PARTNER_ID, Constants.SDK_VERSION);
		requestHolder.setProtocalOptParams(protocalOptParams);

		// 添加签名参数
		try {
			if (Constants.SIGN_METHOD_MD5.equals(signMethod)) {
				protocalMustParams.put(SIGN, HifiveUtils.signTopRequestNew(requestHolder, appSecret, false));
			} else if (Constants.SIGN_METHOD_HMAC.equals(signMethod)) {
				protocalMustParams.put(SIGN, HifiveUtils.signTopRequestNew(requestHolder, appSecret, true));
			} else {
				protocalMustParams.put(SIGN, HifiveUtils.signTopRequest(requestHolder, appSecret));
			}
		} catch (IOException e) {
			throw new ApiException(e);
		}

		protocalMustParams.remove(PATH_INFO);
		StringBuffer urlSb = new StringBuffer(serverUrl + request.getPathInfo());
		try {
			String sysMustQuery = WebUtils.buildQuery(requestHolder.getProtocalMustParams(), Constants.CHARSET_UTF8);
			String sysOptQuery = WebUtils.buildQuery(requestHolder.getProtocalOptParams(), Constants.CHARSET_UTF8);

			urlSb.append("?");
			urlSb.append(sysMustQuery);
			if (sysOptQuery != null & sysOptQuery.length() > 0) {
				urlSb.append("&");
				urlSb.append(sysOptQuery);
			}
		} catch (IOException e) {
			throw new ApiException(e);
		}
		String rsp = null;
		try {
			rsp = WebUtils.doPost(urlSb.toString(), appParams, Constants.CHARSET_UTF8, connectTimeout, readTimeout);
		} catch (IOException e) {
			throw new ApiException(e);
		}
		return rsp;
	}

}

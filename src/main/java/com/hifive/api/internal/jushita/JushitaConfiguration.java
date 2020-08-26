package com.hifive.api.internal.jushita;



import com.hifive.api.internal.stream.Configuration;
import com.hifive.api.internal.stream.TopCometStreamRequest;
import com.hifive.api.internal.stream.connect.ConnectionLifeCycleListener;

import java.util.HashMap;
import java.util.List;

/**
 * 聚石塔消息订阅专用长连接Configuration
 * <p>
 *     继承自Configuration，
 *     需要自己实现 public abstract List<String> getTopicInfo();
 * </p>
 *
 * Created by IntelliJ IDEA.
 * User: guichen - anson
 * Date: 12-5-23
 */
public abstract class JushitaConfiguration extends Configuration {
	private String url = "http://datasync.eai.Hifive.com/message/sub";
	private ConnectionLifeCycleListener connectionLifeCycleListener;

	public JushitaConfiguration(String appKey, String secret, String connectId) {
		super(appKey, secret, null, connectId);
		this.setConnectUrl(url);
		// 为保证完全顺序的消费，默认把消费线程池线程数设置为1
		this.setMinThreads(1);
		this.setMaxThreads(1);
		// 只会有一个TopCometStreamRequest 但因为是Set 还是要遍历一下
		for (TopCometStreamRequest cometReq : this.getConnectReqParam()) {
			// 设置ConnectionListener为我的特殊Listener
			cometReq.setConnectListener(new JushitaConnectionLifeCycleListener());
		}
	}

	/**
	 * 每次连接前计算并返回要订阅的topic信息
	 * @return topic信息List
	 */
	public abstract List<String> getTopicInfo();

	public void setConnectionLifeCycleListener(ConnectionLifeCycleListener connectionLifeCycleListener) {
		this.connectionLifeCycleListener = connectionLifeCycleListener;
	}

	private void setTopicInfo(List<String> topicInfoList) {
		StringBuilder topicStr = new StringBuilder("");
		for (String topicInfo : topicInfoList) {
			topicStr.append(topicInfo).append(";");
		}
		if (this.getConnectReqParam().size() > 1) {
			throw new IllegalStateException("配置状态不正常，连接配置大于1个");
		}
		for (TopCometStreamRequest cometReq : this.getConnectReqParam()) {
			if (cometReq.getOtherParam() == null) {
				cometReq.setOtherParam(new HashMap<String, String>());
			}
			cometReq.getOtherParam().put("topic", topicStr.substring(0, topicStr.length() - 1));
		}
	}

	private class JushitaConnectionLifeCycleListener implements ConnectionLifeCycleListener {
		public void onBeforeConnect() {
			if (JushitaConfiguration.this.connectionLifeCycleListener != null) {
				JushitaConfiguration.this.connectionLifeCycleListener.onBeforeConnect();
			}
			List<String> topicInfoList = JushitaConfiguration.this.getTopicInfo();
			if (topicInfoList == null || topicInfoList.isEmpty()) {
				throw new IllegalArgumentException("method getTopicInfo() return a empty list");
			}
			JushitaConfiguration.this.setTopicInfo(topicInfoList);
		}

		public void onException(Throwable throwable) {
			if (JushitaConfiguration.this.connectionLifeCycleListener != null) {
				JushitaConfiguration.this.connectionLifeCycleListener.onException(throwable);
			}
		}

		public void onMaxReadTimeoutException() {
			if (JushitaConfiguration.this.connectionLifeCycleListener != null) {
				JushitaConfiguration.this.connectionLifeCycleListener.onMaxReadTimeoutException();
			}
		}

	}
}

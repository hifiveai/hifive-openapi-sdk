package com.hifive.api.internal.jushita;



import com.hifive.api.internal.jushita.stream.Message;
import com.hifive.api.internal.jushita.stream.MessageDriver;
import com.hifive.api.internal.stream.Configuration;
import com.hifive.api.internal.stream.TopCometStreamRequest;
import com.hifive.api.internal.stream.connect.ConnectionLifeCycleListener;
import com.hifive.api.internal.stream.message.TopCometMessageListener;
import com.hifive.link.Logger;
import com.hifive.link.logging.LogUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: guichen - anson
 * Date: 12-8-14
 */
public class JushitaConfigurationV2 extends Configuration {
	private static final Logger logger = LogUtil.getLoggerFactory(JushitaConfigurationV2.class).create(JushitaConfigurationV2.class);

	private String url = "http://datasync.eai.Hifive.com/message/sub";
	private String reportUrl = "http://datasync.eai.Hifive.com/message/report";

	private int reportCount = 100;
	private long reportInterval = 5 * 60 * 1000L;

	private List<String> topics = null;

	private TopCometMessageListener topCometMessageListener;

	private ConnectionLifeCycleListener connectionListener;

	private MessageDriver driver;

	public JushitaConfigurationV2(String appKey, String secret, String connectId, List<String> topics) {
		super(appKey, secret, connectId, topics, new HashMap<String, String>());

		this.topics = topics;

		// 保持单线程 多线程交给MessageDriver去处理 这是为了确保顺序的把消息提交给MessageDriver
		super.setMinThreads(1);
		super.setMaxThreads(1);

		// 只会有一个 但因为是set所以还是要遍历下
		for (TopCometStreamRequest cometStreamRequest : this.getConnectReqParam()) {
			cometStreamRequest.setConnectListener(new InnerConnectionLifeCycleListener());
			cometStreamRequest.setMsgListener(new InnerMessageListener());
			// 带上ver参数
			cometStreamRequest.getOtherParam().put("ver", "2");
		}

		driver = new MessageDriver(appKey, secret);
		driver.setMessageHandler(new MessageDriver.MessageHandler() {
			public boolean handle(Message message) {
				// 任何报错都算失败
				try {
					topCometMessageListener.onReceiveMsg(message.getMessage());
				} catch (Exception e) {
					logger.error("error when handle message:" + message.getMessage(), e);
					return false;
				}
				return true;
			}
		});
		this.setConnectUrl(url);
	}

	/**
	 * 测试用的构造方法
	 */
	protected JushitaConfigurationV2(String appKey, String secret, String connectId, List<String> topics, MessageDriver driver) {
		this(appKey, secret, connectId, topics);
		this.driver = driver;
		driver.setReportUrl(reportUrl);
		driver.setMessageHandler(new MessageDriver.MessageHandler() {
			public boolean handle(Message message) {
				// 任何报错都算失败
				try {
					logger.debug(message.getOffset() + " - " + message.getMessage());
					topCometMessageListener.onReceiveMsg(message.getMessage());
				} catch (Exception e) {
					logger.error("error when handle message:" + message.getMessage(), e);
					return false;
				}
				return true;
			}
		});
	}

	@Override
	public void setConnectUrl(String url) {
		this.url = url;
		super.setConnectUrl(url);
		this.reportUrl = url.substring(0, url.lastIndexOf("/") + 1) + "report";
		this.driver.setReportUrl(reportUrl);
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public void setReportInterval(long reportInterval) {
		this.reportInterval = reportInterval;
	}

	public int getReportCount() {
		return reportCount;
	}

	public long getReportInterval() {
		return reportInterval;
	}

	public TopCometMessageListener getTopCometMessageListener() {
		return topCometMessageListener;
	}

	public void setTopCometMessageListener(TopCometMessageListener topCometMessageListener) {
		this.topCometMessageListener = topCometMessageListener;
	}

	public ConnectionLifeCycleListener getConnectionListener() {
		return connectionListener;
	}

	public void setConnectionListener(ConnectionLifeCycleListener connectionListener) {
		this.connectionListener = connectionListener;
	}

	public MessageDriver getDriver() {
		return driver;
	}

	private class InnerMessageListener implements TopCometMessageListener {
		public void onConnectMsg(String message) {
			if (JushitaConfigurationV2.this.topCometMessageListener != null) {
				JushitaConfigurationV2.this.topCometMessageListener.onConnectMsg(message);
			}
		}

		public void onHeartBeat() {
			if (JushitaConfigurationV2.this.topCometMessageListener != null) {
				JushitaConfigurationV2.this.topCometMessageListener.onHeartBeat();
			}
		}

		public void onReceiveMsg(String message) {
			// 收到消息时push到driver里去
			driver.pushMessage(message);
		}

		public void onDiscardMsg(String message) {
			if (JushitaConfigurationV2.this.topCometMessageListener != null) {
				JushitaConfigurationV2.this.topCometMessageListener.onDiscardMsg(message);
			}
		}

		public void onServerUpgrade(String message) {
			if (JushitaConfigurationV2.this.topCometMessageListener != null) {
				JushitaConfigurationV2.this.topCometMessageListener.onServerUpgrade(message);
			}
		}

		public void onServerRehash() {
			if (JushitaConfigurationV2.this.topCometMessageListener != null) {
				JushitaConfigurationV2.this.topCometMessageListener.onServerRehash();
			}
		}

		public void onServerKickOff() {
			if (JushitaConfigurationV2.this.topCometMessageListener != null) {
				JushitaConfigurationV2.this.topCometMessageListener.onServerKickOff();
			}
		}

		public void onClientKickOff() {
			if (JushitaConfigurationV2.this.topCometMessageListener != null) {
				JushitaConfigurationV2.this.topCometMessageListener.onClientKickOff();
			}
		}

		public void onOtherMsg(String message) {
			if (JushitaConfigurationV2.this.topCometMessageListener != null) {
				JushitaConfigurationV2.this.topCometMessageListener.onOtherMsg(message);
			}
		}

		public void onException(Exception ex) {
			if (JushitaConfigurationV2.this.topCometMessageListener != null) {
				JushitaConfigurationV2.this.topCometMessageListener.onException(ex);
			}
		}
	}

	private class InnerConnectionLifeCycleListener implements ConnectionLifeCycleListener {
		public void onBeforeConnect() {
			if (JushitaConfigurationV2.this.connectionListener != null) {
				JushitaConfigurationV2.this.connectionListener.onBeforeConnect();
			}
		}

		public void onException(Throwable e) {
			if (JushitaConfigurationV2.this.connectionListener != null) {
				JushitaConfigurationV2.this.connectionListener.onException(e);
			}
		}

		public void onMaxReadTimeoutException() {
			if (JushitaConfigurationV2.this.connectionListener != null) {
				JushitaConfigurationV2.this.connectionListener.onMaxReadTimeoutException();
			}
		}
	}

	@Override
	public String toString() {
		return "JushitaConfigurationV2{" +
				"url='" + url + '\'' +
				", topics=" + topics +
				'}';
	}
}

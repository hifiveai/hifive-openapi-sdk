package com.hifive.api.internal.jushita.stream;



import com.hifive.api.ApiException;
import com.hifive.api.internal.jushita.JushitaHifiveClient;
import com.hifive.api.internal.util.StringUtils;
import com.hifive.link.Logger;
import com.hifive.link.logging.LogUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 消息缓冲区的驱动对象
 * <p>
 *     TODO: 考虑为所有的连接共用同一个缓冲区和驱动对象
 * </p>
 *
 * Created by IntelliJ IDEA.
 * User: guichen - anson
 * Date: 12-8-21
 */
public class MessageDriver implements Runnable {
	private static final Logger logger = LogUtil.getLoggerFactory(MessageDriver.class).create(MessageDriver.class);

	private String reportUrl;
	private int reportCount = 100;
	private long reportInterval = 5 * 60 * 1000l;
	private int consumerThreadCount = 10;
	private int timeoutSeconds = 10;

	private MessageHandler messageHandler;

	private MessageCircleQueue queue;
	private MessageHandleThread[] consumerThreads;
	private ScheduledExecutorService reportSchedule;

	private Report lastFailedReport;
	private long lastReportTime;

	private String appKey;
	private String secret;

	private JushitaHifiveClient client;

	public MessageDriver(String appKey, String secret) {
		if (StringUtils.isEmpty(appKey) || StringUtils.isEmpty(secret)) {
			throw new NullPointerException("parameters should not empty");
		}
		this.appKey = appKey;
		this.secret = secret;
	}

	/************* getter && setter **************/
	public String getReportUrl() {
		return reportUrl;
	}

	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public long getReportInterval() {
		return reportInterval;
	}

	public void setReportInterval(long reportInterval) {
		this.reportInterval = reportInterval;
	}

	public int getTimeoutSeconds() {
		return timeoutSeconds;
	}

	public void setTimeoutSeconds(int timeoutSeconds) {
		this.timeoutSeconds = timeoutSeconds;
	}

	public int getConsumerThreadCount() {
		return consumerThreadCount;
	}

	public void setConsumerThreadCount(int consumerThreadCount) {
		this.consumerThreadCount = consumerThreadCount;
	}

	public MessageHandler getMessageHandler() {
		return messageHandler;
	}

	public void setMessageHandler(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

	/************* getter && setter **************/

	public void start() {
		logger.info("driver start");
		if (messageHandler == null) {
			throw new NullPointerException("message handle is not be set");
		}
		if (StringUtils.isEmpty(reportUrl)) {
			throw new NullPointerException("report url is not be set");
		}
		// 构建用于发送report的client对象
		client = new JushitaHifiveClient(reportUrl, appKey, secret);
		// 这两个值会影响到吞吐量 50W消息测试大概在38K
		// 生产环境中因为网络的缘故TPS很可能连1K都不到 这两个值的取值已经够了
		int queueSizeMultiple = 20;
		long reportTimerInterval = 50;
		// 初始化缓冲区队列
		queue = new MessageCircleQueue(reportCount * queueSizeMultiple, timeoutSeconds);
		consumerThreads = new MessageHandleThread[consumerThreadCount];
		for (int i = 0; i < consumerThreadCount; i++) {
			consumerThreads[i] = new MessageHandleThread("msg-driver-thread-" + i, messageHandler, queue);
			consumerThreads[i].start();
		}
		this.lastReportTime = System.currentTimeMillis();
		// 启动report schedule线程
		reportSchedule = Executors.newScheduledThreadPool(1);
		reportSchedule.scheduleWithFixedDelay(this, reportTimerInterval, reportTimerInterval, TimeUnit.MILLISECONDS);
	}

	public void stop() {
		logger.info("driver stop");
		for (int i = 0; i < consumerThreadCount; i++) {
			consumerThreads[i].shutdown();
		}
		reportSchedule.shutdown();
	}

	public void pushMessage(String message) {
		try {
			queue.put(new Message(message));
		} catch (InterruptedException e) {
			// 谁那么蛋疼中断这个线程 无视
			logger.info("push message thread is interrupted");
		}
	}

	/**
	 * 此TimerTask用来查看是否需要report
	 */
	public void run() {
		if (lastFailedReport != null) {
			report(lastFailedReport);
		} else {
			int checkedCount = queue.check();
			// 大于需要报告的数量阈值或者超过最长报告时间间隔并且被确认的数量大于0
			if (checkedCount >= reportCount || (checkedCount > 0 && (System.currentTimeMillis() - lastReportTime) > reportInterval)) {
				report(queue.report());
				lastReportTime = System.currentTimeMillis();
			}
		}
	}

	// TODO shutdown hook, 在正常停止的时候要report一次
	protected void report(Report report) {
		Map<String, String> params = new HashMap<String, String>();
		if (logger.isInfoEnabled()) {
			logger.info("report happened:" + report.asJson());
		}
		params.put("report", report.asJson());
		// SDK一定要userId和topic 随便放一个
		params.put("user_id", "1");
		params.put("topic", "topic");
		try {
			// SDK一定要session 随便放一个
			client.execute("report_message", params, "session");
			lastFailedReport = null;
		} catch (ApiException e) {
			lastFailedReport = report;
			logger.error("report message error. if this happened many times, please contact us.", e);
		}
	}

	public static interface MessageHandler {
		boolean handle(Message message);
	}

	public static class MessageHandleThread extends Thread {
		private MessageHandler messageHandle;
		private MessageCircleQueue queue;
		private volatile boolean run = true;

		public MessageHandleThread(String name, MessageHandler messageHandle, MessageCircleQueue queue) {
			super(name);
			this.messageHandle = messageHandle;
			this.queue = queue;
		}

		@Override
		public void run() {
			while (run) {
				try {
					Message message = queue.take();
					boolean isSuccess = messageHandle.handle(message);
					if (isSuccess) {
						message.setState(Message.State.SUCCESS);
					} else {
						message.setState(Message.State.FAILED);
					}
				} catch (InterruptedException e) {
					// 被中断就跳出
					logger.warn("thread is interrupted");
					break;
				} catch (Exception e) {
					// 别的错都无视
					logger.warn("error happened when handle message:" + e.getMessage(), e);
				}
			}
		}

		public void shutdown() {
			this.run = false;
		}
	}

}

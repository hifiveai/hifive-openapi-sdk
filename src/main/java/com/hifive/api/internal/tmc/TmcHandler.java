package com.hifive.api.internal.tmc;


import com.hifive.link.LinkException;
import com.hifive.link.endpoint.EndpointContext;
import com.hifive.link.endpoint.Identity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.hifive.link.endpoint.MessageHandler;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息服务-内部处理器
 */
class TmcHandler implements MessageHandler {

	private static final Log log = LogFactory.getLog(TmcClient.class);

	protected TmcClient tmcClient;

	public TmcHandler(TmcClient tmcClient) {
		this.tmcClient = tmcClient;
	}

	public final void onMessage(Map<String, Object> message, Identity identity) {
		if (log.isDebugEnabled()) {
			log.debug("unexpected messsage: " + message);
		}
	}

	public void onMessage(final EndpointContext context) throws Exception {
		final Map<String, Object> rawMsg = context.getMessage();

		if (log.isDebugEnabled()) {
			log.debug(String.format("onMessage from %s: %s", context.getMessageFrom(), rawMsg));
		}

		handleMessage(rawMsg);
	}

	public void close() {
	}

	protected void handleMessage(final Map<String, Object> rawMsg) {
		handleMessage(parse(rawMsg));
	}

	protected void handleMessage(final Message message) {
		tmcClient.getThreadPool().submit(new Runnable() {
			public void run() {
				MessageStatus status = new MessageStatus();

				try {
					tmcClient.getMessageHandler().onMessage(message, status);
				} catch (Exception e) {
					log.error(String.format("handle message fail: %s", message.getRaw()), e);
					return;
				}

				if (status.isFail())
					return;

				try {
					confirm(message.getRaw());
				} catch (Exception e) {
					log.warn(String.format("confirm message fail: %s", message.getRaw()), e);
				}
			}
		});
	}

	protected void confirm(Map<String, Object> message) throws LinkException {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put(MessageFields.KIND, MessageKind.Confirm);
		msg.put(MessageFields.CONFIRM_ID, message.get(MessageFields.OUTGOING_ID));
		tmcClient.getClient().send(msg);
	}

	protected Message parse(Map<String, Object> raw) {
		Message msg = new Message();
		msg.setRaw(raw);
		msg.setId((Long) raw.get(MessageFields.OUTGOING_ID));
		msg.setTopic((String) raw.get(MessageFields.DATA_TOPIC));
		msg.setPubAppKey((String) raw.get(MessageFields.DATA_OUTGOING_PUBLISHER));
		msg.setUserId((Long) raw.get(MessageFields.DATA_OUTGOING_USER_ID));
		msg.setUserNick((String) raw.get(MessageFields.DATA_OUTGOING_USER_NICK));
		msg.setContent((String) raw.get(MessageFields.DATA_CONTENT));
		msg.setPubTime((Date) raw.get(MessageFields.DATA_PUBLISH_TIME));
		Object time = raw.get(MessageFields.DATA_ATTACH_OUTGOING_TIME);
		if (time != null)
			msg.setOutgoingTime((Date) time);
		return msg;
	}


}
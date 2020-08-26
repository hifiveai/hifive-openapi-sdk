package com.hifive.api.internal.jushita.stream;


import com.hifive.api.internal.util.HifiveUtils;

import java.util.Map;

/**
 * 消息实体类
 * <p>
 *     除了消息中带有的业务信息外，还有三个给MessageCircleQueue用的属性：index，state和popTime
 * </p>
 *
 * Created by IntelliJ IDEA.
 * User: guichen - anson
 * Date: 12-8-13
 */
public class Message {
	private String topic;
	private String pubAppKey;
	private String offset;
	private String nextOffset;
	private String md5;
	private long userId;
	private String message;

	private Offset offsetDO;
	private Offset nextOffsetDO;

	private int index;
	private volatile State state = State.UNKNOWN;
	private long takeTime;

	private long putTime = System.currentTimeMillis();

	public Message(String jsonText) {
		// 这破Utils转换出来的key都是String value有String和long
		Map<?, ?> jsonMap = HifiveUtils.parseJson(jsonText);
		// init attribute
		this.topic = (String) jsonMap.get("topic");
		this.message = (String) jsonMap.get("message");
		this.nextOffset = (String) jsonMap.get("nextOffset");
		this.offset = (String) jsonMap.get("offset");
		this.md5 = (String) jsonMap.get("md5");
		this.pubAppKey = (String) jsonMap.get("pubAppKey");
		this.userId = (Long) jsonMap.get("userId");
		// parser offsetDO
		this.offsetDO = new Offset(offset);
		this.nextOffsetDO = new Offset(nextOffset);
	}

	public String getTopic() {
		return topic;
	}

	public String getPubAppKey() {
		return pubAppKey;
	}

	public String getOffset() {
		return offset;
	}

	public String getNextOffset() {
		return nextOffset;
	}

	public long getUserId() {
		return userId;
	}

	public String getMessage() {
		return message;
	}

	public Offset getOffsetDO() {
		return offsetDO;
	}

	public Offset getNextOffsetDO() {
		return nextOffsetDO;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public long getTakeTime() {
		return takeTime;
	}

	public void taken() {
		this.takeTime = System.currentTimeMillis();
	}

	public long getPutTime() {
		return putTime;
	}

	public String getMd5() {
		return md5;
	}

	@Override
	public String toString() {
		return "JIPMessage{" +
				"topic='" + topic + '\'' +
				", pubAppKey='" + pubAppKey + '\'' +
				", offset='" + offset + '\'' +
				", nextOffset='" + nextOffset + '\'' +
				", userId=" + userId +
				", message='" + message + '\'' +
				'}';
	}

	public static class Offset {
		public long id;
		public String partition;
		public long offset;

		public Offset(long id, String partition, long offset) {
			this.id = id;
			this.partition = partition;
			this.offset = offset;
		}

		public Offset(String offsetStr) {
			String[] splitResult = offsetStr.split("@");
			this.id = Long.valueOf(splitResult[0]);
			this.partition = splitResult[1];
			this.offset = Long.valueOf(splitResult[2]);
		}
	}

	public static enum State {
		SUCCESS, FAILED, UNKNOWN
	}
}

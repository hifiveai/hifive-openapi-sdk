package com.hifive.api.internal.stream;

/**
 * @author junmu
 *
 */
public enum GroupEnum {
	//group2是给数据推动专用的
	GROUP_DEFAULT("1"),GROUP_SECONDARY("2");
	
	final private String groupId;//不同的组之间的消息是隔离，互不干扰的，group2是给数据推动专用的
	
	public String getGroupId() {
		return groupId;
	}
	private GroupEnum(String groupId){
		this.groupId=groupId;
	}
}

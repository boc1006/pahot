package cn.pahot.xa.enums;

/**
 * 消息存活状态
 * @author hj87080234@gmail.com
 * @date 2016年9月6日
 */
public enum MessageLiveStateEnum {
	ALIVED("01", "未死亡"), 
	DEAD("02", "已死亡"); 
	
	// 成员变量
	public String key;
	public String value;

	// 构造方法
	private MessageLiveStateEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
}

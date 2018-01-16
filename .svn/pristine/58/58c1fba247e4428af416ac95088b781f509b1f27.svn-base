package cn.pahot.message.consts;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信发送渠道
 * <p>@Title: SmsChannel.java 
 * <p>@Package cn.pahot.message.consts 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月8日 下午6:44:29 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public enum SmsChannelEnum {
	CHANNEL_ALIDY("01", "阿里大鱼");
	
	// 成员变量
	public String key;
	public String value;

	// 构造方法
	private SmsChannelEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	private static final Map<String, SmsChannelEnum> stringToEnum = new HashMap<String, SmsChannelEnum>();
	static {
		for (SmsChannelEnum blah : values()) {
			stringToEnum.put(blah.key, blah);
		}
	}

	public static SmsChannelEnum toEnum(String symbol) {
		return stringToEnum.get(symbol);
	}
}

package cn.pahot.message.consts;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信发送状态
 * <p>@Title: SmsStateEnum.java 
 * <p>@Package cn.pahot.message.consts 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月8日 下午6:47:47 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public enum SmsStateEnum {
	SMS_STATE_WATTING("01", "待发送"),
	SMS_STATE_SENDDING("02", "发送中"),
	SMS_STATE_SUCCESS("03", "发送成功"),
	SMS_STATE_RETRY("04", "发送失败待重发"),
	SMS_STATE_FAIL("05", "发送失败关闭");
	
	// 成员变量
	public String key;
	public String value;

	// 构造方法
	private SmsStateEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	private static final Map<String, SmsStateEnum> stringToEnum = new HashMap<String, SmsStateEnum>();
	static {
		for (SmsStateEnum blah : values()) {
			stringToEnum.put(blah.key, blah);
		}
	}

	public static SmsStateEnum toEnum(String symbol) {
		return stringToEnum.get(symbol);
	}
}

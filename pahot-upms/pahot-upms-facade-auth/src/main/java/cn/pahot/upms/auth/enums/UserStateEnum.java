package cn.pahot.upms.auth.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统用户状态
 * <p>@Title: UserStateEnum.java 
 * <p>@Package cn.pahot.upms.auth.enums 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月21日 上午11:35:03 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public enum UserStateEnum {
	USER_STATE_OK("01", "正常"),
	USER_STATE_LOCK("02","冻结"),
	USER_STATE_LOGOUT("00","注销");
	
	// 成员变量
	public String key;
	public String value;

	// 构造方法
	private UserStateEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	private static final Map<String, UserStateEnum> stringToEnum = new HashMap<String, UserStateEnum>();
	static {
		for (UserStateEnum blah : values()) {
			stringToEnum.put(blah.key, blah);
		}
	}

	public static UserStateEnum toEnum(String symbol) {
		return stringToEnum.get(symbol);
	}
}

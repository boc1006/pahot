package cn.pahot.upms.consts;

import java.util.HashMap;
import java.util.Map;

/**
 * 角色状态枚举
 * <p>@Title: RoleStateEnum.java 
 * <p>@Package cn.pahot.upms.consts 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月7日 上午10:50:29 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public enum RoleStateEnum {
	ROLE_STATE_NORMAL("01", "正常"),
	ROLE_STATE_OFF("00","禁用"),
	USER_STATE_ALL("02","全部");
	
	// 成员变量
	public String key;
	public String value;

	// 构造方法
	private RoleStateEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	private static final Map<String, RoleStateEnum> stringToEnum = new HashMap<String, RoleStateEnum>();
	static {
		for (RoleStateEnum blah : values()) {
			stringToEnum.put(blah.key, blah);
		}
	}

	public static RoleStateEnum toEnum(String symbol) {
		return stringToEnum.get(symbol);
	}
}

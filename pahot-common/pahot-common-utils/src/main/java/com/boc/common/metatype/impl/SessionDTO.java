package com.boc.common.metatype.impl;

/**
 * 具体用户状态信息的DTO,该对象只能在Web项目中实例化
 * <p>@Title: SessionDTO.java 
 * <p>@Package com.boc.common.web 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月6日 上午11:08:18 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class SessionDTO<K, V> extends BaseDTO<K, V> {
	private static final long serialVersionUID = 3520806671198149221L;
	
	@Override
	public Integer getUserId() {
		return getAsInteger(SESSION_KEY_USERID);
	}

	@Override
	public String getUserName() {
		return getAsString(SESSION_KEY_USERNAME);
	}

	@Override
	public String getSystemId() {
		return getAsString(SESSION_KEY_SYSTEMID);
	}

	@Override
	public String getClientIp() {
		return getAsString(SESSION_KEY_CLIENTIP);
	}

	@Override
	public Long getClientTime() {
		return getAsLong(SESSION_KEY_CLIENTTIME);
	}
}

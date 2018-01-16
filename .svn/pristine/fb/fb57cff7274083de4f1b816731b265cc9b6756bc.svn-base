package com.boc.common.web.permissions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.boc.common.web.permissions.exception.PermissionsException;

/**
 * 会话管理
 * <p>@Title Session</p>
 * <p>@Description com.boc.common.web.permissions</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月14日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class Session implements Serializable{

	private static final long serialVersionUID = -4183032171842955255L;
	/**
	 * 会话信息保存对象
	 */
	private Map<String,Serializable> map = new HashMap<String,Serializable>();
	/**
	 * 会话ID
	 */
	private String id;

	/**
	 * 主机信息
	 */
	private String host;

	public Serializable getAttribute(String key) {
		return this.map.get(key);
	}

	/**
	 * 保存数据
	 * @param key
	 * @param attribute
	 * @throws Exception
	 */
	public void setAttribute(String key,Serializable attribute) throws PermissionsException {
		this.map.put(key, attribute);
		SecurityUtils.getSubject().reloadSession(this);
	}

	/**
	 * 移除数据
	 * @param key
	 * @throws Exception
	 */
	public void removeAttribute(String key) throws PermissionsException {
		this.map.remove(key);
		SecurityUtils.getSubject().reloadSession(this);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}

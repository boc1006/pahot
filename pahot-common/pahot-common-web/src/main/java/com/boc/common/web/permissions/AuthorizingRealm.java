package com.boc.common.web.permissions;

import com.boc.common.web.permissions.exception.PermissionsException;

/**
 * 用户登录授权接口
 * <p>@Title AuthorizingRealm</p>
 * <p>@Description com.boc.common.web.permissions</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月14日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public abstract class AuthorizingRealm {
	
	/**
	 * 校验用户登录信息
	 * @param userName
	 * @param passwd 加密后的密码串
	 * @return
	 */
	public abstract AuthenticationInfo doGetAuthenticationInfo(String userName,String passwd,String ...params) throws PermissionsException;
	
	private TokenEncryption tokenEncryption;

	public TokenEncryption getTokenEncryption() {
		return tokenEncryption;
	}

	public void setTokenEncryption(TokenEncryption tokenEncryption) {
		this.tokenEncryption = tokenEncryption;
	}
	
}

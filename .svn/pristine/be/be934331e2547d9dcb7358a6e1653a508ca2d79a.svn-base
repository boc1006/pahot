package com.boc.common.web.permissions.exception;

import com.boc.common.exception.BizSystemException;
import com.boc.common.web.enums.UpmsWebConst;

/**
 * 用户权限管理异常处理
 * <p>@Title PermissionsException</p>
 * <p>@Description com.boc.common.web.permissions.exception</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月15日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class PermissionsException extends BizSystemException{

	private static final long serialVersionUID = 6361312063281749461L;
	public static final BizSystemException INSTANCE = new BizSystemException(UpmsWebConst.EXCEPTION_INIT_CODE, "%s");
	/**
	 * 用户名或密码为空
	 */
	public static final BizSystemException ACCOUNT_IS_NULL = new BizSystemException(UpmsWebConst.EXCEPTION_INIT_CODE+UpmsWebConst.EXCEPTION_INIT_CODE, "用户名或密码为空==>【%s】");
	
	/**
	 * 帐户校验失败
	 */
	public static final BizSystemException ACCOUNT_FAIL = new BizSystemException(UpmsWebConst.EXCEPTION_INIT_CODE+2, "帐户信息校验失败==>【%s】");
	/**
	 * 权限异常
	 */
	public static final BizSystemException PERMISSION_FAIL = new BizSystemException(UpmsWebConst.EXCEPTION_INIT_CODE+3, "用户权限校验失败==>【%s】");

}

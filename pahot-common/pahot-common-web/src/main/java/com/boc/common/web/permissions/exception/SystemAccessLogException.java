package com.boc.common.web.permissions.exception;

import com.boc.common.exception.BizSystemException;

/**
 * 处理系统访问日志异常
 * <p>@Title PermissionsException</p>
 * <p>@Description com.boc.common.web.permissions.exception</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月15日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class SystemAccessLogException extends BizSystemException{

	private static final long serialVersionUID = 6361312063281749461L;
	public static final BizSystemException INSTANCE = new BizSystemException(99880000, "%s");
	
	public static final BizSystemException ACCESS_LOGGER_NULL = new BizSystemException(99880001, "处理访问日志参数为空==>【%s】");
	public static final BizSystemException EXCEPTION_LOGGER_NULL = new BizSystemException(99880001, "处理异常日志参数为空==>【%s】");

}

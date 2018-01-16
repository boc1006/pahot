package cn.pahot.upms.auth.exception;

import com.boc.common.exception.BizException;

import cn.pahot.upms.auth.consts.AuthConst;

/**
 * 系统用户授权认证业务异常
 * <p>@Title: UserAuthBizException.java 
 * <p>@Package cn.pahot.upms.auth.exception 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月21日 上午11:40:20 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class UserAuthBizException extends BizException {

	private static final long serialVersionUID = 8311120224274809527L;

	public static final BizException INSTANCE = new BizException(AuthConst.EXCEPTION_INIT_CODE, "%s");
	public static final BizException PARAM_IS_NULL = new BizException(AuthConst.EXCEPTION_INIT_CODE+1, "用户登录参数不能为空==>【%s】");
	public static final BizException PERMISSION_IS_NULL = new BizException(AuthConst.EXCEPTION_INIT_CODE+1, "该用户未授予任何权限==>【%s】");

}

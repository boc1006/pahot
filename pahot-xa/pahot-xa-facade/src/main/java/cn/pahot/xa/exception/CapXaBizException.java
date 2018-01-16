package cn.pahot.xa.exception;

import com.boc.common.exception.BizException;

import cn.pahot.xa.enums.XAConst;

/**
 * 分布式事务异常处理
 * <p>@Title CapXaBizException</p>
 * <p>@Description com.dgg.message.xa.exception</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2017年2月16日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class CapXaBizException extends BizException {

	private static final long serialVersionUID = 3643734746996823510L;

	public static final BizException INSTANCE = new BizException(XAConst.EXCEPTION_INIT_CODE, "%s");
	public static final BizException XA_DEFAULT_NULL = new BizException(XAConst.EXCEPTION_INIT_CODE+1, "传入参数对象不能为空");
	
}

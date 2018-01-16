package cn.pahot.logger.exception;

import com.boc.common.exception.BizException;

import cn.pahot.logger.consts.LoggerConst;

/**
 * 日志信息异常处理
 * <p>@Title LoggerBizException</p>
 * <p>@Description 日志信息异常类</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Hou Jiang Hua</p>o
 * <p>@date 2016年12月13日</p>
 * <p>houjianghua@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class LoggerBizException extends BizException {

	private static final long serialVersionUID = 3643734746996823510L;

	public static final BizException INSTANCE = new BizException(LoggerConst.EXCEPTION_INIT_CODE, "%s");
	
	public static final BizException PARAMS_IS_NULL = new BizException(LoggerConst.EXCEPTION_INIT_CODE+1, "日志处理参数不能为空==>【%s】");
	
}

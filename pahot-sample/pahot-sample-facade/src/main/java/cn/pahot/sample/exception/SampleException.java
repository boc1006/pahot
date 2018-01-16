package cn.pahot.sample.exception;

import com.boc.common.exception.BizException;

import cn.pahot.sample.constant.SampleConst;

/**
 * 定义Sample业务处理异常
 * <p>@Title: SampleException.java 
 * <p>@Package cn.pahot.sample.exception 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月1日 下午6:05:06 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class SampleException extends BizException {

	private static final long serialVersionUID = 3841579627235243770L;
	public static final BizException INSTANCE = new BizException(SampleConst.EXCEPTION_INIT_CODE, "%s");
	public static final BizException PARAM_IS_NULL = new BizException(SampleConst.EXCEPTION_INIT_CODE+1, "参数不能为空==>【%s】");
	
}

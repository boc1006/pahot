package com.boc.common.core.biz;

import com.boc.common.exception.BizException;

/**
 * 参数校验异常定义
 * <p>@Title: SampleException.java
 * <p>@Package cn.pahot.sample.exception
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com
 * <p>@date 2017年12月1日 下午6:05:06
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class ParamVerifyException extends BizException {

	private static final long serialVersionUID = 3841579627235243770L;

	public static final BizException INSTANCE = new BizException(11110000, "%s");
	public static final BizException PARAM_IS_NULL = new BizException(11110001, "参数不能为空==>【%s】");
}

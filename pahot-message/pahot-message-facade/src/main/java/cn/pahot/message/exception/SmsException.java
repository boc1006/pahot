package cn.pahot.message.exception;

import com.boc.common.exception.BizException;

import cn.pahot.message.consts.MessageConst;

/**
 * 短信发送异常定义
 * <p>@Title: SmsException.java 
 * <p>@Package cn.pahot.message.exception 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com
 * <p>@date 2017年12月8日 下午7:11:34 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class SmsException extends BizException {

	private static final long serialVersionUID = -5813069753572338269L;
	public static final BizException INSTANCE = new BizException(MessageConst.EXCEPTION_INIT_CODE, "%s");
	public static final BizException PARAM_IS_NULL = new BizException(MessageConst.EXCEPTION_INIT_CODE+1, "短信发送参数为空==>【%s】");
	public static final BizException PARAM_HINT_ERROR = new BizException(MessageConst.EXCEPTION_INIT_CODE+2, "短信发送非法请求==>【%s】");
	public static final BizException CODE_VERIFY_ERROR = new BizException(MessageConst.EXCEPTION_INIT_CODE+3, "短信验证码校验失败==>【%s】");
}

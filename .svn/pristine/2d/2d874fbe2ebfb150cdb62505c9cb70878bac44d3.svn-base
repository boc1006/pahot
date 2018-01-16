package cn.pahot.member.exception;

import com.boc.common.exception.BizException;

import cn.pahot.member.consts.MemberConsts;

/**
 * 会员管理业务异常定义
 * <p>@Title: MemberException.java 
 * <p>@Package cn.pahot.member.exception 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月21日 下午1:48:57 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class MemberException extends BizException {

	private static final long serialVersionUID = 369493654527031276L;
	public static final BizException INSTANCE = new BizException(MemberConsts.EXCEPTION_INIT_CODE, "%s");
	public static final BizException PARAM_IS_NULL = new BizException(MemberConsts.EXCEPTION_INIT_CODE+1, "会员管理参数为空==>【%s】");
	public static final BizException PARAM_HINT_WARN = new BizException(MemberConsts.EXCEPTION_INIT_CODE+2, "会员管理非法请求==>【%s】");
	public static final BizException PARAM_HINT_ERROR = new BizException(MemberConsts.EXCEPTION_INIT_CODE+3, "会员管理非法请求==>【%s】");

}

package com.boc.common.core.biz;

import com.boc.common.exception.BizException;
import com.boc.common.page.PageParams;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 构建分页组件
 * <p>@Title: PageBuilder.java 
 * <p>@Package com.boc.common.core.biz 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月28日 下午7:04:57 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class DefaultPageBuilder {
	
	/**
	 * 构建分页
	 * @param pageNo
	 * @param pageSize
	 * @param builder
	 * @return
	 */
	public static <T,P> PageInfo<T> build(PageParams<P> params,AbstractPageBuilder<T,P> builder) throws BizException {
		PageHelper.startPage(params.getPageNo(), params.getPageSize());
		return new PageInfo<T>(builder.build(params.getParams()));
	}
}

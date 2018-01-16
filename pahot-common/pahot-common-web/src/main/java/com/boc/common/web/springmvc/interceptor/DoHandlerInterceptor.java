package com.boc.common.web.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.boc.common.web.permissions.SecurityUtils;
import com.dgg.common.DGGUtils;

/**
 * <p>@Title DoHandlerInterceptor</p>
 * <p>@Description 拦截以.do结尾的所有请求,该拦截器只对cookie信息做权限判断</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月16日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class DoHandlerInterceptor extends HandlerInterceptorAdapter{
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		DGGUtils.getWebManager().setHttpHeader(request, response);
		if(SecurityUtils.getWebSecurityManager().checkToken()) {
			return true;
		}else{

			response.sendRedirect(rooturl(request));
		}
		return false;
	}
	
	private  String rooturl(HttpServletRequest request){
		String path = request.getContextPath();
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	}
}

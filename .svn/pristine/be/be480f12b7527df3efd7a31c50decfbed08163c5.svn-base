package com.boc.common.web.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.boc.common.web.permissions.SecurityUtils;
import com.boc.common.web.permissions.Subject;
import com.dgg.common.DGGUtils;

/**
 * <p>@Title SecHandlerInterceptor</p>
 * <p>@Description 拦截以.sec结尾的请求,该拦截器会刷新session过期时间</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月16日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class SecHandlerInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		DGGUtils.getWebManager().setHttpHeader(request, response);
		if(SecurityUtils.getWebSecurityManager().checkToken()) {
			Subject subject = SecurityUtils.getSubject();
			if(subject == null || !subject.isAuthenticated()) {
				response.sendRedirect(rooturl(request));
			}else{
				//更新session时间
				SecurityUtils.getWebSecurityManager().setSubjectExpire();
				return true;
			}
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

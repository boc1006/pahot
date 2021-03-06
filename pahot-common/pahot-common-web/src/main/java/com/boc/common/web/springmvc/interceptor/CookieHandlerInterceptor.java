package com.boc.common.web.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.boc.common.utils.UUIDUitl;
import com.boc.common.web.cookie.CookieTools;
import com.boc.common.web.permissions.DefaultWebSecurityManager;
import com.boc.common.web.permissions.SecurityUtils;
import com.boc.common.web.permissions.WebSecurityManager;

/**
 * <p>@Title CookieHandlerInterceptor</p>
 * <p>@Description cookie拦截器，用于拦截并创建Cookie信息</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2017年02月22日</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class CookieHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        WebSecurityManager webSecurityManager = SecurityUtils.getWebSecurityManager();
        DefaultWebSecurityManager defaultWebSecurityManager = (DefaultWebSecurityManager)webSecurityManager;
        String cookieName = defaultWebSecurityManager.getCookieName();
        javax.servlet.http.Cookie cookie = CookieTools.getCookieByName(request, cookieName);
        if (cookie == null) {
            SecurityUtils.getWebSecurityManager().setCookie(cookieName, UUIDUitl.generateUpperString(64));
        }
        return true;
    }
}

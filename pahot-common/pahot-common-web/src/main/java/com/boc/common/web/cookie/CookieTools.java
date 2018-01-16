package com.boc.common.web.cookie;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Cookie操作
 * @author Administrator
 *
 */
public class CookieTools {
	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public synchronized static Cookie getCookieByName(HttpServletRequest request,String name){
		Cookie[] cookies = request.getCookies();
		if(null!=cookies){
	        for(Cookie cookie : cookies){
	            if(cookie.getName().equals(name)) {
	            	return cookie;
	            }
	        }
	    }
		return null;
	}
	
	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
}

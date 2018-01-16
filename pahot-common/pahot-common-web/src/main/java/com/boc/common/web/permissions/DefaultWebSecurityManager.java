package com.boc.common.web.permissions;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.boc.common.utils.MD5Helper;
import com.boc.common.web.cookie.CookieTools;
import com.boc.common.web.enums.MenuEnums;
import com.boc.common.web.permissions.bean.SysBean;
import com.boc.common.web.permissions.exception.PermissionsException;

/**
 * 默认的WebSecurityManager实现
 * <p>@Title DefaultWebSecurityManager</p>
 * <p>@Description com.boc.common.web.permissions</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月15日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class DefaultWebSecurityManager extends WebSecurityManager {

	private SubjectSessionManager subjectSessionManager;
	private AuthorizingRealm realm;
	private String cookieName = "JSESSIONID";
	private String tokenName = "PAHOTTOKEN";
	private String path = "/";
	private String domain = "";

	@Override
	public ArrayList<String> getArightsForCodes(MenuEnums menuEnums) {
		String path = menuEnums.getUri();
		String pathHashKey = MD5Helper.encrypt(path);
		return getSubject().getHrightsCodes().get(pathHashKey);
	}

	@Override
	public boolean checkAuthorization(MenuEnums menuEnums) throws PermissionsException {
		String path = menuEnums.getUri();
		Subject subject = getSubject();
		if(subject.isSuperAdmin())return true;

		ArrayList<String> arightsHashKeys = subject.getArightsHashKey();
		ArrayList<String> hrightsHashKeys = subject.getHrightsHashKey();
		boolean hasAccess = false;
		boolean hasHandle = false;
		String pathHashKey = MD5Helper.encrypt(path);
		for(String s : arightsHashKeys) {
			if(s.equals(pathHashKey)) {
				hasAccess = true;
				break;
			}
		}
		if(!hasAccess) {
			throw PermissionsException.ACCOUNT_FAIL.newInstance("【无该URL的访问权限!】");
		}

		if(StringUtils.isEmpty(menuEnums.getCode()))return true;

		String doPathHashKey = MD5Helper.encrypt(path+"!"+menuEnums.getCode());
		for(String s:hrightsHashKeys) {
			if(s.equals(doPathHashKey)) {
				hasHandle = true;
				break;
			}
		}

		if(!hasHandle) {
			throw PermissionsException.ACCOUNT_FAIL.newInstance("无该请求的数据操作权限!");
		}
		return true;
	}

    public static void main(String[] args) {
        System.out.print(MD5Helper.encrypt("role/index.sec"));
    }

	/**
	 * 实现自动登录
	 *
	@Override
	public Subject getSubject() throws PermissionsException {
		HttpServletRequest request = getRequest();
		Cookie cookie = CookieTools.getCookieByName(request, cookieName);
		Cookie token = CookieTools.getCookieByName(request, tokenName);
		TokenEncryption te = realm.getTokenEncryption();
		if(null != cookie) {
			Subject subject = subjectSessionManager.getSubjectSession(cookie.getValue());
			//System.out.println("subject1="+subject);
			if(null == subject && checkToken()) {
				try {
					//隐形自动登录....
					autoLogin(te.getUsername(token.getValue()), te.getPasswd(token.getValue()));
				}catch (Exception e){
					return null;
				}
				subject = subjectSessionManager.getSubjectSession(cookie.getValue());
			}
			//System.out.println("subject text =" +JSONObject.toJSONString(subject).toString());
			return subject;
		}
		return null;
	}
	*/
	
	@Override
	public Subject getSubject() throws PermissionsException {
		HttpServletRequest request = getRequest();
		Cookie cookie = CookieTools.getCookieByName(request, cookieName);
		if(null != cookie) {
			return subjectSessionManager.getSubjectSession(cookie.getValue());
		}
		return null;
	}

	/**
	 * 重新进行登录验证
	 * @param username
	 * @param passwd
	 * @throws Exception
	 */
	private void autoLogin(String username, String passwd) throws PermissionsException {
		HttpServletRequest request = getRequest();
		Cookie cookie = CookieTools.getCookieByName(request, cookieName);
		Subject subject = new Subject();
		Session session = new Session();
		session.setId(cookie.getValue());
		session.setHost(getIpAddr());
		subject.setSession(session);
		setSubject(subject);
		try {
			AuthenticationInfo auth = realm.doGetAuthenticationInfo(username, passwd);
			subject = getSubject();
			subject.setAuthenticated(true);
			subject.setArights(auth.getArights());
			subject.setHrights(auth.getHrights());
			subject.setArightsHashKey(auth.getArightsHashKey());
			subject.setHrightsHashKey(auth.getHrightsHashKey());
			subject.setHrightsCodes(auth.geHrightsCodes());
			subject.setSidArray(auth.getSidArray());
			subject.setUserid(realm.getTokenEncryption().getUserid(auth.getCredentials()));
			subject.setUsername(realm.getTokenEncryption().getUsername(auth.getCredentials()));
			setSubject(subject);
		}catch(PermissionsException e) {
			logout();
			throw e;
		}
	}

	@Override
	public void setSubjectExpire() {
		Cookie cookie = CookieTools.getCookieByName(getRequest(), cookieName);
		subjectSessionManager.setSubjectSessionExpire(cookie.getValue(),expire);
	}

	@Override
	public boolean checkToken() {
		Cookie token = CookieTools.getCookieByName(getRequest(), tokenName);
		return null != token && realm.getTokenEncryption().decrypt(token.getValue());
	}

	@Override
	public void setSubject(Subject subject) {
		Cookie cookie = CookieTools.getCookieByName(getRequest(), cookieName);
		subjectSessionManager.setSubjectSession(cookie.getValue(), subject,expire);
	}

	@Override
	public void login(String username, String passwd,int maxAge,String ...args) throws PermissionsException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();

		Cookie cookie = CookieTools.getCookieByName(request, cookieName);

		Subject subject = new Subject();
		Session session = new Session();
		session.setId(cookie.getValue());
		session.setHost(getIpAddr());
		subject.setSession(session);

		setSubject(subject);
		try {
			AuthenticationInfo auth = realm.doGetAuthenticationInfo(username, passwd,args);
			subject = getSubject();
			subject.setAuthenticated(true);
			subject.setArights(auth.getArights());
			subject.setHrights(auth.getHrights());
			subject.setArightsHashKey(auth.getArightsHashKey());
			subject.setHrightsHashKey(auth.getHrightsHashKey());
			subject.setHrightsCodes(auth.geHrightsCodes());
			subject.setSidArray(auth.getSidArray());
			subject.setUserid(realm.getTokenEncryption().getUserid(auth.getCredentials()));
			subject.setUsername(realm.getTokenEncryption().getUsername(auth.getCredentials()));
			setSubject(subject);

			cookie.setMaxAge(maxAge);
			cookie.setHttpOnly(true);
			cookie.setDomain(domain);
			cookie.setPath(path);

			Cookie cookieToken = new Cookie(tokenName, auth.getCredentials());
			cookieToken.setMaxAge(maxAge);
			cookieToken.setHttpOnly(true);
			cookieToken.setDomain(domain);
			cookieToken.setPath(path);
//			System.out.println("顶级域名："+domain);
			response.addCookie(cookie);
			response.addCookie(cookieToken);
			response.setHeader("P3P","CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
		} catch (PermissionsException e) {
			logout();
			throw e;
		}

	}

	@Override
	public void logout() {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		Cookie cookie = CookieTools.getCookieByName(request, cookieName);
		subjectSessionManager.delSubjectSession(cookie.getValue());

		cookie.setMaxAge(0);
		cookie.setHttpOnly(true);
		cookie.setDomain(domain);
		cookie.setPath(path);

		Cookie cookieToken = new Cookie(tokenName, null);
		cookieToken.setMaxAge(0);
		cookieToken.setDomain(domain);
		cookieToken.setHttpOnly(true);
		cookieToken.setPath(path);

		response.addCookie(cookie);
		response.addCookie(cookieToken);
		response.setHeader("P3P","CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
	}

	public SubjectSessionManager getSubjectSessionManager() {
		return subjectSessionManager;
	}


	public void setSubjectSessionManager(SubjectSessionManager subjectSessionManager) {
		this.subjectSessionManager = subjectSessionManager;
	}


	public AuthorizingRealm getRealm() {
		return realm;
	}

	public void setRealm(AuthorizingRealm realm) {
		this.realm = realm;
	}

	public String getCookieName() {
		return cookieName;
	}

	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}

	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}


	public String getTokenName() {
		return tokenName;
	}


	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}


	private HttpServletRequest getRequest() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return req;
	}

	private HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		return response;
	}

	private String getIpAddr() {
		HttpServletRequest request = getRequest();
	    String ip = request.getHeader("x-forwarded-for");
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip;
	}

	@Override
	public void setCookie(String key, String value) {
		HttpServletResponse response = getResponse();
		Cookie cookie = new Cookie(key, realm.getTokenEncryption().encryptStr(value));
		cookie.setMaxAge(-1);
		cookie.setHttpOnly(true);
		cookie.setPath(path);
		cookie.setDomain(domain);

		response.setHeader("P3P","CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
		response.addCookie(cookie);
	}

	@Override
	public String getCookie(String key) {
		Cookie token = CookieTools.getCookieByName(getRequest(), key);
		return realm.getTokenEncryption().decryptStr(token.getValue());
	}

	@Override
	public ArrayList<SysBean> getUserSysArray() {
		return getSubject().getSidArray();
	}

}

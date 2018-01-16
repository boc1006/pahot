package com.boc.common.web.permissions;

import java.util.ArrayList;

import com.boc.common.web.enums.MenuEnums;
import com.boc.common.web.permissions.bean.SysBean;
import com.boc.common.web.permissions.exception.PermissionsException;

/**
 * spring mvc安全管理器
 * <p>@Title WebSecurityManager</p>
 * <p>@Description com.boc.common.web.permissions</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月15日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public abstract class WebSecurityManager {

	/**
	 * 默认过期时间,30分钟
	 */
	public int expire = 1800;

	/**
	 * 系统编号
	 */
	public String sid;

	/**
	 * 获取Subject对象
	 * @return
	 */
	public abstract Subject getSubject() throws PermissionsException;

	/**
	 * 保存Subject
	 * @param subject
	 */
	public abstract void setSubject(Subject subject);

	/**
	 * 更新Subject过期时间
	 */
	public abstract void setSubjectExpire();

	/**
	 * 用户认证登录
	 * @param username 登录名
	 * @param passwd 加密后的密码串
	 * @param maxAge cookie有效时间(秒),-1表示关闭浏览器，cookie就会消失
	 * @param args 其它附加参数
	 */
	public abstract void login (String username,String passwd,int maxAge,String ...args) throws PermissionsException;

	/**
	 * 用户认证登出
	 */
	public abstract void logout();

	/**
	 * 检查token正确性
	 * @return
	 */
	public abstract boolean checkToken();

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	/**
	 * 校验用户权限
	 * @param menuEnums 菜单枚举
	 * @return
	 * @throws PermissionsException
	 */
	public abstract boolean checkAuthorization(MenuEnums menuEnums) throws PermissionsException;

	 /**
	 * 设置COOKIE
	 * @param key
	 * @param value
	 */
	public abstract void setCookie(String key,String value);

	/**
	 * 获取COOKIE
	 * @param key
	 */
	public abstract String getCookie(String key);

	/**
	 * 获取某个访问权限下面的所有操作权限代码
	 */
	public abstract ArrayList<String> getArightsForCodes(MenuEnums menuEnums);

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	/**
	 * 获取用户已授权的系统列表
	 * @return
	 */
	public abstract ArrayList<SysBean> getUserSysArray();

}

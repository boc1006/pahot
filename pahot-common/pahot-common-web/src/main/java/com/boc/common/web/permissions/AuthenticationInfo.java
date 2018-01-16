package com.boc.common.web.permissions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import com.boc.common.web.permissions.bean.SysBean;

/**
 * 权限认证信息
 * <p>@Title AuthenticationInfo</p>
 * <p>@Description com.boc.common.web.permissions</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月15日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public interface AuthenticationInfo {
	
	/**
	 * 获取认证证书
	 * @return
	 */
	public String getCredentials();
	/**
	 * 获取菜单权限
	 * @return
	 */
	public BigInteger getArights();
	
	/**
	 * 获取数据访问权限
	 * @return
	 */
	public BigInteger getHrights();
	
	/**
	 * 获取用户访问权限HashKey
	 * @return
	 */
	public ArrayList<String> getArightsHashKey();
	
	/**
	 * 获取用户操作权限HashKey
	 * @return
	 */
	public ArrayList<String> getHrightsHashKey();
	
	/**
	 * 获取用户菜单权限下对应的所有操作权限
	 * @return
	 */
	public HashMap<String,ArrayList<String>> geHrightsCodes();
	
	/**
	 * 获取用户已授权的系统编号列表
	 * @return
	 */
	public ArrayList<SysBean> getSidArray();
	
}

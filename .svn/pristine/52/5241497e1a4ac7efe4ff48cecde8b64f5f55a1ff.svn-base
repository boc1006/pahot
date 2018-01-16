package com.boc.common.web.permissions;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import com.boc.common.utils.RightsHelper;
import com.boc.common.web.permissions.bean.SysBean;

/**
 * 权限对象
 * <p>@Title Subject</p>
 * <p>@Description com.boc.common.web.permissions</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月14日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class Subject implements Serializable{
	
	private static final long serialVersionUID = 2646889549160539128L;
	private boolean authenticated;
	private Session session;
	private BigInteger arights=new BigInteger("0");
	private BigInteger hrights=new BigInteger("0");
	private ArrayList<String> arightsHashKey;
	private ArrayList<String> hrightsHashKey;
	private ArrayList<SysBean> sidArray;
	/**
	 * key:访问路径HashKey
	 * value:访问路径下对应的操作权限Code集合
	 */
	private HashMap<String,ArrayList<String>> hrightsCodes;
	private String userid;
	private String username;

	public boolean isSuperAdmin() {
		return RightsHelper.testRights(arights, 0) && RightsHelper.testRights(hrights, 0);
	}

	public BigInteger getArights() {
		return arights;
	}


	public void setArights(BigInteger arights) {
		this.arights = arights;
	}


	public BigInteger getHrights() {
		return hrights;
	}


	public void setHrights(BigInteger hrights) {
		this.hrights = hrights;
	}


	public Session getSession() {
		return session;
	}


	public void setSession(Session session) {
		this.session = session;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
	
	public void reloadSession(Session session){
		this.session = session;
		SecurityUtils.getWebSecurityManager().setSubject(this);
	}

	public ArrayList<String> getArightsHashKey() {
		return arightsHashKey;
	}

	public void setArightsHashKey(ArrayList<String> arightsHashKey) {
		this.arightsHashKey = arightsHashKey;
	}

	public ArrayList<String> getHrightsHashKey() {
		return hrightsHashKey;
	}

	public void setHrightsHashKey(ArrayList<String> hrightsHashKey) {
		this.hrightsHashKey = hrightsHashKey;
	}

	public HashMap<String, ArrayList<String>> getHrightsCodes() {
		return hrightsCodes;
	}

	public void setHrightsCodes(HashMap<String, ArrayList<String>> hrightsCodes) {
		this.hrightsCodes = hrightsCodes;
	}

	public ArrayList<SysBean> getSidArray() {
		return sidArray;
	}

	public void setSidArray(ArrayList<SysBean> sidArray) {
		this.sidArray = sidArray;
	}

}

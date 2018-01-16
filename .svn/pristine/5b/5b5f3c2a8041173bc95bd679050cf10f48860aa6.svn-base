package com.boc.common.web.permissions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import com.boc.common.web.permissions.bean.SysBean;

/**
 * 默认的权限认证实现
 * <p>@Title SimpleAuthenticationInfo</p>
 * <p>@Description com.boc.common.web.permissions</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月15日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class SimpleAuthenticationInfo implements AuthenticationInfo {
	
	private String token;
	private BigInteger arights;
	private BigInteger hrights;
	private ArrayList<String> arightsHashKey;
	private ArrayList<String> hrightsHashKey;
	private HashMap<String,ArrayList<String>> hrightsCodesMap;
	private ArrayList<SysBean> sidArray;
	public SimpleAuthenticationInfo(String token,BigInteger arights,BigInteger hrights,ArrayList<String> arightsHashKey,ArrayList<String> hrightsHashKey,HashMap<String,ArrayList<String>> hrightsCodesMap,ArrayList<SysBean> sidArray) {
		this.token = token;
		this.arights = arights;
		this.hrights = hrights;
		this.arightsHashKey = arightsHashKey;
		this.hrightsHashKey = hrightsHashKey;
		this.hrightsCodesMap = hrightsCodesMap;
		this.sidArray = sidArray;
	}

	@Override
	public String getCredentials() {
		return this.token;
	}

	@Override
	public BigInteger getArights() {
		return this.arights;
	}

	@Override
	public BigInteger getHrights() {
		return this.hrights;
	}

	@Override
	public ArrayList<String> getArightsHashKey() {
		return this.arightsHashKey;
	}

	@Override
	public ArrayList<String> getHrightsHashKey() {
		return this.hrightsHashKey;
	}

	@Override
	public HashMap<String, ArrayList<String>> geHrightsCodes() {
		return hrightsCodesMap;
	}

	@Override
	public ArrayList<SysBean> getSidArray() {
		return sidArray;
	}
}

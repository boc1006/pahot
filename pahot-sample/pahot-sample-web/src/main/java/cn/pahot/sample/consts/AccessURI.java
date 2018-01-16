package cn.pahot.sample.consts;

/**
 * 定义当前web应用所有URL
 * <p>@Title: AccessURI.java 
 * <p>@Package cn.pahot.sample.controller 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月4日 上午10:46:31 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface AccessURI {
	
	/**定义一个URL路径,此路径和在运营后台菜单管理中菜单的访问路径保持一致**/
	public static final String ADD_URL = "sample/addUser.sec";
	/**定义一个URL的操作权限代码,此代码必须后运营后台菜单管理中的操作权限代码保持一致**/
	public static final String ADD_URL_ADD = "ADD";
	public static final String ADD_URL_UPD = "UPD";
	public static final String ADD_URL_DEL = "DEL";
	public static final String ADD_URL_QUERY = "QUERY";
}

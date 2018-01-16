package com.boc.common.web.permissions;

/**
 * TOKEN加、解密算法接口
 * <p>@Title TokenEncryption</p>
 * <p>@Description com.boc.common.web.permissions</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月15日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public interface TokenEncryption {
	/**
	 * 用户信息加密
	 * @param username
	 * @param passwd 加密后的串
	 * @return
	 */
//	public String encrypt(String userid,String username,String passwd);
	public String encrypt(String ...args);
	
	/**
	 * 解密token
	 * @param token
	 * @return
	 */
	public boolean decrypt (String token);
	
	/**
	 * 获取解密后的用户名
	 * @param token
	 * @return
	 */
	public String getUsername (String token);
	
	/**
	 * 获取解密后的密码
	 * @param token
	 * @return
	 */
	public String getPasswd (String token);
	
	/**
	 * 获取解密后的userid
	 * @param token
	 * @return
	 */
	public String getUserid (String token);
	
	/**
	 * 对指定串加密
	 * @param str
	 * @return
	 */
	public String encryptStr(String str);
	/**
	 * 对指定串解密
	 * @param str
	 * @return
	 */
	public String decryptStr(String str);
	
	/**
	 * 解密token并获取指定索引的值
	 * @param token
	 * @param idx
	 * @return
	 */
	public String getDecryptString(String token,int idx);
}

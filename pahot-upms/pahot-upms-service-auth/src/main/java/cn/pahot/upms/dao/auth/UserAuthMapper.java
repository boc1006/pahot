package cn.pahot.upms.dao.auth;

import java.util.List;

import com.boc.common.metatype.DTO;
import com.github.pagehelper.Page;

/**
 * 用户权限认证DAO接口定义
 * <p>@Title: UserAuthMapper.java 
 * <p>@Package cn.pahot.upms.dao.auth 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月21日 下午12:46:46 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface UserAuthMapper<T> {
	
	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	public T queryUserByUsername(String username);
	
	/**
	 * 根据用户ID查询用户可用的角色列表
	 * @param param
	 * @return
	 */
	public List<T> queryUserRoleByUID(Integer uid);
	
	/**
	 * 根据系统编号查询系统菜单
	 * @param param
	 * @return
	 */
	public List<T> querySysMenu(DTO param);
	
	/**
	 * 查询系统菜单树
	 * @param param
	 * @return
	 */
	public List<T> querySysMenuTree(DTO param);


	/**
	 * 查询系统菜单操作权限
	 * @param param
	 * @return
	 */
	public List<T> querySysRightsMenu(DTO param);
	
}

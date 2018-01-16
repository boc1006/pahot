package cn.pahot.upms.auth.facade;

import java.math.BigInteger;
import java.util.List;

import com.boc.common.exception.BizException;
import com.boc.common.exception.BizException;

import cn.pahot.upms.auth.entity.MenuHashKeyBean;
import cn.pahot.upms.auth.entity.RoleEntity;
import cn.pahot.upms.auth.entity.SystemConfEntity;
import cn.pahot.upms.auth.entity.UserEntity;

/**
 * 系统用户权限认证服务接口定义
 * <p>@Title: UserAuthFacade.java 
 * <p>@Package cn.pahot.upms.auth.facade 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月21日 上午11:59:17 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface UserAuthFacade {
	/**
	 * 根据用户名获取系统用户信息
	 * @param username
	 * @return
	 * @throws BizException
	 */
	public UserEntity queryUserByUsername(String username) throws BizException;
	
	/**
	 * 根据用户ID获取用户角色列表
	 * @param uid
	 * @return
	 * @throws BizException
	 */
    public List<RoleEntity> queryUserRoleByUID(Integer uid) throws BizException;
    
    /**
     * 根据用户访问权限查询可用的菜单树列表 *
     *
     * @param isAdmin
     * @param arights
     * @return
     * @throws BizException
     */
    public List<SystemConfEntity> queryUserMenulistByArights(boolean isAdmin, BigInteger arights) throws BizException;


    /**
     * 获取用户菜单及权限HashKey *
     *
     * @param arights
     * @param hrights
     * @return
     */
    public MenuHashKeyBean getUserMenuHashKey(BigInteger arights, BigInteger hrights) throws BizException;
    
}

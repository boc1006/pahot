package cn.pahot.upms.service.auth;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boc.common.exception.BizException;
import com.github.pagehelper.PageInfo;

import cn.pahot.upms.auth.entity.MenuHashKeyBean;
import cn.pahot.upms.auth.entity.RoleEntity;
import cn.pahot.upms.auth.entity.SystemConfEntity;
import cn.pahot.upms.auth.entity.UserEntity;
import cn.pahot.upms.auth.facade.UserAuthFacade;
import cn.pahot.upms.biz.auth.UserAuthBiz;

/**
 * 系统用户权限认证服务接口实现
 * <p>@Title: UserAuthService.java 
 * <p>@Package cn.pahot.upms.auth.service 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月21日 下午12:43:30 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights eserved.
 */
@Service("userAuthFacade")
public class UserAuthService implements UserAuthFacade {
	
	@Resource
	private UserAuthBiz userAuthBiz;

	@Override
	public UserEntity queryUserByUsername(String username) throws BizException {
		return userAuthBiz.queryUserByUsername(username);
	}

	@Override
	public List<RoleEntity> queryUserRoleByUID(Integer uid) throws BizException {
		return userAuthBiz.queryUserRoleByUID(uid);
	}

	@Override
	public List<SystemConfEntity> queryUserMenulistByArights(boolean isAdmin, BigInteger arights) throws BizException {
		return userAuthBiz.queryUserMenulistByArights(isAdmin, arights);
	}

	@Override
	public MenuHashKeyBean getUserMenuHashKey(BigInteger arights, BigInteger hrights) throws BizException {
		return userAuthBiz.getUserMenuHashKey(arights, hrights);
	}

}

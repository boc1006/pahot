package cn.pahot.upms.service;

import cn.pahot.upms.biz.RoleBiz;
import cn.pahot.upms.entity.RoleEntity;
import cn.pahot.upms.entity.UserRoleEntity;
import cn.pahot.upms.facade.RoleFacade;

import com.boc.common.core.test.annotation.DubboProviderMethod;
import com.boc.common.enums.PropertiesHelper;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色管理服务实现类
 * <p>@Title: RoleService.java 
 * <p>@Package cn.pahot.upms.service 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月7日 上午10:57:30 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@Service("roleFacade")
public class RoleService implements RoleFacade {
	
	@Autowired
	private RoleBiz roleBiz;

	@DubboProviderMethod(name="黄杰",system="UPMS系统",systemNum="100000",ip="192.168.3.113")
	@Override
	public List<RoleEntity> queryRolesBySystemId(String sysId, String state, boolean expire) throws BizException{
		return roleBiz.queryRolesBySystemId(sysId, state, expire);
	}

	@Override
	public Integer saveRole(RoleEntity roleEntity) throws BizException {
		return roleBiz.saveRole(roleEntity);
	}

	@Override
	public void updateRole(RoleEntity roleEntity) throws BizException {
		roleBiz.updateRole(roleEntity);
	}

	@Override
	public void handleRoleState(DTO<String, String> param) {
		roleBiz.handleRoleState(param);
	}

	@Override
	public RoleEntity queryRoleById(Integer roleId) throws BizException {
		return roleBiz.queryRoleById(roleId);
	}

	@Override
	public void roleByGrant(DTO<String,String> param) throws BizException {
		roleBiz.roleByGrant(param);
	}


	@Override
	public List<UserRoleEntity> queryUserRoleBySystemIdAndUserId(Integer sid, Integer uid,Integer rid) throws BizException {
		return roleBiz.queryUserRole(sid, uid,rid);
	}

	@Override
	public Integer saveUserRole(UserRoleEntity userRoleEntity) throws BizException {
		return roleBiz.saveUserRole(userRoleEntity);
	}

	@Override
	public void delUserRole(Integer id) throws BizException {
		roleBiz.delUserRole(id);
	}



//	@Override
//	public List<UserRoleEntity> queryUserRoleBySystemIdAndUserId(Integer sid, Integer uid) throws BizException {
//		return userRoleBiz.queryUserRole(sid, uid);
//	}
//
//	@Override
//	public Integer saveUserRole(UserRoleEntity userRoleEntity) throws BizException {
//		return userRoleBiz.saveUserRole(userRoleEntity);
//	}
//
//	@Override
//	public void delUserRole(Integer id) throws BizException {
//		userRoleBiz.delUserRole(id);
//	}
}

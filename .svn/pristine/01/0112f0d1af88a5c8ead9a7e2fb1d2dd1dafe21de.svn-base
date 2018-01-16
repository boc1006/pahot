package cn.pahot.upms.realm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import com.boc.common.utils.RightsHelper;
import com.boc.common.web.permissions.AuthenticationInfo;
import com.boc.common.web.permissions.AuthorizingRealm;
import com.boc.common.web.permissions.SecurityUtils;
import com.boc.common.web.permissions.SimpleAuthenticationInfo;
import com.boc.common.web.permissions.bean.SysBean;
import com.boc.common.web.permissions.exception.PermissionsException;

import cn.pahot.upms.auth.entity.MenuHashKeyBean;
import cn.pahot.upms.auth.entity.RoleEntity;
import cn.pahot.upms.auth.entity.SystemConfEntity;
import cn.pahot.upms.auth.entity.UserEntity;
import cn.pahot.upms.auth.enums.UserStateEnum;
import cn.pahot.upms.auth.facade.UserAuthFacade;

/**
 * 系统用户登录接口
 * <p>@Title: LoginRealm.java 
 * <p>@Package cn.pahot.upms.realm 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月21日 下午3:44:55 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class LoginRealm extends AuthorizingRealm {

	@Resource
	private UserAuthFacade userAuthFacade;
	
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(String userName, String passwd, String... params)
			throws PermissionsException {
		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(passwd)) {
			throw PermissionsException.ACCOUNT_IS_NULL.newInstance("用户名");
		}
		
		//进行登录
		UserEntity ue = userAuthFacade.queryUserByUsername(userName);
		
		if(StringUtils.isEmpty(ue)) {
			throw PermissionsException.ACCOUNT_FAIL.newInstance(userName);
		}

		if(!passwd.equals(ue.getPasswd())) {
			throw PermissionsException.ACCOUNT_FAIL.newInstance(userName);
		}
		
		if(!ue.getOrgstate().equals("01")) {
			throw PermissionsException.ACCOUNT_FAIL.newInstance("当前用户所属部门不存在或已被停用!");
		}
		
		UserStateEnum use = UserStateEnum.toEnum(ue.getState());
		if(use == UserStateEnum.USER_STATE_LOCK || use == UserStateEnum.USER_STATE_LOGOUT) {
			throw PermissionsException.PERMISSION_FAIL.newInstance(use.value);
		}

		//获取用户角色权限
		List<RoleEntity> lstRole = userAuthFacade.queryUserRoleByUID(ue.getId());
		BigInteger arights = BigInteger.ZERO;
		BigInteger hrights = BigInteger.ZERO;
		if(!StringUtils.isEmpty(lstRole) && lstRole.size() > 0) {
			for(RoleEntity re:lstRole) {
				if (!StringUtils.isEmpty(re.getArights())){
					arights = arights.or(new BigInteger(re.getArights()));
				}
				if(!StringUtils.isEmpty(re.getHrights())){
					hrights = hrights.or(new BigInteger(re.getHrights()));
				}
			}
		}else{
			throw PermissionsException.PERMISSION_FAIL.newInstance("该用户未授权!");
		}

		MenuHashKeyBean hashkey = userAuthFacade.getUserMenuHashKey(arights, hrights);

		ArrayList<SystemConfEntity> lstMe = (ArrayList<SystemConfEntity>) userAuthFacade.queryUserMenulistByArights(RightsHelper.testRights(arights, 0), arights);
		SecurityUtils.getSubject().getSession().setAttribute("menu", lstMe);
		ArrayList<SysBean> sidArray = new ArrayList<SysBean>();
		for(SystemConfEntity scf:lstMe) {
			SysBean sb = new SysBean();
			sb.setId(scf.getId());
			sb.setName(scf.getName());
			sb.setType(scf.getType());
			sb.setIcon(scf.getLogo());
			sidArray.add(sb);
		}

		return new SimpleAuthenticationInfo(getTokenEncryption().encrypt(String.valueOf(ue.getId()),userName, passwd),
				arights,hrights,hashkey.getArightsHashKey(),hashkey.getHrightsHashKey(),hashkey.getHrightsMap(),sidArray);
	}

}

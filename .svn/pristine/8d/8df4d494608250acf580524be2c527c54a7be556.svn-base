package cn.pahot.upms.biz.auth;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;
import com.boc.common.utils.RightsHelper;

import cn.pahot.upms.auth.entity.MenuEntity;
import cn.pahot.upms.auth.entity.MenuHashKeyBean;
import cn.pahot.upms.auth.entity.MenuRightsEntity;
import cn.pahot.upms.auth.entity.RoleEntity;
import cn.pahot.upms.auth.entity.SystemConfEntity;
import cn.pahot.upms.auth.entity.UserEntity;
import cn.pahot.upms.auth.exception.UserAuthBizException;
import cn.pahot.upms.dao.auth.UserAuthMapper;

/**
 * 用户权限认证业务逻辑处理
 * <p>@Title: UserAuthBiz.java 
 * <p>@Package cn.pahot.upms.biz.auth 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月21日 下午12:44:41 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@Service("userAuthBiz")
public class UserAuthBiz{
	
	@Resource
	private UserAuthMapper<UserEntity> userDao;
	
	@Resource
	private UserAuthMapper<MenuEntity> menuDao;
	
	@Resource
	private UserAuthMapper<SystemConfEntity> sysMenuDao;
	
	@Resource
	private UserAuthMapper<MenuRightsEntity> menuRightsDao;
	
	@Resource
	private UserAuthMapper<RoleEntity> roleDao;
	
	/**
	 * 根据用户登帐号获取用户信息
	 * @param username
	 * @return
	 * @throws BizException
	 */
	public UserEntity queryUserByUsername(String username) throws BizException{
		if(StringUtils.isEmpty(username)) {
			 throw UserAuthBizException.PARAM_IS_NULL.newInstance("登录帐号");
		}
		return userDao.queryUserByUsername(username);
	}
	
	/**
	 * 根据用户编号获取当前用户所有角色列表
	 * @param uid
	 * @return
	 * @throws BizException
	 */
	public List<RoleEntity> queryUserRoleByUID(Integer uid) throws BizException {
		if(StringUtils.isEmpty(uid)) {
			 throw UserAuthBizException.PARAM_IS_NULL.newInstance("uid");
		}
		return roleDao.queryUserRoleByUID(uid);
	}
	
	/**
     * 根据用户访问权限查询可用的菜单树列表 *
     * @param isAdmin
     * @param arights
     * @return
     */
    public List<SystemConfEntity> queryUserMenulistByArights(boolean isAdmin, BigInteger arights) throws BizException {
        DTO<String,String> param = new BaseDTO<String,String>();
        param.put("state", "01");
        if (isAdmin) {
            return sysMenuDao.querySysMenu(param);
        }
        if (StringUtils.isEmpty(arights) || arights.equals(BigInteger.ZERO)) {
            throw UserAuthBizException.PERMISSION_IS_NULL.newInstance("arights");
        }
        List<SystemConfEntity> resultList = new ArrayList<SystemConfEntity>();
        List<SystemConfEntity> lstMenus = sysMenuDao.querySysMenu(param);
        for (SystemConfEntity sce : lstMenus) {
            List<MenuEntity> lstNew = new ArrayList<MenuEntity>();
            List<MenuEntity> lstMe = sce.getList();
            for (MenuEntity me : lstMe) {
                if (RightsHelper.testRights(arights, me.getId())) {
                    lstNew.add(me);
                }
            }
            if (lstNew.size() > 0) {
                sce.setList(lstNew);
                resultList.add(sce);
            }
        }
        return resultList;
    }
    
    /**
     * 获取用户菜单及权限HashKey *
     *
     * @param arights
     * @param hrights
     * @return
     * @throws BizException
     */
    public MenuHashKeyBean getUserMenuHashKey(BigInteger arights, BigInteger hrights) throws BizException {
        if (StringUtils.isEmpty(arights) || StringUtils.isEmpty(hrights)) {
            throw UserAuthBizException.PERMISSION_IS_NULL.newInstance("arights|hrights");
        }
        MenuHashKeyBean result = new MenuHashKeyBean();
        DTO<String,String> param = new BaseDTO<String,String>();
        param.put("state", "01");
        HashMap<String, ArrayList<String>> hrightsMap = new HashMap<String, ArrayList<String>>();
        Map<Integer, String> tempMap = new HashMap<Integer, String>();
        List<MenuEntity> lstMenus = menuDao.querySysMenuTree(param);
        ArrayList<String> arightsHashKey = new ArrayList<String>();
        for (MenuEntity me : lstMenus) {
            if (RightsHelper.testRights(arights, 0) || (RightsHelper.testRights(arights, me.getId()) && !StringUtils.isEmpty(me.getHashkey()))) {
                arightsHashKey.add(me.getHashkey());
                hrightsMap.put(me.getHashkey(), new ArrayList<String>());
                tempMap.put(me.getId(), me.getHashkey());
            }
        }
        result.setArightsHashKey(arightsHashKey);

        if (!StringUtils.isEmpty(hrights)) {
            List<MenuRightsEntity> lstMenusRights = menuRightsDao.querySysRightsMenu(param);
            ArrayList<String> hrightsHashKey = new ArrayList<String>();
            for (MenuRightsEntity mre : lstMenusRights) {
                if (RightsHelper.testRights(arights, 0) || (RightsHelper.testRights(hrights, mre.getId()) && !StringUtils.isEmpty(mre.getHashkey()))) {
                    hrightsHashKey.add(mre.getHashkey());
                    if (tempMap.containsKey(mre.getMid()) && hrightsMap.containsKey(tempMap.get(mre.getMid())))
                        hrightsMap.get(tempMap.get(mre.getMid())).add(mre.getCode());
                }
            }
            result.setHrightsHashKey(hrightsHashKey);
        }
        result.setHrightsMap(hrightsMap);
        return result;
    }
}

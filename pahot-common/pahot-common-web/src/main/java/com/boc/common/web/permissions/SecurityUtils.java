package com.boc.common.web.permissions;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import com.boc.common.context.ApplicationContextAware;
import com.boc.common.web.permissions.bean.SysBean;
import com.boc.common.web.permissions.exception.PermissionsException;

/**
 * 权限管理工具类
 * <p>@Title SecurityUtils</p>
 * <p>@Description com.boc.common.web.permissions</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月14日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class SecurityUtils {
	
	private static final WebSecurityManager webSecurityManager;
	
	static {
		ApplicationContext context = ApplicationContextAware.getApplicationContext();
		webSecurityManager = (WebSecurityManager) context.getBean("webSecurityManager");
	}

	/**
	 * 获取Subject对象
	 * @return
	 * @throws Exception 
	 */
	public static Subject getSubject() throws PermissionsException {
		return webSecurityManager.getSubject();
	}

	/**
	 * 获取WebSecurityManager对象
	 */
	public static WebSecurityManager getWebSecurityManager(){
		return webSecurityManager;
	}
	
	/**
	 * 判断子系统是否已对该用户授权
	 * @param sid
	 * @return
	 */
    public static boolean permissionToSystem(final String sid) {
        if (StringUtils.isEmpty(sid)) {
            throw PermissionsException.INSTANCE.newInstance("sid参数不能为空");
        }
        
        List<SysBean> lsb = getSubject().getSidArray();
        
        for(SysBean sb:lsb) {
        	if(sid.equals(sb.getId())){
        		return true;
        	}
        }

        throw PermissionsException.PERMISSION_FAIL.newInstance("该用户无该系统授权信息");
    }
}

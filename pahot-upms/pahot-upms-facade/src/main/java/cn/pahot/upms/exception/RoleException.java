package cn.pahot.upms.exception;

import com.boc.common.exception.BizException;

import cn.pahot.upms.consts.UPMSConst;

/**
 * 角色管理异常定义
 * <p>@Title: RoleException.java
 * <p>@Package cn.pahot.upms.exception
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com
 * <p>@date 2017年12月7日 上午11:18:54
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class RoleException extends BizException {
    private static final long serialVersionUID = 1L;
    public static final BizException INSTANCE = new BizException(UPMSConst.EXCEPTION_INIT_CODE, "%s");
    public static final BizException PARAM_HINT_WARN = new BizException(UPMSConst.EXCEPTION_INIT_CODE + 20, "角色管理操作警告==>%s");
    public static final BizException PARAM_HINT_ERROR = new BizException(UPMSConst.EXCEPTION_INIT_CODE + 21, "角色管理操作非法==>%s");
    public static final BizException PARAM_IS_NULL = new BizException(UPMSConst.EXCEPTION_INIT_CODE + 22, "角色管理参数为空==>%s");
}

package cn.pahot.upms.exception;

import cn.pahot.upms.consts.UPMSConst;
import com.boc.common.exception.BizException;

/**
 * 用户角色管理
 */
public class UserRoleException extends BizException {
    public static final int EXCEPTION_CODE = UPMSConst.EXCEPTION_INIT_CODE + 1000;
    public static final BizException INSTANCE = new BizException(EXCEPTION_CODE, "%s");
    public static final BizException PARAM_HINT_ERROR = new BizException(UPMSConst.EXCEPTION_INIT_CODE + 1, "角色管理操作非法==>%s");
    public static final BizException PARAM_IS_NULL = new BizException(EXCEPTION_CODE + 2, "用户角色关系管理参数为空==>%s");
}

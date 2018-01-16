package cn.pahot.upms.exception;

import cn.pahot.upms.consts.UPMSConst;
import com.boc.common.exception.BizException;

/**
 * @Author: yuzhiyun
 * @Date: 2017/12/11
 * @Description: 系统变量异常
 */
public class SystemSettingException extends BizException {

    public static final BizException INSTANCE = new BizException(UPMSConst.EXCEPTION_INIT_CODE, "%s");
    public static final BizException PARAM_HINT_WARN = new BizException(UPMSConst.EXCEPTION_INIT_CODE + 36, "系统变量管理操作警告==>%s");
    public static final BizException PARAM_HINT_ERROR = new BizException(UPMSConst.EXCEPTION_INIT_CODE + 37, "系统变量管理操作非法==>%s");
    public static final BizException PARAM_IS_NULL = new BizException(UPMSConst.EXCEPTION_INIT_CODE + 38, "系统变量管理参数为空==>%s");

}
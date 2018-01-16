package cn.pahot.business.exception;

import com.boc.common.exception.BizException;

/**
 * 商品sku属性异常
 */
public class MerchantException {
    public static final int EXCEPTION_CODE = ExceptionCode.ERRORCODR_PARAMS_NULL + 1001;
    public static final BizException INSTANCE = new BizException(EXCEPTION_CODE, "%s");
    public static final BizException PARAM_HINT_ERROR = new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL + 1, "商家管理操作非法==>%s");
    public static final BizException PARAM_IS_NULL = new BizException(EXCEPTION_CODE + 2, "商家管理参数为空==>%s");
}

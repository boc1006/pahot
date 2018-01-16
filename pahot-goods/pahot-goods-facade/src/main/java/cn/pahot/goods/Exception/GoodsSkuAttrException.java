package cn.pahot.goods.Exception;

import com.boc.common.exception.BizException;

import static cn.pahot.goods.consts.GoodsConst.*;

/**
 * 商品sku属性异常
 */
public class GoodsSkuAttrException {
    public static final int EXCEPTION_CODE = EXCEPTION_INIT_CODE + 1001;
    public static final BizException INSTANCE = new BizException(EXCEPTION_CODE, "%s");
    public static final BizException PARAM_HINT_ERROR = new BizException(EXCEPTION_INIT_CODE + 1, "商品sku属性管理操作非法==>%s");
    public static final BizException PARAM_IS_NULL = new BizException(EXCEPTION_CODE + 2, "商品sku属性管理参数为空==>%s");
}

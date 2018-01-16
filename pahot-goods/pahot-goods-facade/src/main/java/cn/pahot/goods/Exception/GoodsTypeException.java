package cn.pahot.goods.Exception;

import com.boc.common.exception.BizException;

import static cn.pahot.goods.consts.GoodsConst.EXCEPTION_INIT_CODE;

/**
 * 商品sku属性异常
 */
public class GoodsTypeException {
    public static final int EXCEPTION_CODE = EXCEPTION_INIT_CODE + 1002;
    public static final BizException INSTANCE = new BizException(EXCEPTION_CODE, "%s");
    public static final BizException PARAM_HINT_ERROR = new BizException(EXCEPTION_INIT_CODE + 1, "商品类型管理操作非法==>%s");
    public static final BizException PARAM_IS_NULL = new BizException(EXCEPTION_CODE + 2, "商品类型管理参数为空==>%s");
}

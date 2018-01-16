package cn.pahot.business.biz;

import cn.pahot.business.dao.ShopOpenSubLogDao;
import cn.pahot.business.entity.ShopInfoEntity;
import cn.pahot.business.entity.ShopOpenSolveLogEntity;
import cn.pahot.business.entity.ShopOpenSubLogEntity;
import cn.pahot.business.exception.ExceptionCode;
import com.boc.common.biz.IdWorkerUtil;
import com.boc.common.exception.BizException;
import com.boc.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 店铺申请记录
 */
@Service("shopOpenSubLogBiz")
public class ShopOpenSubLogBiz extends BaseBiz {

    @Autowired
    private ShopOpenSubLogDao shopOpenSubLogDao;

    @Autowired
    private BusinessInfoBiz businessInfoBiz;

    /*添加一条店铺申请记录*/
    public void addItem(ShopOpenSubLogEntity shopOpenSubLogEntity) {

        checkParamas(shopOpenSubLogEntity,
                "merchant_id",
                "name",
                "address",
                "aa01");

        businessInfoBiz.checkBusinessExist(shopOpenSubLogEntity.getMerchant_id());

        shopOpenSubLogEntity.setAa02(DateUtils.getCurrDateTimeToLong());
        shopOpenSubLogEntity.setId(IdWorkerUtil.getId());
        shopOpenSubLogEntity.setState(ShopInfoEntity.STATE_SUBMIT);
        shopOpenSubLogDao.addShopSubLogInfo(shopOpenSubLogEntity);

    }


    /**
     * 获取一条店铺申请记录
     *
     * @param id 店铺开通申请记录编号
     * @return 店铺申请记录
     */
    public ShopOpenSubLogEntity getItem(Long id) {

        if (id == null) {
            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:id为空");
        }
        return shopOpenSubLogDao.getShopSubLogInfoItem(id);
    }


    /**
     * 改变店铺申请的状态
     */
    public void changeShopSubState(ShopOpenSolveLogEntity shopOpenSolveLogEntity) {

        checkParamas(shopOpenSolveLogEntity, "id", "shop_ask_id", "state", "remark", "aa01");
        shopOpenSolveLogEntity.setAa02(DateUtils.getCurrDateTimeToLong());
        shopOpenSubLogDao.updateShopSubLogInfoState(shopOpenSolveLogEntity);

    }

}

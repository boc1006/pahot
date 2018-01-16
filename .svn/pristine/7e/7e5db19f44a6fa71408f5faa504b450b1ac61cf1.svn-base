package cn.pahot.business.biz;

import cn.pahot.business.dao.ShopInfoDao;
import cn.pahot.business.entity.ShopInfoEntity;
import cn.pahot.business.entity.ShopOpenSubLogEntity;
import cn.pahot.business.exception.ExceptionCode;
import com.boc.common.exception.BizException;
import com.boc.common.utils.DateUtils;
import com.boc.common.utils.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 店铺管理
 */
@Service("shopInfoBiz")
public class ShopInfoBiz extends BaseBiz {


    @Autowired
    private BusinessInfoBiz businessInfoBiz;

    @Autowired
    private ShopInfoDao shopInfoDao;


    /**
     * 获取一个店铺信息
     *
     * @param shopId 店铺id
     * @return
     */
    public ShopInfoEntity getShopItem(Integer shopId) {

        if (shopId == null) {
            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:id为空");
        }
        return shopInfoDao.getShopItem(shopId);
    }

    /* 添加一条店铺信息*/
    public void addShopItem(ShopInfoEntity shopInfoEntity) {
        checkParamas(shopInfoEntity,
                "merchant_id",
                "name",
                "address");
        businessInfoBiz.checkBusinessExist(shopInfoEntity.getMerchant_id());
        shopInfoEntity.setTon_time(DateUtils.getShortDateStr());
        shopInfoEntity.setState(ShopInfoEntity.STATE_OK);
        shopInfoEntity.setAa02(DateUtils.getCurrDateTimeToLong());
        shopInfoDao.addShopInfo(shopInfoEntity);

    }


    /*更新店铺信息*/
    public void updateShopItem(ShopInfoEntity shopInfoEntity) {

        checkParamas(shopInfoEntity, "id");
        checkShopExist(shopInfoEntity.getId());

        if (StringUtil.isEmpty(shopInfoEntity.getState())) {
            shopInfoDao.updateShopItem(shopInfoEntity);
        } else {
            checkParamas(shopInfoEntity, "state");

            ShopInfoEntity params = new ShopInfoEntity();
            params.setId(shopInfoEntity.getId());
            params.setState(shopInfoEntity.getState());

            shopInfoDao.updateShopItem(params);

        }

    }


    /**
     * 检查店铺状态
     *
     * @param shopId 店铺id
     */
    public void checkShopExist(Integer shopId) {

        ShopInfoEntity shopInfoEntity = getShopItem(shopId);
        if (shopInfoEntity == null) {
            throw new BizException(ExceptionCode.ERRORCODR_EXIST, "店铺不存在(id=" + shopId + ")");
        } else if (!shopInfoEntity.getState().equals(ShopInfoEntity.STATE_OK)) {
            throw new BizException(ExceptionCode.ERRORCODR_STATE, "店铺状态异常(id=" + shopId + ")");
        }
        businessInfoBiz.checkBusinessExist(shopInfoEntity.getMerchant_id());

    }


    /*检查店铺申请记录*/
    public void checkShopSub(ShopOpenSubLogEntity shopOpenSubLogEntity) {

        if (shopOpenSubLogEntity == null) {
            throw new BizException(ExceptionCode.ERRORCODR_EXIST, "此条申请记录不存在");
        } else if (!shopOpenSubLogEntity.getState().equals(ShopOpenSubLogEntity.STATE_OK)) {
            throw new BizException(ExceptionCode.ERRORCODR_EXIST, "此条申请状态异常");
        }
        businessInfoBiz.checkBusinessExist(shopOpenSubLogEntity.getMerchant_id());
    }


    /*获取店铺列表*/
    public List<ShopInfoEntity> getShopList(int merchant_id) {

        return shopInfoDao.getShopList(merchant_id);
    }

}

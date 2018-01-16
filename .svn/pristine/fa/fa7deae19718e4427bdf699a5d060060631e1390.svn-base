package cn.pahot.business.biz;

import cn.pahot.business.dao.ShopOpenSolveLogDao;
import cn.pahot.business.entity.ShopInfoEntity;
import cn.pahot.business.entity.ShopOpenSolveLogEntity;
import cn.pahot.business.entity.ShopOpenSubLogEntity;
import com.boc.common.biz.IdWorkerUtil;
import com.boc.common.utils.DateUtils;
import com.boc.common.utils.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 店铺审核记录
 */
@Service("shopOpenSolveLogBiz")
public class ShopOpenSolveLogBiz extends BaseBiz {


    @Autowired
    private ShopOpenSolveLogDao shopOpenSolveLogDao;//处理店铺申请的日志

    @Autowired
    private ShopInfoBiz shopInfoBiz;//店铺操作

    @Autowired
    private ShopOpenSubLogBiz shopOpenSubLogBiz;//店铺申请


    /**
     * 添加一条店铺审核处理记录
     *
     * @param shopOpenSubLogEntity
     */
    private void addItem(ShopOpenSubLogEntity shopOpenSubLogEntity) {
        checkParamas(shopOpenSubLogEntity, "id", "aa03", "aa04", "state");
        shopOpenSolveLogDao.addShopSolveLogInfo(shopOpenSubLogEntity, IdWorkerUtil.getId());
    }


    /**
     * 处理店铺申请的审核
     */
    @Transactional
    public void setShopSubInfoState(ShopOpenSubLogEntity params) {

        //0.检查各项的值是否异常
        checkParamas(params, "id", "remark2", "state", "aa03");

        ShopOpenSubLogEntity shopOpenSubLogEntity = shopOpenSubLogBiz.getItem(params.getId());

        //1.检查是否有这条申请信息和商家状态
        shopInfoBiz.checkShopSub(shopOpenSubLogEntity);

        //2.添加店铺申请处理结果
        shopOpenSubLogEntity.setState(params.getState());
        shopOpenSubLogEntity.setRemark2(params.getRemark2());
        shopOpenSubLogEntity.setAa03(params.getAa03());
        shopOpenSubLogEntity.setAa04(DateUtils.getCurrDateTimeToLong());
        addItem(shopOpenSubLogEntity);

        //3.更新店铺申请信息
        ShopOpenSolveLogEntity shopOpenSolveLogEntity = new ShopOpenSolveLogEntity();
        shopOpenSolveLogEntity.setState(params.getState());
        shopOpenSolveLogEntity.setShop_ask_id(shopOpenSubLogEntity.getId());
        shopOpenSolveLogEntity.setRemark(params.getRemark2());
        shopOpenSolveLogEntity.setAa01(params.getAa03());
        shopOpenSolveLogEntity.setAa02(DateUtils.getCurrDateTimeToLong());
        shopOpenSubLogBiz.changeShopSubState(shopOpenSolveLogEntity);

        //4.如果是审核通过则添加相关信息
        if (StringUtil.equals(params.getState().trim(), ShopInfoEntity.STATE_OK)) {

            //4_1.获取到商家的信息
//            BusinessInfoEntity businessInfoEntity = businessInfoBiz.getBusinessInfoById(shopOpenSubLogEntity.getMerchant_id());
//
//            //4_2.注册一个账号并返回注册信息
//            AccountEntity accountEntity = memberFacade.bizRegistryMember(
//                    businessInfoEntity.getAuth_phone(),
//                    true,
//                    "",
//                    true,
//                    RegTypeEnum.REG_TYPE_03.key,
//                    TerminalEnum.TERMINAL_OTHER.key,
//                    businessInfoEntity.getAuth_name(),
//                    businessInfoEntity.getAuth_phone());

            //4_3.添加一条店铺信息
          //  shopInfoBiz.addShopItem(shopOpenSubLogEntity);

//            //4_4.给店铺添加一个管理员账号
//            BusinessAccountEntity businessAccountEntity = new BusinessAccountEntity();
//            businessAccountEntity.setId(accountEntity.getId());
//            businessAccountEntity.setMerchant_id(shopOpenSubLogEntity.getMerchant_id());
//            businessAccountEntity.setName(businessInfoEntity.getAuth_name());
//            businessAccountEntity.setUsername(accountEntity.getUsername());
//            businessAccountEntity.setRoot("" + BusinessRoleEntity.ROLE_ADMIN);
//            businessAccountEntity.setAb01(BusinessRoleEntity.ROLE_ADMIN);
//            businessAccountEntity.setAb02(DateUtils.getCurrDateTimeToLong());
//            businessAccountBiz.addAccount(businessAccountEntity);
        }

    }


}

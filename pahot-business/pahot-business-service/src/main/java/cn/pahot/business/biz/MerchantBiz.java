package cn.pahot.business.biz;

import cn.pahot.business.entity.*;
import cn.pahot.business.enums.BusinessApplayStateEnum;
import cn.pahot.business.exception.MerchantException;
import cn.pahot.member.entity.AccountEntity;
import cn.pahot.member.enums.RegTypeEnum;
import cn.pahot.member.enums.TerminalEnum;
import cn.pahot.member.facade.MemberFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("merchantBiz")
public class MerchantBiz extends BaseBiz {
    @Autowired
    BusinessApplayInfoBiz businessApplayInfoBiz;
    @Autowired
    BusinessApplayLogBiz businessApplayLogBiz;
    @Autowired
    BusinessInfoBiz businessInfoBiz;
    @Autowired
    BusinessAccountBiz businessAccountBiz;
    @Autowired
    ShopInfoBiz shopInfoBiz;
    @Autowired
    MemberFacade memberFacade;

    /**
     * 提交商家申请
     *
     * @param baie
     */
    public void businessSubInfo(BusinessApplayInfoEntity baie) {
        if (baie == null)
            throw MerchantException.PARAM_IS_NULL.newInstance("businessSubInfo|baie");
        businessApplayInfoBiz.addBusinessApplayInfo(baie);
    }


    /**
     * 审核商家信息
     *
     * @param businessApplayLogEntity
     */
    @Transactional
    public void auditBusinessApply(BusinessApplayLogEntity businessApplayLogEntity) {
        if (businessApplayLogEntity == null)
            throw MerchantException.PARAM_IS_NULL.newInstance("auditBusinessApply|businessApplayLogEntity");
        //保存商家审核日志
        businessApplayLogBiz.addBusinessApplayLog(businessApplayLogEntity);
        BusinessApplayStateEnum businessApplayStateEnum = BusinessApplayStateEnum.toEnum(businessApplayLogEntity.getState());
        BusinessApplayInfoEntity newBaie = new BusinessApplayInfoEntity();
        newBaie.setId(businessApplayLogEntity.getMer_ask_id());
        newBaie.setState(businessApplayLogEntity.getState());
        newBaie.setAa01(businessApplayLogEntity.getAa01());
        newBaie.setRemark2(businessApplayLogEntity.getRemark());
        //修改申请状态
        businessApplayInfoBiz.updateBusinessSubInfoState(newBaie);
        //如果是审核通过
        if (businessApplayStateEnum == BusinessApplayStateEnum.BUSINESS_APPLAY_STATE_PASS) {
            BusinessApplayInfoEntity businessApplayInfoEntity = this.businessApplayInfoBiz.getBusinessSubInfoItem(businessApplayLogEntity.getMer_ask_id());
            //注册账号
            AccountEntity accountEntity = this.memberFacade.bizRegistryMember(businessApplayInfoEntity.getPhone(), true,
                    "", true, RegTypeEnum.REG_TYPE_03.key, TerminalEnum.TERMINAL_OTHER.key, businessApplayInfoEntity.getName(),
                    "");
            //保存商家
            BusinessInfoEntity businessInfoEntity = new BusinessInfoEntity();
            businessInfoEntity.setAsk_id(businessApplayLogEntity.getMer_ask_id());
            businessInfoEntity.setMid(accountEntity.getId());
            businessInfoEntity.setAa01(businessApplayLogEntity.getAa01());
            businessInfoEntity.setExp_date(businessApplayLogEntity.getExpiration());
            businessInfoBiz.addBusinessInfo(businessInfoEntity);
            Integer businessInfoId   = businessInfoEntity.getId();
            //保存子账号(商家管理员)
            BusinessAccountEntity account = new BusinessAccountEntity();
            account.setId(accountEntity.getId());
            account.setMerchant_id(businessInfoId);
            account.setRoot(BusinessAccountEntity.ROOT_SUPPER);
            account.setName(accountEntity.getName());
            account.setUsername(accountEntity.getUsername());
            account.setAa01(businessApplayLogEntity.getAa01());
            businessAccountBiz.addAccount(account, false);

            ShopInfoEntity shopInfoEntity = new ShopInfoEntity();
            shopInfoEntity.setAa01(businessApplayLogEntity.getAa01());
            shopInfoEntity.setAddress(businessApplayInfoEntity.getAddress2());
            shopInfoEntity.setName(businessApplayInfoEntity.getName());
            shopInfoEntity.setMerchant_id(businessInfoId);
            shopInfoEntity.setExp_date(businessApplayLogEntity.getExpiration());
            shopInfoEntity.setShop_desc(businessApplayInfoEntity.getShop_desc());
            shopInfoEntity.setBiz_scope(businessApplayInfoEntity.getBiz_scope());
            //保存商铺
            shopInfoBiz.addShopItem(shopInfoEntity);
        }
    }

}

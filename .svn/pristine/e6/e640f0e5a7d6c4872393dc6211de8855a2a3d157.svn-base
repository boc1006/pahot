package cn.pahot.business.service;

import cn.pahot.business.biz.*;
import cn.pahot.business.entity.*;
import cn.pahot.business.facade.MerchantFacade;
import com.boc.common.core.test.annotation.DubboProviderMethod;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.page.PageParams;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商家账户管理服务实现
 * <p>@Title: MerchantService.java
 * <p>@Package cn.pahot.business.service
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com
 * <p>@date 2017年12月18日 下午2:34:01
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@DubboProviderMethod(ip = "192.168.3.151", name = "徐杰", system = "商家系统", systemNum = "100005")
@Service("merchantFacade")
public class MerchantService implements MerchantFacade {

    @Autowired
    private MerchantBiz merchantBiz;

    @Autowired
    private ShopInfoBiz shopInfoBiz;

    @Autowired
    private ShopOpenSolveLogBiz shopOpenSolveLogBiz;

    @Autowired
    private ShopOpenSubLogBiz shopOpenSubLogBiz;

    @Autowired
    private BusinessAccountBiz businessAccountBiz;

    /**
     * 提交商家申请
     *
     * @param businessApplayInfoEntity
     * @throws BizException
     */
    @Override
    public void businessSubInfo(BusinessApplayInfoEntity businessApplayInfoEntity) throws BizException {
        merchantBiz.businessSubInfo(businessApplayInfoEntity);
    }

    @Override
    public void addBusinessSubLogInfo(BusinessApplayLogEntity businessApplayLog) throws BizException {
        this.merchantBiz.auditBusinessApply(businessApplayLog);
    }

    @Override
    public void updateBusinessItem(BusinessInfoEntity businessInfoEntity) throws BizException {

    }

    @Override
    public PageInfo<BusinessInfoEntity> getBusinessList(PageParams<DTO<String, String>> pageParams) throws BizException {
        return null;
    }

    @Override
    public void updateShopItem(ShopInfoEntity shopInfoEntity) throws BizException {
        shopInfoBiz.updateShopItem(shopInfoEntity);
    }

    @Override
    public List<ShopInfoEntity> getShopList(Integer merchant_id) throws BizException {
        return shopInfoBiz.getShopList(merchant_id);
    }

    @Override
    public void addShopSubLogInfo(ShopOpenSubLogEntity shopOpenSubLogEntity) throws BizException {
        shopOpenSubLogBiz.addItem(shopOpenSubLogEntity);
    }

    @Override
    public void addShopSolveLogInfo(ShopOpenSolveLogEntity shopOpenSolveLogEntity) throws BizException {

        shopOpenSolveLogBiz.setShopSubInfoState(shopOpenSubLogBiz.getItem(shopOpenSolveLogEntity.getShop_ask_id()));

    }

    @Override
    public void addBusinessAccount(BusinessAccountEntity businessAccountEntity) throws BizException {
        businessAccountBiz.addAccount(businessAccountEntity, true);

    }

    @Override
    public void updateBusinessAccount(BusinessAccountEntity businessAccountEntity) throws BizException {

        businessAccountBiz.updateAccountInfo(businessAccountEntity);

    }

    @Override
    public List<BusinessAccountEntity> getBusinessAccountList() throws BizException {
        return businessAccountBiz.getAccountList();
    }

    @Override
    public void addBusinessRole(BusinessRoleEntity businessRoleEntity) throws BizException {

    }

    @Override
    public void updateBusinessRole(BusinessRoleEntity businessRoleEntity) throws BizException {

    }

    @Override
    public void addBusinessMenu(BusinessMenuEntity businessMenuEntity) throws BizException {

    }

    @Override
    public void updateBusinessMenu(BusinessMenuEntity businessMenuEntity) throws BizException {

    }

    @Override
    public List<BusinessMenuEntity> getBusinessMenuList() throws BizException {
        return null;
    }
}

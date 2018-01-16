package cn.pahot.business.facade;

import cn.pahot.business.entity.*;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.page.PageParams;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 商家账号管理接口定义,包括商品登录、注册、认证、审核等
 * <p>@Title: MerchantFacade.java
 * <p>@Package cn.pahot.business.facade
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com
 * <p>@date 2017年12月18日 下午2:32:31
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface MerchantFacade {


    /**
     * 商家申请入驻,提交资料
     **/
    void businessSubInfo(BusinessApplayInfoEntity businessApplayInfoEntity) throws BizException;

    /*2.商家入驻审核日志*/
    void addBusinessSubLogInfo(BusinessApplayLogEntity businessApplayLogEntity) throws BizException;

    /**
     * 商家管理
     **/

    /*2.修改商家信息*/
    void updateBusinessItem(BusinessInfoEntity businessInfoEntity) throws BizException;

    /*3.获取商家列表*/
    PageInfo<BusinessInfoEntity> getBusinessList(PageParams<DTO<String, String>> pageParams) throws BizException;


    /**
     * 修改店铺信息(除了状态)
     *
     * @param id             -->必传,店铺id
     * @param exp_date       -->选传,店铺有效时间
     * @param merchant_id    -->选传,商家编号
     * @param name           -->选传,店铺名称
     * @param address        -->选传,店铺地址
     * @param shop_lng       -->选传,店铺经度
     * @param shop_lat       -->选传,店铺纬度
     * @param shop_desc      -->选传,店铺描述
     * @param banner         -->选传,店铺横幅
     * @param biz_scope      -->选传,店铺id
     * @param ton_time       -->选传,店铺开通时间
     * @param shopInfoEntity
     * @throws BizException
     */
    void updateShopItem(ShopInfoEntity shopInfoEntity) throws BizException;


    /**
     * 获取店铺列表
     * @param merchant_id 商家编号
     * @return
     * @throws BizException
     */
    List<ShopInfoEntity> getShopList(Integer merchant_id) throws BizException;


    /**
     * 4.商家店铺申请日志信息
     *
     * @param merchant_id -->必传,商家编号
     * @param name        -->必传,店铺名称
     * @param address     -->必传,店铺地址
     * @param shop_lng    -->选传,店铺经度
     * @param shop_lat    -->选传,店铺纬度
     * @param shop_desc   -->选传,店铺描述
     * @param biz_scope   -->选传,经营范围描述
     * @param remark      -->选传,其它申请描述
     * @param aa01        -->必传,申请人编号
     * @param aa03        -->选传,审核人编号
     * @param state       -->选传,审核结果(状态):ShopInfoEntity的常量
     * @param remark2     -->选传,审核描述
     * @throws BizException
     * @paramREMARK
     */
    void addShopSubLogInfo(ShopOpenSubLogEntity shopOpenSubLogEntity) throws BizException;


    /**
     * 添加商家店铺审核日志信息
     *
     * @param shop_ask_id -->必传,店铺开通申请记录编号
     * @throws BizException
     */
    void addShopSolveLogInfo(ShopOpenSolveLogEntity shopOpenSolveLogEntity) throws BizException;

    /**
     * 商家子账户管理
     **/
        /*1.新增一个账户*/
    void addBusinessAccount(BusinessAccountEntity businessAccountEntity) throws BizException;

    /*2.修改账户信息*/
    void updateBusinessAccount(BusinessAccountEntity businessAccountEntity) throws BizException;

    /*3.获取账户列表*/
    List<BusinessAccountEntity> getBusinessAccountList() throws BizException;

    /**
     * 商家角色管理
     **/
        /*1.新增一个角色*/
    void addBusinessRole(BusinessRoleEntity businessRoleEntity) throws BizException;

    /*2.修改角色信息*/
    void updateBusinessRole(BusinessRoleEntity businessRoleEntity) throws BizException;

    /**
     * 商家菜单管理
     **/
        /*1.新增一个菜单*/
    void addBusinessMenu(BusinessMenuEntity businessMenuEntity) throws BizException;

    /*2.修改菜单信息*/
    void updateBusinessMenu(BusinessMenuEntity businessMenuEntity) throws BizException;

    /*3.获取菜单列表*/
    List<BusinessMenuEntity> getBusinessMenuList() throws BizException;
}

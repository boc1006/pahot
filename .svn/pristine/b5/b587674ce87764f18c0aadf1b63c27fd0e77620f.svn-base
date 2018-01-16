package cn.pahot.business.dao;

import cn.pahot.business.entity.BusinessAccountEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商家子账户
 */
public interface BusinessAccountDao {
    /**
     * 添加商家子账户
     *
     * @param BusinessAccount
     */
    void addBusinessAccount(BusinessAccountEntity BusinessAccount);


    /*添加一条子账户*/
    void addShopAccount(BusinessAccountEntity businessAccountEntity);

    /*获取一条商家子账户信息*/
    BusinessAccountEntity getShopAccountItem(@Param("id") Integer id);

    /*获取商家子账户信息列表*/
    List<BusinessAccountEntity> getShopAccountList();

    /*更新商家子账户信息*/
    void updateShopAccountItem(BusinessAccountEntity businessAccountEntity);

}

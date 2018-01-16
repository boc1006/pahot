package cn.pahot.business.dao;

import cn.pahot.business.entity.ShopInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 店铺数据库操作接口
 */
public interface ShopInfoDao {
    /**
     * 添加店铺
     *
     * @param shopInfoEntity
     */
    void addShopInfo(ShopInfoEntity shopInfoEntity);

    /*查找一条店铺信息*/
    ShopInfoEntity getShopItem(@Param("id") Integer id);

    /*更新店铺信息*/
    void updateShopItem(ShopInfoEntity shopInfoEntity);


    List<ShopInfoEntity> getShopList(@Param("merchant_id") int merchant_id);

}

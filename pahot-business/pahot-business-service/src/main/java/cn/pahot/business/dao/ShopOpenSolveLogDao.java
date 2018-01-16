package cn.pahot.business.dao;

import cn.pahot.business.entity.ShopOpenSolveLogEntity;
import cn.pahot.business.entity.ShopOpenSubLogEntity;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 商家店铺申请审核数据库接口
 */
public interface ShopOpenSolveLogDao {
    /*添加店铺审核状态信息*/
    void addShopSolveLogInfo(ShopOpenSubLogEntity shopOpenSubLogEntity, @Param("insertid") Long insertid);

    /*获取店铺审核记录列表*/
    Page<ShopOpenSolveLogEntity> getShopSolveLogInfoList();

    /*获取一条店铺审核记录*/
    ShopOpenSolveLogEntity getShopSolveLogInfoItem(@Param("id") Long id);
}

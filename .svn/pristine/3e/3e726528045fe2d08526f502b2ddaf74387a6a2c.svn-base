package cn.pahot.business.dao;

import cn.pahot.business.entity.ShopOpenSolveLogEntity;
import cn.pahot.business.entity.ShopOpenSubLogEntity;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 店铺开通申请记录数据库接口
 */
public interface ShopOpenSubLogDao {

    /*添加一条店铺申请信息*/
    void addShopSubLogInfo(ShopOpenSubLogEntity shopOpenSubLogEntity);

    /*更新一条店铺申请信息*/
    void updateShopSubLogInfoState(ShopOpenSolveLogEntity shopOpenSolveLogEntity);

    /*获取一条店铺申请记录*/
    ShopOpenSubLogEntity getShopSubLogInfoItem(@Param("id") Long id);

    /*获取店铺申请记录列表*/
    Page<ShopOpenSolveLogEntity> getShopSubLogInfoStateList();

}

package cn.pahot.business.dao;

import cn.pahot.business.entity.BusinessApplayInfoEntity;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 商家申请信息数据库接口
 */
public interface BusinessApplayInfoDao {

    /*添加一条商家申请记录*/
    void addBusinessSubInfo(BusinessApplayInfoEntity businessApplayInfoEntity);

    /*获取一条商家申请记录*/
    BusinessApplayInfoEntity getBusinessSubInfoItem(@Param("id") Long id);

    /*获取商家申请记录的列表*/
    Page<BusinessApplayInfoEntity> getBusinessSubInfoList();

   /* *//*更新商家申请记录的状态*//*
    void updateBusinessSubInfoState(BusinessApplayLogEntity businessApplayLogEntity);*/

    /**
     * 更新商家申请状态
     *
     * @param businessApplayInfoEntity
     */
    void updateBusinessSubInfoState(BusinessApplayInfoEntity businessApplayInfoEntity);
}

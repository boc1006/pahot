package cn.pahot.business.dao;

import cn.pahot.business.entity.BusinessInfoEntity;
import com.boc.common.metatype.DTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 商家数据库操作接口
 */
public interface BusinessInfoDao {
    /**
     * 添加商家
     *
     * @param businessInfoEntity
     */
    Integer addBusinessInfo(BusinessInfoEntity businessInfoEntity);

    /**
     * 分页查询查询商家
     *
     * @param params
     * @return
     */
    Page<BusinessInfoEntity> getBusinessInfoForPage(DTO<String, String> params);


    /**
     * 根据商家id查询商家
     *
     * @param id
     * @return
     */
    BusinessInfoEntity getBusinessInfoById(@Param("id") Integer id);

    /**
     * 更新商家信息
     *
     * @param businessInfoEntity
     * @return
     */
    void updateBusinessInfoById(BusinessInfoEntity businessInfoEntity);


    /**
     * 更新商家状态信息 包括商家state  供应商商状态 ,销售商状态
     *
     * @param dto
     * @return
     */
    void updateBusinessForState(DTO<String,String> dto);


    /*更新商家信息*/
    void updateBusinessItem(BusinessInfoEntity businessInfoEntity);

}

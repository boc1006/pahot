package cn.pahot.business.dao;

import cn.pahot.business.entity.BusinessApplayLogEntity;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 商家审核操作接口
 */
public interface BusinessApplayLogDao {
    /**
     * 添加商家审核日志
     *
     * @param BusinessApplayLog
     */
    void addBusinessApplayLog(BusinessApplayLogEntity BusinessApplayLog);

    /**
     * 分页查询商家审核
     *
     * @param BusinessApplayLog
     */
    Page<BusinessApplayLogEntity> getBusinessApplayLogForPage(BusinessApplayLogEntity BusinessApplayLog);

    /**
     * 通过id查询商家审核日志
     *
     * @param id
     */
    BusinessApplayLogEntity getBusinessApplayLogById(@Param("id") Integer id);
}

package cn.pahot.business.biz;

import cn.pahot.business.dao.BusinessApplayInfoDao;
import cn.pahot.business.dao.MerchantMapper;
import cn.pahot.business.entity.BusinessApplayInfoEntity;
import cn.pahot.business.enums.BusinessApplayStateEnum;
import cn.pahot.business.exception.MerchantException;
import com.boc.common.biz.IdWorkerUtil;
import com.boc.common.utils.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("businessApplayInfoBiz")
public class BusinessApplayInfoBiz extends BaseBiz {
    @Autowired
    private BusinessApplayInfoDao businessApplayInfoDao;

    /**
     * 根据申请信息id查询商家申请信息
     *
     * @param id
     * @return
     */
    public BusinessApplayInfoEntity getBusinessApplayInfoById(@Param("id") Integer id) {
        if (id == null || id == 0)
            throw MerchantException.PARAM_IS_NULL.newInstance("查询商家申请信息时参数id为null");
        //merchantDao.get
        return null;
    }

    public void addBusinessApplayInfo(BusinessApplayInfoEntity businessApplayInfoEntity) {
        this.checkParamas(businessApplayInfoEntity, "merchant_name", "located_type", "phone", "username", "address",
                "idcard", "name", "address2", "shop_desc", "biz_scope",
                "aa01");
        //生成申请id

        businessApplayInfoEntity.setId(IdWorkerUtil.getId());
        //生成申请时间
        businessApplayInfoEntity.setCmttime(DateUtils.getCurrDateTimeToLong());
        //生成添加时间
        businessApplayInfoEntity.setAa02(DateUtils.getCurrDateTimeToLong());
        businessApplayInfoEntity.setState(BusinessApplayStateEnum.BUSINESS_APPLAY_STATE_AUDIT.key);
        this.businessApplayInfoDao.addBusinessSubInfo(businessApplayInfoEntity);
    }

    public BusinessApplayInfoEntity getBusinessSubInfoItem(Long mer_ask_id) {
        BusinessApplayInfoEntity businessApplayInfoEntity = businessApplayInfoDao.getBusinessSubInfoItem(mer_ask_id);
        return businessApplayInfoEntity;
    }

    /**
     * 更新商家申请状态
     *
     * @param baie
     */
    public void updateBusinessSubInfoState(BusinessApplayInfoEntity baie) {
        if (baie == null)
            MerchantException.PARAM_IS_NULL.newInstance("updateBusinessSubInfoState|businessApplayInfoEntity");
        this.checkParamas(baie, "state", "remark2", "aa01", "id");
        //查询商铺申请信息
        BusinessApplayInfoEntity businessApplayInfoEntity = this.businessApplayInfoDao.getBusinessSubInfoItem(baie.getId());
        if (businessApplayInfoEntity == null) {
            throw MerchantException.PARAM_HINT_ERROR.newInstance("未找到对应的商家申请记录");
        }
        BusinessApplayStateEnum bas = BusinessApplayStateEnum.toEnum(businessApplayInfoEntity.getState());
        if (bas == null)
            throw MerchantException.PARAM_HINT_ERROR.newInstance("商家申请记录状态有误!");
        if (bas != BusinessApplayStateEnum.BUSINESS_APPLAY_STATE_AUDIT)
            throw MerchantException.PARAM_HINT_ERROR.newInstance("该商家申请记录已经审核过了!");
        baie.setAa02(DateUtils.getCurrDateTimeToLong());
        businessApplayInfoDao.updateBusinessSubInfoState(baie);
    }

}

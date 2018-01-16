package cn.pahot.business.biz;

import cn.pahot.business.dao.BusinessApplayLogDao;
import cn.pahot.business.entity.BusinessApplayLogEntity;
import cn.pahot.business.enums.BusinessApplayStateEnum;
import cn.pahot.business.exception.MerchantException;
import com.boc.common.biz.IdWorkerUtil;
import com.boc.common.core.biz.AbstractPageBuilder;
import com.boc.common.core.biz.DefaultPageBuilder;
import com.boc.common.exception.BizException;
import com.boc.common.page.PageParams;
import com.boc.common.utils.DateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("businessApplayLogBiz")
public class BusinessApplayLogBiz extends BaseBiz {
    @Autowired
    private BusinessApplayLogDao businessApplayLogDao;

    /**
     * 保存审核日志
     *
     * @param businessApplayLog
     */
    public void addBusinessApplayLog(BusinessApplayLogEntity businessApplayLog) {
        businessApplayLog.setId(IdWorkerUtil.getId());
        businessApplayLog.setAa02(DateUtils.getCurrDateTimeToLong());
        this.checkParamas(businessApplayLog, "state", "mer_ask_id", "remark", "id", "aa01");
        //检测状态是否有效
        BusinessApplayStateEnum businessApplayStateEnum = BusinessApplayStateEnum.toEnum(businessApplayLog.getState());
        if (businessApplayLog == null)
            throw MerchantException.PARAM_HINT_ERROR.newInstance("auditBusinessApply|state 异常:" + businessApplayLog.getState());
        //检测商家申请状态

        businessApplayLogDao.addBusinessApplayLog(businessApplayLog);
    }

    /**
     * 分页查询商家申请审核记录
     *
     * @param pageParams
     * @return
     * @throws BizException
     */
    public PageInfo<BusinessApplayLogEntity> getBusinessApplayLogForPage(PageParams<BusinessApplayLogEntity> pageParams) throws BizException {
        PageInfo<BusinessApplayLogEntity> pi = DefaultPageBuilder.build(pageParams,
                new AbstractPageBuilder<BusinessApplayLogEntity, BusinessApplayLogEntity>() {
                    @Override
                    public Page<BusinessApplayLogEntity> build(BusinessApplayLogEntity businessApplayLogEntity) throws BizException {
                        return businessApplayLogDao.getBusinessApplayLogForPage(businessApplayLogEntity);
                    }
                });
        return pi;
    }


    /**
     * 保存审核日志
     *
     * @param id
     */
    public BusinessApplayLogEntity getBusinessApplayLogById(@Param("id") Integer id) {
        return businessApplayLogDao.getBusinessApplayLogById(id);
    }




   /* *//**
     * 审核商家申请
     *//*
    @Transactional
    public void auditBusinessApply(BusinessApplayLogEntity businessApplayLogEntity) {
        businessApplayLogEntity.setId(IdWorkerUtil.getId());
        businessApplayLogEntity.setAa02(DateUtils.getCurrDateTimeToLong());
        businessApplayLogDao.addBusinessApplayLog(businessApplayLogEntity);
        //如果审核通过
        if (BusinessApplayStateEnum.BUSINESS_APPLAY_STATE_PASS == BusinessApplayStateEnum.toEnum(businessApplayLogEntity.getState())) {
            //查询商铺申请信息
            BusinessApplayInfoEntity businessApplayInfoEntity = this.merchantMapper.getBusinessSubInfoItem(businessApplayLogEntity.getMer_ask_id());
            if (businessApplayInfoEntity == null) {
                throw MerchantException.PARAM_HINT_ERROR.newInstance("未找到对应的商家申请记录");
            }
            BusinessApplayStateEnum bas = BusinessApplayStateEnum.toEnum(businessApplayInfoEntity.getState());
            if (bas == null)
                throw MerchantException.PARAM_HINT_ERROR.newInstance("商家申请记录状态有误!");
            if (bas != BusinessApplayStateEnum.BUSINESS_APPLAY_STATE_AUDIT)
                throw MerchantException.PARAM_HINT_ERROR.newInstance("该商家申请记录已经审核过了!");
          *//*  BusinessApplayInfoEntity  businessApplayInfoEntityForUpdateState =  new BusinessApplayInfoEntity();
            businessApplayInfoEntityForUpdateState.setId(businessApplayInfoEntity.getId());
            businessApplayInfoEntityForUpdateState.setState(businessApplayLogEntity.getState());*//*
            //更新 申请状态
            this.merchantMapper.updateBusinessSubInfoState(businessApplayLogEntity);
            //添加用户
            //	int bizRegistryMember(String phone,boolean isDefPwd,String passwd,boolean isSendSms,String regType,
            // String terminal,String name,String tel) throws BizException;
            //注册用户
            int userId = this.memberFacade.bizRegistryMember(businessApplayInfoEntity.getPhone(), true,
                    "", false, RegTypeEnum.REG_TYPE_03.key, TerminalEnum.TERMINAL_OTHER.key, businessApplayInfoEntity.getName(),
                    "");

            //添加商家子账号

            //  this.businessAccountDao.addBusinessAccount();


        }
    }*/

}

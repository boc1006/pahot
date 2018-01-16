package cn.pahot.business.biz;

import cn.pahot.business.dao.BusinessApplayInfoDao;
import cn.pahot.business.dao.BusinessInfoDao;
import cn.pahot.business.entity.BusinessApplayInfoEntity;
import cn.pahot.business.entity.BusinessInfoEntity;
import cn.pahot.business.enums.BusinessApplayStateEnum;
import cn.pahot.business.exception.ExceptionCode;
import cn.pahot.business.exception.MerchantException;
import com.boc.common.core.biz.AbstractPageBuilder;
import com.boc.common.core.biz.DefaultPageBuilder;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;
import com.boc.common.page.PageParams;
import com.boc.common.utils.DateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("businessInfoBiz")
public class BusinessInfoBiz extends BaseBiz {
    @Autowired
    private BusinessInfoDao businessInfoEntityDao;
    @Autowired
    private BusinessApplayInfoDao businessApplayInfoDao;

    /**
     * 添加商家
     *
     * @param businessInfo businessInfo.ask_id 对应的商家申请id
     *                     businessInfo.mid  用户账号
     *                     businessInfo.aa01  操作人id
     */
    public Integer addBusinessInfo(BusinessInfoEntity businessInfo) {

        checkParamas(businessInfo, "ask_id", "aa01", "mid");
        //查询商家申请信息
        BusinessApplayInfoEntity businessApplayInfoEntity = businessApplayInfoDao.getBusinessSubInfoItem(businessInfo.getAsk_id());

        if (businessApplayInfoEntity == null)
            throw MerchantException.PARAM_HINT_ERROR.newInstance("为找到对应的商家申请信息.");
        /*if (businessApplayInfoEntity == null)
            throw MerchantException.PARAM_HINT_ERROR.newInstance("为找到对应的商家申请信息.");*/
        BusinessApplayStateEnum bas = BusinessApplayStateEnum.toEnum(businessApplayInfoEntity.getState());
        if (BusinessApplayStateEnum.BUSINESS_APPLAY_STATE_PASS != bas)
            throw MerchantException.PARAM_HINT_ERROR.newInstance("商家申请信息状态有误.");

        businessInfo.setAuth_name(businessApplayInfoEntity.getName());
        businessInfo.setAuth_phone(businessApplayInfoEntity.getPhone());
        businessInfo.setIdcard(businessApplayInfoEntity.getIdcard());
        businessInfo.setLocated_date(DateUtils.getShortDateStr());
        businessInfo.setLocated_type(businessApplayInfoEntity.getLocated_type());
        //01供应商,02销售商,00供应商+销售商',
        if ("01".equals(businessInfo.getLocated_type())) {
            businessInfo.setSale_state("00");
            businessInfo.setSupply_state("01");
        } else if ("02".equals(businessInfo.getLocated_type())) {
            businessInfo.setSale_state("01");
            businessInfo.setSupply_state("00");
        } else if ("00".equals(businessInfo.getLocated_type())) {
            businessInfo.setSale_state("01");
            businessInfo.setSupply_state("01");
        }
        businessInfo.setName(businessApplayInfoEntity.getMerchant_name());
        //添加立即为开通状态
        businessInfo.setState("01");
        businessInfo.setAa02(DateUtils.getCurrDateTimeToLong());
        //  businessInfo.set
     return   this.businessInfoEntityDao.addBusinessInfo(businessInfo);
    }


    /**
     * 更新商家
     *
     * @param businessInfo
     */
    public void updateBusinessInfo(BusinessInfoEntity businessInfo) {
        if (businessInfo == null)
            throw MerchantException.PARAM_IS_NULL.newInstance("更商家信息时候,商家参数为null");
        this.checkParamas(businessInfo, "id", "ab01");

        //   businessInfo.setAa02(DateUtils.getCurrDateTimeToLong());
        this.businessInfoEntityDao.addBusinessInfo(businessInfo);
    }

    /**
     * @param pageParams
     * @return
     * @throws BizException
     */
    public PageInfo<BusinessInfoEntity> getBusinessInfoForPage(PageParams<DTO<String, String>> pageParams) {
        PageInfo<BusinessInfoEntity> pi = DefaultPageBuilder.build(pageParams,
                new AbstractPageBuilder<BusinessInfoEntity, DTO<String, String>>() {
                    @Override
                    public Page<BusinessInfoEntity> build(DTO<String, String> params) {
                        return businessInfoEntityDao.getBusinessInfoForPage(params);
                    }
                });
        return pi;
    }


    /**
     * 通过id查询商家
     *
     * @param id
     */
    public BusinessInfoEntity getBusinessInfoById(Integer id) {
        if (id == null || id == 0)
            throw MerchantException.PARAM_IS_NULL.newInstance("查询商家信息时候id为null");
        return this.businessInfoEntityDao.getBusinessInfoById(id);
    }


    /**
     * 更新商家状态   // 01入驻,02冻结,03注销'
     *
     * @param
     */
    public void updateBusinessInfoState(BusinessInfoEntity businessInfoEntity) {
        this.checkParamas(businessInfoEntity, "id", "state");
        BusinessInfoEntity bie = this.businessInfoEntityDao.getBusinessInfoById(businessInfoEntity.getId());
        if ("00".equals(bie.getState()))
            throw MerchantException.PARAM_HINT_ERROR.newInstance("商家已经注销!");
        DTO<String, String> dto = new BaseDTO<>();
        dto.put("id", String.valueOf(businessInfoEntity.getId()));
        dto.put("state", String.valueOf(businessInfoEntity.getState()));
        this.businessInfoEntityDao.updateBusinessForState(dto);
    }


    /**
     * 更新商家销售和供应商状态   // 01入驻,02冻结,03注销'
     *
     * @param
     */
    public void updateBusinessInfoSaleAndSupplyState(BusinessInfoEntity businessInfoEntity) {
        this.checkParamas(businessInfoEntity, "id", "sale_state", "supply_state");
        BusinessInfoEntity bie = this.businessInfoEntityDao.getBusinessInfoById(businessInfoEntity.getId());
        if ("00".equals(bie.getState()))
            throw MerchantException.PARAM_HINT_ERROR.newInstance("商家已经注销!");
        DTO<String, String> dto = new BaseDTO<>();
        dto.put("id", String.valueOf(businessInfoEntity.getId()));
        dto.put("supply_state", String.valueOf(businessInfoEntity.getSupply_state()));
        dto.put("sale_state", String.valueOf(businessInfoEntity.getSale_state()));
        this.businessInfoEntityDao.updateBusinessForState(dto);
    }


    /**
     * 检查商家的状态
     *
     * @param id 商家id
     */
    public void checkBusinessExist(Integer id) {

        BusinessInfoEntity businessInfoEntity = getBusinessInfoById(id);
        if (businessInfoEntity == null) {
            throw new BizException(ExceptionCode.ERRORCODR_EXIST, "商家不存在(id=" + id + ")");
        } else if (!businessInfoEntity.getState().equals(BusinessInfoEntity.BUSINESS_STATE_OK)) {
            throw new BizException(ExceptionCode.ERRORCODR_STATE, "商家状态异常(id=" + id + ")");
        }
    }

}

package cn.pahot.business.biz;

import cn.pahot.business.entity.*;
import cn.pahot.business.exception.ExceptionCode;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.BaseEntity;
import com.boc.common.utils.string.StringUtil;

/**
 * creat by @author:chen in 2017\12\21 0021
 *
 * @description:
 **/
public class BaseBiz {
    /**
     * 检测某个实体里面的变量是否为空
     *
     * @param baseEntity
     * @param params     需要检测的字段名称
     */
    public void checkParamas(BaseEntity baseEntity, String... params) {

        if (baseEntity == null) {
            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "内容为空");
        }

        if (baseEntity instanceof BusinessApplayInfoEntity) {
            BusinessApplayInfoEntity businessApplayInfoEntity = (BusinessApplayInfoEntity) baseEntity;
            for (String parItem : params) {

                switch (parItem) {
                    case "merchant_name":
                        if (StringUtil.isEmpty(businessApplayInfoEntity.getMerchant_name())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:merchant_name为空");
                        }
                        break;
                    case "located_type":
                        if (StringUtil.isEmpty(businessApplayInfoEntity.getLocated_type())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:located_type为空");
                        }
                        break;
                    case "phone":
                        if (StringUtil.isEmpty(businessApplayInfoEntity.getPhone())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:phone为空");
                        }
                        break;
                    case "username":
                        if (StringUtil.isEmpty(businessApplayInfoEntity.getUsername())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:username为空");
                        }
                        break;

                    case "address":
                        if (StringUtil.isEmpty(businessApplayInfoEntity.getAddress())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:address为空");
                        }
                        break;

                    case "idcard":
                        if (StringUtil.isEmpty(businessApplayInfoEntity.getIdcard())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:idcard为空");
                        }
                        break;
                    case "name":
                        if (StringUtil.isEmpty(businessApplayInfoEntity.getName())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:name为空");
                        }
                        break;
                    case "address2":
                        if (StringUtil.isEmpty(businessApplayInfoEntity.getAddress2())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:address2为空");
                        }
                        break;
                    case "state":
                        if (StringUtil.isEmpty(businessApplayInfoEntity.getState())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:state为空");
                        }
                        break;
                }


            }

        } else if (baseEntity instanceof BusinessApplayLogEntity) {

            BusinessApplayLogEntity businessApplayLogEntity = (BusinessApplayLogEntity) baseEntity;

            for (String parItem : params) {

                switch (parItem) {
                    case "mer_ask_id":

                        if (businessApplayLogEntity.getMer_ask_id() == null) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:mer_ask_id为空");
                        }
                        break;
                    case "state":
                        if (StringUtil.isEmpty(businessApplayLogEntity.getState())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:state为空");
                        }
                        break;
                }
            }
        } else if (baseEntity instanceof BusinessInfoEntity) {
            BusinessInfoEntity businessInfoEntity = (BusinessInfoEntity) baseEntity;

            for (String parItem : params) {
                switch (parItem) {
                    case "name":
                        if (StringUtil.isEmpty(businessInfoEntity.getName())) {

                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:name为空");
                        }
                        break;
                    case "located_type":
                        if (StringUtil.isEmpty(businessInfoEntity.getLocated_type())) {

                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:located_type为空");
                        }
                        break;
                    case "mid":
                        if (businessInfoEntity.getMid() == null) {

                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:mid为空");
                        }
                        break;
                    case "auth_phone":
                        if (StringUtil.isEmpty(businessInfoEntity.getAuth_phone())) {

                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:auth_phone为空");
                        }
                        break;
                    case "auth_name":
                        if (StringUtil.isEmpty(businessInfoEntity.getAuth_name())) {

                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:auth_name为空");
                        }
                        break;
                    case "state":
                        if (StringUtil.isEmpty(businessInfoEntity.getState())) {

                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:state为空");
                        }
                        break;
                    case "supply_state":
                        if (StringUtil.isEmpty(businessInfoEntity.getSupply_state())) {

                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:supply_state为空");
                        }
                        break;
                    case "sale_state":
                        if (StringUtil.isEmpty(businessInfoEntity.getSale_state())) {

                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:sale_state为空");
                        }
                        break;
                    case "located_date":
                        if (StringUtil.isEmpty(businessInfoEntity.getLocated_date())) {

                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:located_date为空");
                        }
                        break;
                    case "exp_date":
                        if (StringUtil.isEmpty(businessInfoEntity.getExp_date())) {

                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:exp_date为空");
                        }
                        break;

                }
            }
        } else if (baseEntity instanceof ShopOpenSolveLogEntity) {
            ShopOpenSolveLogEntity shopOpenSolveLogEntity = (ShopOpenSolveLogEntity) baseEntity;


            for (String paItem : params) {

                switch (paItem) {
                    case "shop_ask_id":

                        if (shopOpenSolveLogEntity.getShop_ask_id() == null) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:shop_ask_id为空");
                        }

                        break;

                    case "state":
                        if (StringUtil.isEmpty(shopOpenSolveLogEntity.getState())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:state为空");
                        } else if (!shopOpenSolveLogEntity.getState().equals(ShopInfoEntity.STATE_OK)
                                && !shopOpenSolveLogEntity.getState().equals(ShopInfoEntity.STATE_REFUSE)
                                && !shopOpenSolveLogEntity.getState().equals(ShopInfoEntity.STATE_SUBMIT)) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:state类型错误:" + shopOpenSolveLogEntity.getState());
                        }

                        break;

                    case "remark":
                        if (StringUtil.isEmpty(shopOpenSolveLogEntity.getRemark())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:remark为空空");
                        }

                        break;
                }

            }

        } else if (baseEntity instanceof ShopOpenSubLogEntity) {
            ShopOpenSubLogEntity shopOpenSubLogEntity = (ShopOpenSubLogEntity) baseEntity;

            for (String paItem : params) {
                switch (paItem) {
                    case "merchant_id":
                        if (shopOpenSubLogEntity.getMerchant_id() == null) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:merchant_id为空");
                        }
                        break;
                    case "name":
                        if (StringUtil.isEmpty(shopOpenSubLogEntity.getName())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:name为空");
                        }
                        break;
                    case "address":
                        if (StringUtil.isEmpty(shopOpenSubLogEntity.getAddress())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:address为空");
                        }
                        break;

                    case "shop_lng":
                        if (StringUtil.isEmpty(shopOpenSubLogEntity.getShop_lng())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:shop_lng为空");
                        }
                        break;

                    case "shop_lat":
                        if (StringUtil.isEmpty(shopOpenSubLogEntity.getShop_lat())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:shop_lat为空");
                        }
                        break;
                    case "shop_desc":
                        if (StringUtil.isEmpty(shopOpenSubLogEntity.getShop_desc())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:shop_desc为空");
                        }
                        break;

                    case "biz_scope":
                        if (StringUtil.isEmpty(shopOpenSubLogEntity.getBiz_scope())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:biz_scope为空");
                        }
                        break;

                    case "remark":
                        if (StringUtil.isEmpty(shopOpenSubLogEntity.getRemark())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:remark为空");
                        }
                        break;

                    case "aa03":
                        if (shopOpenSubLogEntity.getAa03() == null) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:aa03为空");
                        }
                        break;

                    case "aa04":
                        if (shopOpenSubLogEntity.getAa04() == null) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:aa04为空");
                        }
                        break;

                    case "state":
                        if (StringUtil.isEmpty(shopOpenSubLogEntity.getState())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:state为空");
                        } else if (!shopOpenSubLogEntity.getState().equals(ShopInfoEntity.STATE_SUBMIT)
                                && !shopOpenSubLogEntity.getState().equals(ShopInfoEntity.STATE_REFUSE)
                                && !shopOpenSubLogEntity.getState().equals(ShopInfoEntity.STATE_OK)) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:state类型错误");
                        }
                        break;

                    case "remark2":
                        if (StringUtil.isEmpty(shopOpenSubLogEntity.getRemark2())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:remark2为空");
                        }
                        break;

                }
            }
        } else if (baseEntity instanceof BusinessAccountEntity) {
            BusinessAccountEntity businessAccountEntity = (BusinessAccountEntity) baseEntity;

            for (String item : params) {

                switch (item) {
                    case "id":
                        if (businessAccountEntity.getId() == null) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:id为空");
                        }
                        break;
                    case "merchant_id":

                        if (businessAccountEntity.getMerchant_id() == null) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:merchant_id为空");
                        }

                        break;

                    case "state":

                        if (StringUtil.isEmpty(businessAccountEntity.getState())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:state为空");
                        } else if (!businessAccountEntity.getState().equals(BusinessAccountEntity.STATE_OK)
                                && !businessAccountEntity.getState().equals(BusinessAccountEntity.STATE_DELETED)
                                && !businessAccountEntity.getState().equals(BusinessAccountEntity.STATE_LOCKED)) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数state异常:" + businessAccountEntity.getState());
                        }
                        break;
                    case "name":

                        if (StringUtil.isEmpty(businessAccountEntity.getName())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:name为空");
                        }

                        break;
                    case "username":

                        if (StringUtil.isEmpty(businessAccountEntity.getUsername())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:username为空");
                        }

                        break;
                    case "root":

                        if (StringUtil.isEmpty(businessAccountEntity.getRoot())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:root为空");
                        }

                        break;
                }

            }
        } else if (baseEntity instanceof BusinessRoleEntity) {
            BusinessRoleEntity businessRoleEntity = (BusinessRoleEntity) baseEntity;

            for (String item : params) {
                switch (item) {
                    case "id":
                        if (businessRoleEntity.getId() == null) {

                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:id为空");
                        }
                        break;

                    case "merchant_id":
                        if (businessRoleEntity.getMerchant_id() == null) {

                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:merchant_id为空");
                        }
                        break;

                    case "shop_id":
                        if (businessRoleEntity.getShop_id() == null) {

                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:shop_id为空");
                        }
                        break;
                    case "role_name":
                        if (StringUtil.isEmpty(businessRoleEntity.getRole_name())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:role_name为空");
                        }
                        break;
                    case "aright":
                        if (StringUtil.isEmpty(businessRoleEntity.getAright())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:aright为空");
                        }
                        break;

                    case "hright":
                        if (StringUtil.isEmpty(businessRoleEntity.getHright())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:hright为空");
                        }
                        break;

                    case "state":
                        if (StringUtil.isEmpty(businessRoleEntity.getState())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:state为空");
                        }
                        break;
                }
            }
        } else if (baseEntity instanceof BusinessMenuEntity) {

            BusinessMenuEntity businessMenuEntity = (BusinessMenuEntity) baseEntity;

            for (String item : params) {
                switch (item) {
                    case "id":
                        if (businessMenuEntity.getId() == null) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:id为空");
                        }
                        break;
                    case "name":
                        if (StringUtil.isEmpty(businessMenuEntity.getName())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:name为空");
                        }
                        break;
                    case "pid":
                        if (businessMenuEntity.getPid() == null) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:pid为空");
                        }
                        break;
                    case "type":
                        if (StringUtil.isEmpty(businessMenuEntity.getType())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:type为空");
                        }
                        break;
                    case "icon":
                        if (StringUtil.isEmpty(businessMenuEntity.getIcon())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:icon为空");
                        }
                        break;

                    case "url":
                        if (StringUtil.isEmpty(businessMenuEntity.getUrl())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:url为空");
                        }
                        break;
                    case "hashkEY":
                        if (StringUtil.isEmpty(businessMenuEntity.getHashkEY())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:hashkEY为空");
                        }
                        break;

                    case "owner":
                        if (StringUtil.isEmpty(businessMenuEntity.getOwner())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:owner为空");
                        }
                        break;

                    case "state":
                        if (StringUtil.isEmpty(businessMenuEntity.getState())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:state为空");
                        }
                        break;

                    case "sort":
                        if (businessMenuEntity.getSort() == null) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:sort为空");
                        }
                        break;
                }
            }

        } else if (baseEntity instanceof ShopInfoEntity) {
            ShopInfoEntity shopInfoEntity = (ShopInfoEntity) baseEntity;
            for (String item : params) {
                switch (item) {
                    case "id":
                        if (shopInfoEntity.getId() == null) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:id为空");
                        }
                        break;

                    case "merchant_id":
                        if (shopInfoEntity.getMerchant_id() == null) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:merchant_id为空");
                        }
                        break;
                    case "name":
                        if (StringUtil.isEmpty(shopInfoEntity.getName())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:name为空");
                        }
                        break;

                    case "state":
                        if (StringUtil.isEmpty(shopInfoEntity.getState())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:state为空");
                        } else if (!shopInfoEntity.getState().equals(ShopInfoEntity.STATE_SUBMIT)
                                && !shopInfoEntity.getState().equals(ShopInfoEntity.STATE_SUBMIT)
                                && !shopInfoEntity.getState().equals(ShopInfoEntity.STATE_SUBMIT)) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:state异常," + shopInfoEntity.getState());
                        }
                        break;

                    case "address":
                        if (StringUtil.isEmpty(shopInfoEntity.getAddress())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:address为空");
                        }
                        break;

                    case "shop_lng":
                        if (StringUtil.isEmpty(shopInfoEntity.getShop_lng())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:shop_lng为空");
                        }
                        break;

                    case "shop_lat":
                        if (StringUtil.isEmpty(shopInfoEntity.getShop_lat())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:shop_lat为空");
                        }
                        break;

                    case "shop_desc":
                        if (StringUtil.isEmpty(shopInfoEntity.getShop_desc())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:shop_desc为空");
                        }
                        break;
                    case "banner":
                        if (StringUtil.isEmpty(shopInfoEntity.getBanner())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:banner为空");
                        }
                        break;

                    case "biz_scope":
                        if (StringUtil.isEmpty(shopInfoEntity.getBiz_scope())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:biz_scope为空");
                        }
                        break;

                    case "ton_time":
                        if (StringUtil.isEmpty(shopInfoEntity.getTon_time())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:ton_time为空");
                        }
                        break;
                    case "exp_date":
                        if (StringUtil.isEmpty(shopInfoEntity.getExp_date())) {
                            throw new BizException(ExceptionCode.ERRORCODR_PARAMS_NULL, "参数:exp_date为空");
                        }
                        break;

                }
            }

        }

    }
}

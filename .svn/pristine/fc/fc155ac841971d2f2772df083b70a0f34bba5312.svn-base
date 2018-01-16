package cn.pahot.business.entity;

import com.boc.common.metatype.BaseEntity;

/**
 * creat by @author:chen in 2017\12\21 0021
 *
 * @description:商家店铺申请处理记录
 **/
public class ShopOpenSolveLogEntity extends BaseEntity {

    private static final long serialVersionUID = -850104074099487451L;
    private Long id;//          BIGINT not null comment '店铺审核日志记录编号',
    private Long shop_ask_id;//       BIGINT not null comment '店铺开通申请记录编号,关联PT_BUSINESS.PT_BUSINESS_MERCHANT_SHOP_ASK.ID',
    private String state;//          VARCHAR(2) not null comment '审核结果(状态),01审核通过,02审核未通过',
    private String remark;//           VARCHAR(512) comment '审核描述,对审核结果的描述',

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShop_ask_id() {
        return shop_ask_id;
    }

    public void setShop_ask_id(Long shop_ask_id) {
        this.shop_ask_id = shop_ask_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

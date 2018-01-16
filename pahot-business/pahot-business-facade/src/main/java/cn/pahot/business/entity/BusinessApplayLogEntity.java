package cn.pahot.business.entity;

import com.boc.common.metatype.BaseEntity;


/**
 * creat by @author:chen in 2017\12\21 0021
 *
 * @description:商家审核日志
 **/
public class BusinessApplayLogEntity extends BaseEntity {
    private static final long serialVersionUID = 1443999948669435855L;

    private Long id;//                BIGINT not null comment '商家入驻审核日志编号',
    private Long mer_ask_id;//       BIGINT not null comment '商家入驻申请编号,关联PT_BUSINESS.PT_BUSINESS_MERCHANT_ASK.ID',
    private String state;//           VARCHAR(2) not null comment '审核结果(状态),01审核通过,02审核未通过',
    private String remark;//           VARCHAR(512) comment '审核描述,对审核结果的描述',
    private String expiration;        //VARCHAR(50) comment '店铺到期时间',

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMer_ask_id() {
        return mer_ask_id;
    }

    public void setMer_ask_id(Long mer_ask_id) {
        this.mer_ask_id = mer_ask_id;
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


    public String getExpiration() {
        return expiration;
    }

    public BusinessApplayLogEntity setExpiration(String expiration) {
        this.expiration = expiration;
        return this;
    }
}

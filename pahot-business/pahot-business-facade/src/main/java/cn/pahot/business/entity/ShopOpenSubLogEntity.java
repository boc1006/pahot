package cn.pahot.business.entity;

import com.boc.common.metatype.BaseEntity;

/**
 * creat by @author:chen in 2017\12\21 0021
 *
 * @description:店铺开通申请记录
 **/
public class ShopOpenSubLogEntity extends BaseEntity {

    //01审核通过,02审核未通过,03提交审核
    public static final String STATE_OK = "01";
    public static final String STATE_REFUSE = "02";
    public static final String STATE_SUBMIT = "03";


    private static final long serialVersionUID = -6521537012334446508L;
    private Long id;//        BIGINT not null comment '店铺开通申请记录编号',
    private Integer merchant_id;//       INT not null comment '商家编号,联系PT_BUSINESS.PT_BUSINESS_MERCHANT.ID',
    private String name;//           VARCHAR(64) not null comment '店铺名称',
    private String address;//           VARCHAR(128) not null comment '店铺地址',
    private String shop_lng;//           VARCHAR(100) comment '店铺经度',
    private String shop_lat;//         VARCHAR(100) comment '店铺纬度',
    private String shop_desc;//          VARCHAR(512) comment '店铺描述',
    private String biz_scope;//         VARCHAR(512) comment '经营范围描述',
    private String remark;//             VARCHAR(512) comment '其它申请描述',
    private Integer aa03;//           INT comment '审核人编号',
    private Long aa04;//           BIGINT comment '审核时间',
    private String state;//          VARCHAR(2) comment '审核结果(状态),01审核通过,02审核未通过',
    private String remark2;//        VARCHAR(512) comment '审核描述,对审核结果的描述',

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(Integer merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShop_lng() {
        return shop_lng;
    }

    public void setShop_lng(String shop_lng) {
        this.shop_lng = shop_lng;
    }

    public String getShop_lat() {
        return shop_lat;
    }

    public void setShop_lat(String shop_lat) {
        this.shop_lat = shop_lat;
    }

    public String getShop_desc() {
        return shop_desc;
    }

    public void setShop_desc(String shop_desc) {
        this.shop_desc = shop_desc;
    }

    public String getBiz_scope() {
        return biz_scope;
    }

    public void setBiz_scope(String biz_scope) {
        this.biz_scope = biz_scope;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAa03() {
        return aa03;
    }

    public void setAa03(Integer aa03) {
        this.aa03 = aa03;
    }

    public Long getAa04() {
        return aa04;
    }

    public void setAa04(Long aa04) {
        this.aa04 = aa04;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }
}

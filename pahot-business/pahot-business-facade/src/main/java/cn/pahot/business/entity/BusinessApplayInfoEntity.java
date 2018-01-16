package cn.pahot.business.entity;

import com.boc.common.metatype.BaseEntity;

/**
 * creat by @author:chen in 2017\12\21 0021
 *
 * @description:商家申请
 **/
public class BusinessApplayInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 4999018712752445375L;
    private Long id;//              BIGINT not null comment '商家入驻申请编号',
    private String merchant_name;//        VARCHAR(128) not null comment '商家名称',
    private String located_type;//         VARCHAR(2) not null comment '入驻类型,01供应商,02销售商,00供应商+销售商',
    private String phone;//           VARCHAR(20) not null comment '申请人手机号码',
    private String username;//            VARCHAR(64) not null comment '申请人姓名',
    private String address;//            VARCHAR(256) not null comment '商家地址',
    private String idcard;//    身份照
    private String biz_lng;//            VARCHAR(100) comment '公司地址经度信息',
    private String biz_lat;//             VARCHAR(100) comment '公司地址纬度信息',
    private String name;//              VARCHAR(64) not null comment '店铺名称',
    private String address2;//         VARCHAR(128) not null comment '店铺地址',
    private String shop_lng;//            VARCHAR(100) comment '店铺经度',
    private String shop_lat;//          VARCHAR(100) comment '店铺纬度',
    private String shop_desc;//         VARCHAR(512) comment '店铺描述',
    private String biz_scope;//          VARCHAR(512) comment '经营范围描述',
    private Long cmttime;//          BIGINT not null comment '申请提交时间',
    private String remark;//            VARCHAR(512) comment '其它申请描述',
    private String info;//           VARCHAR(2000) comment '申请资料电子文档,多个使用","分隔',
    private String state;//             VARCHAR(2) comment '审核结果(状态),01审核通过,02审核未通过' 03审核中,
    private String remark2;//           VARCHAR(512) comment '审核描述,对审核结果的描述',

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getLocated_type() {
        return located_type;
    }

    public void setLocated_type(String located_type) {
        this.located_type = located_type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getBiz_lng() {
        return biz_lng;
    }

    public void setBiz_lng(String biz_lng) {
        this.biz_lng = biz_lng;
    }

    public String getBiz_lat() {
        return biz_lat;
    }

    public void setBiz_lat(String biz_lat) {
        this.biz_lat = biz_lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
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

    public Long getCmttime() {
        return cmttime;
    }

    public void setCmttime(Long cmttime) {
        this.cmttime = cmttime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

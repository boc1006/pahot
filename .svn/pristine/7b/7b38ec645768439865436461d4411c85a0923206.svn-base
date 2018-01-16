package cn.pahot.business.entity;

import com.boc.common.metatype.BaseEntity;

/**
 * creat by @author:chen in 2017\12\21 0021
 *
 * @description:店铺信息
 **/
public class ShopInfoEntity extends BaseEntity {

    //01审核通过,02审核未通过,03提交审核
    public static final String STATE_OK = "01";
    public static final String STATE_REFUSE = "02";
    public static final String STATE_SUBMIT = "03";

    private static final long serialVersionUID = 7583968702561361446L;
    private Integer id;//             INT not null auto_increment comment '店铺编号,自动增长列,初始值=10000',
    private Integer merchant_id;//       INT not null comment '商家编号,关联PT_BUSINESS.PT_BUSINESS_MERCHANT.ID',
    private String name;//        VARCHAR(64) not null comment '店铺名称',
    private String state;//          VARCHAR(2) not null comment '店铺状态,00申请开通,01已开通,02冻结,03注销,04开通未通过',
    private String address;//           VARCHAR(128) not null comment '店铺地址',
    private String shop_lng;//          VARCHAR(100) comment '店铺经度',
    private String shop_lat;//          VARCHAR(100) comment '店铺纬度',
    private String shop_desc;//          VARCHAR(512) comment '店铺描述',
    private String banner;//         VARCHAR(256) comment '店铺横幅',
    private String biz_scope;//         VARCHAR(512) comment '经营范围描述',
    private String ton_time;//          VARCHAR(20) not null comment '店铺开通时间',
    private String exp_date;//         VARCHAR(20) not null comment '店铺有效时间',

    public Integer getId() {
        return id;
    }

    public ShopInfoEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getMerchant_id() {
        return merchant_id;
    }

    public ShopInfoEntity setMerchant_id(Integer merchant_id) {
        this.merchant_id = merchant_id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShopInfoEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getState() {
        return state;
    }

    public ShopInfoEntity setState(String state) {
        this.state = state;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ShopInfoEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getShop_lng() {
        return shop_lng;
    }

    public ShopInfoEntity setShop_lng(String shop_lng) {
        this.shop_lng = shop_lng;
        return this;
    }

    public String getShop_lat() {
        return shop_lat;
    }

    public ShopInfoEntity setShop_lat(String shop_lat) {
        this.shop_lat = shop_lat;
        return this;
    }

    public String getShop_desc() {
        return shop_desc;
    }

    public ShopInfoEntity setShop_desc(String shop_desc) {
        this.shop_desc = shop_desc;
        return this;
    }

    public String getBanner() {
        return banner;
    }

    public ShopInfoEntity setBanner(String banner) {
        this.banner = banner;
        return this;
    }

    public String getBiz_scope() {
        return biz_scope;
    }

    public ShopInfoEntity setBiz_scope(String biz_scope) {
        this.biz_scope = biz_scope;
        return this;
    }

    public String getTon_time() {
        return ton_time;
    }

    public ShopInfoEntity setTon_time(String ton_time) {
        this.ton_time = ton_time;
        return this;
    }

    public String getExp_date() {
        return exp_date;
    }

    public ShopInfoEntity setExp_date(String exp_date) {
        this.exp_date = exp_date;
        return this;
    }
}

package cn.pahot.business.entity;

import com.boc.common.metatype.BaseEntity;

/**
 * creat by @author:chen in 2017\12\21 0021
 *
 * @description:商家子账户
 **/
public class BusinessAccountEntity extends BaseEntity {

    //01开通,02锁定,00删除
    public static final String STATE_OK = "01";
    public static final String STATE_LOCKED = "02";
    public static final String STATE_DELETED = "00";
    //超级管理员
    public static final String ROOT_SUPPER = "1";
    //普通用户
    public static final String ROOT_NORMAL = "0";


    private static final long serialVersionUID = 5061326904638909518L;
    private Integer id;//           INT not null comment '会员登录号,关联PT_MEMBER.PT_MEMBER_ACCOUNT.ID',
    private Integer merchant_id;//        INT not null comment '商家编号,关联PT_BUSINESS.PT_BUSINESS_MERCHANT.ID',
    private String state;//          VARCHAR(2) not null comment '子商户会员状态,01开通,02锁定,00删除',
    private String name;//           VARCHAR(32) not null comment '会员姓名',
    private String username;//           VARCHAR(16) not null comment '会员登录账号',
    private String root;//            VARCHAR(1) not null default '0' comment '是否超级管理员,1是,0否,默认为0',

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(Integer merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }
}

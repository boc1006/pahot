package cn.pahot.business.entity;

import com.boc.common.metatype.BaseEntity;

/**
 * creat by @author:chen in 2017\12\21 0021
 *
 * @description:商家角色
 **/
public class BusinessRoleEntity extends BaseEntity {

    public static final int ROLE_ADMIN = 0;//超级管理员角色


    private static final long serialVersionUID = -2590633738095402072L;
    private Integer id;//                INT not null comment '商家角色编号',
    private Integer merchant_id;//         INT not null comment '商家编号,关联PT_BUSINESS.PT_BUSINESS_MERCHANT.ID',
    private Integer shop_id;//          INT not null comment '店铺编号,关联PT_BUSINESS.PT_BUSINESS_MERCHANT_SHOP.ID',
    private String role_name;//          VARCHAR(32) not null comment '角色名称',
    private String aright;//          VARCHAR(2000) not null comment '菜单访问权限',
    private String hright;//         VARCHAR(2000) not null comment '功能操作权限',
    private String state;//       VARCHAR(2) not null comment '角色状态,01正常,02停用,00删除',

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

    public Integer getShop_id() {
        return shop_id;
    }

    public void setShop_id(Integer shop_id) {
        this.shop_id = shop_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getAright() {
        return aright;
    }

    public void setAright(String aright) {
        this.aright = aright;
    }

    public String getHright() {
        return hright;
    }

    public void setHright(String hright) {
        this.hright = hright;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

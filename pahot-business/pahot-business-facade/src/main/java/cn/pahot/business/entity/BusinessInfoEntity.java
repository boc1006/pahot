package cn.pahot.business.entity;

import com.boc.common.metatype.BaseEntity;


/**
 * creat by @author:chen in 2017\12\21 0021
 *
 * @description:商家信息
 **/
public class BusinessInfoEntity extends BaseEntity {

    //商家状态:01入驻,02冻结,03注销
    public static final String BUSINESS_STATE_OK = "01";
    public static final String BUSINESS_STATE_ERROR = "02";
    public static final String BUSINESS_STATE_DELETE = "03";

    private static final long serialVersionUID = -9214668311836553757L;
    private String idcard;
    private Long ask_id;   // 商家申请记录
    private Integer id;    //      int not null auto_increment comment '商家编号,自动增长列,初始值=10000',
    private String name;    //          varchar(128) not null comment '商家名称',
    private String located_type;    //        varchar(2) not null comment '入驻类型,01供应商,02销售商,00供应商+销售商',
    private Integer mid;    //     int not null comment '会员编号,关联pt_member.pt_member_account.id',
    private String auth_phone;    //      varchar(20) not null comment '认证手机号',
    private String auth_name;    //       varchar(64) not null comment '认证人姓名',
    private String other_concat;    //       varchar(128) comment '其它联系方式',
    private String email;    //       varchar(64) comment '邮箱地址',
    private String state;    //       varchar(2) not null comment '商家状态,01入驻,02冻结,03注销',
    private String supply_state;    //       varchar(2) not null comment '供应商状态,00未开通,01开通,02冻结,03注销,04申请开通,05申请未通过',
    private String sale_state;    //      varchar(2) not null comment '销售商状态,00未开通,01开通,02冻结,03注销,04申请开通,05申请未通过',
    private String located_date;    //       varchar(20) not null comment '入驻日期',
    private String exp_date;    //         varchar(20) not null comment '合同截止日期',
    private String biz_lng;    //         varchar(100) comment '公司地址经度信息',
    private String biz_lat;    //          varchar(100) comment '公司地址纬度信息',
    private String remark;    //         varchar(512) comment '商家备注',

    public String getIdcard() {
        return idcard;
    }

    public BusinessInfoEntity setIdcard(String idcard) {
        this.idcard = idcard;
        return this;
    }

    public Long getAsk_id() {
        return ask_id;
    }

    public BusinessInfoEntity setAsk_id(Long ask_id) {
        this.ask_id = ask_id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public BusinessInfoEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BusinessInfoEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getLocated_type() {
        return located_type;
    }

    public BusinessInfoEntity setLocated_type(String located_type) {
        this.located_type = located_type;
        return this;
    }

    public Integer getMid() {
        return mid;
    }

    public BusinessInfoEntity setMid(Integer mid) {
        this.mid = mid;
        return this;
    }

    public String getAuth_phone() {
        return auth_phone;
    }

    public BusinessInfoEntity setAuth_phone(String auth_phone) {
        this.auth_phone = auth_phone;
        return this;
    }

    public String getAuth_name() {
        return auth_name;
    }

    public BusinessInfoEntity setAuth_name(String auth_name) {
        this.auth_name = auth_name;
        return this;
    }

    public String getOther_concat() {
        return other_concat;
    }

    public BusinessInfoEntity setOther_concat(String other_concat) {
        this.other_concat = other_concat;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BusinessInfoEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getState() {
        return state;
    }

    public BusinessInfoEntity setState(String state) {
        this.state = state;
        return this;
    }

    public String getSupply_state() {
        return supply_state;
    }

    public BusinessInfoEntity setSupply_state(String supply_state) {
        this.supply_state = supply_state;
        return this;
    }

    public String getSale_state() {
        return sale_state;
    }

    public BusinessInfoEntity setSale_state(String sale_state) {
        this.sale_state = sale_state;
        return this;
    }

    public String getLocated_date() {
        return located_date;
    }

    public BusinessInfoEntity setLocated_date(String located_date) {
        this.located_date = located_date;
        return this;
    }

    public String getExp_date() {
        return exp_date;
    }

    public BusinessInfoEntity setExp_date(String exp_date) {
        this.exp_date = exp_date;
        return this;
    }

    public String getBiz_lng() {
        return biz_lng;
    }

    public BusinessInfoEntity setBiz_lng(String biz_lng) {
        this.biz_lng = biz_lng;
        return this;
    }

    public String getBiz_lat() {
        return biz_lat;
    }

    public BusinessInfoEntity setBiz_lat(String biz_lat) {
        this.biz_lat = biz_lat;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public BusinessInfoEntity setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}

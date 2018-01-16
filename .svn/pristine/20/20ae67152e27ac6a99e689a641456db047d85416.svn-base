package cn.pahot.upms.entity;

import java.io.Serializable;

/**
 * creat by @author:chen in 2017\12\7 0007
 *
 * @description:全局数据字典实体类
 **/
public class DataIndexEntity implements Serializable {
    private static final long serialVersionUID = 6728727992366426384L;

    private Long id;//                   int not null auto_increment comment '字典编号,自动增长,初始值=10000',
    private String sid;//                varchar(6) not null comment '系统编号,关联pt_upms.pt_upms_sysconf.id',
    private String field;//            varchar(10) not null comment '对照字段',
    private String fieldname;//           varchar(20) not null comment '对照字段名称',
    private String code;//               varchar(10) not null comment '代码',
    private String codedesc;//            varchar(512) not null comment '代码描述',
    private String enabled;//            varchar(2) not null default '01' comment '启用状态(01启用,00停用),默认值=01',
    private String editmode;//          varchar(2) not null default '01' comment '编辑模式(01可编辑,00禁止编辑),默认值=01',
    private Integer sort;//              tinyint not null default 1 comment '排序号,默认为1',
    private String remark;//             varchar(128) comment '字典描述',
    private Integer aa01;//               int not null comment '创建人',
    private Long aa02;//               bigint not null comment '创建时间',
    private Integer ab01;//               int comment '修改人',
    private Long ab02;//            bigint comment '修改时间',


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodedesc() {
        return codedesc;
    }

    public void setCodedesc(String codedesc) {
        this.codedesc = codedesc;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getEditmode() {
        return editmode;
    }

    public void setEditmode(String editmode) {
        this.editmode = editmode;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAa01() {
        return aa01;
    }

    public void setAa01(Integer aa01) {
        this.aa01 = aa01;
    }

    public Long getAa02() {
        return aa02;
    }

    public void setAa02(Long aa02) {
        this.aa02 = aa02;
    }

    public Integer getAb01() {
        return ab01;
    }

    public void setAb01(Integer ab01) {
        this.ab01 = ab01;
    }

    public Long getAb02() {
        return ab02;
    }

    public void setAb02(Long ab02) {
        this.ab02 = ab02;
    }
}

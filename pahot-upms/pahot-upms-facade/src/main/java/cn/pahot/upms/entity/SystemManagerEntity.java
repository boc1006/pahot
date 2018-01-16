package cn.pahot.upms.entity;


import java.io.Serializable;

/**
 * creat by @author:chen in 2017\12\6 0006
 *
 * @description:系统管理实体
 **/
public class SystemManagerEntity implements Serializable {

    private static final long serialVersionUID = 694227273002235176L;


    private String id;       //            VARCHAR(6) not null comment '系统编号',
    private String name;    //            VARCHAR(64) not null comment '系统名称',
    private String type;   //             VARCHAR(2) not null comment '系统类型,01管理系统,02业务系统,03运维系统',
    private String state = "01";    //          VARCHAR(2) not null default '01' comment '系统状态(01启用,00停用),默认值=01',
    private String url;    //            VARCHAR(128) not null comment '系统访问根路径',
    private String remark;   //              VARCHAR(128) comment '系统描述',
    private String logo;    //             VARCHAR(128) comment 'LOGO图标',
    private Integer sort = 1;   //               TINYINT not null default 1 comment '排序序号,默认值=1',
    private Integer aa01;   //               INT not null comment '创建人',
    private Long aa02;    //             BIGINT not null comment '创建时间',
    private Integer ab01;    //                 INT comment '修改人',
    private Long ab02;   //              BIGINT comment '修改时间',


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

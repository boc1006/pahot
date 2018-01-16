package cn.pahot.business.entity;

import com.boc.common.metatype.BaseEntity;

/**
 * creat by @author:chen in 2017\12\21 0021
 *
 * @description:商家菜单
 **/
public class BusinessMenuEntity extends BaseEntity {
    private static final long serialVersionUID = 7975448839654664465L;

    private Integer id;//              INT not null comment '商家菜单编号',
    private String name;//               VARCHAR(32) not null comment '商家菜单名称',
    private Integer pid;//               INT not null comment '上级菜单编号,顶级为0',
    private String type;//               VARCHAR(2) not null comment '商家菜单类型,01目录,02菜单',
    private String icon;//             VARCHAR(128) comment '商家菜单图标',
    private String url;//              VARCHAR(128) not null comment '商家菜单访问路径',
    private String hashkEY;//            VARCHAR(32) not null comment '商家菜单HASHKEY',
    private String owner;//            VARCHAR(2) not null default '1' comment '商家菜单拥有者,1商家,2 顾问',
    private String state;//           VARCHAR(2) not null comment '菜单状态,1正常,2停用,0删除',
    private Integer sort;//              INT not null comment '排序',


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHashkEY() {
        return hashkEY;
    }

    public void setHashkEY(String hashkEY) {
        this.hashkEY = hashkEY;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}

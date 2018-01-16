package cn.pahot.goods.entity;

import java.io.Serializable;

/**
 * 商品分类实体
 */
public class GoodsTypeEntity implements Serializable {
    private Integer id;
    private String name;
    private String ename;
    private Integer parentId;
    private String icon;
    private Integer sort;
    private String path;
    private String state;
    private Integer aa01;
    private Long aa02;
    private Integer ab01;
    private Long ab02;


    public Integer getId() {
        return id;
    }

    public GoodsTypeEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GoodsTypeEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getEname() {
        return ename;
    }

    public GoodsTypeEntity setEname(String ename) {
        this.ename = ename;
        return this;
    }

    public Integer getParentId() {
        return parentId;
    }

    public GoodsTypeEntity setParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public GoodsTypeEntity setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public GoodsTypeEntity setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getPath() {
        return path;
    }

    public GoodsTypeEntity setPath(String path) {
        this.path = path;
        return this;
    }

    public String getState() {
        return state;
    }

    public GoodsTypeEntity setState(String state) {
        this.state = state;
        return this;
    }

    public Integer getAa01() {
        return aa01;
    }

    public GoodsTypeEntity setAa01(Integer aa01) {
        this.aa01 = aa01;
        return this;
    }

    public Long getAa02() {
        return aa02;
    }

    public GoodsTypeEntity setAa02(Long aa02) {
        this.aa02 = aa02;
        return this;
    }

    public Integer getAb01() {
        return ab01;
    }

    public GoodsTypeEntity setAb01(Integer ab01) {
        this.ab01 = ab01;
        return this;
    }

    public Long getAb02() {
        return ab02;
    }

    public GoodsTypeEntity setAb02(Long ab02) {
        this.ab02 = ab02;
        return this;
    }

    @Override
    public String toString() {
        return "GoodsTypeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ename='" + ename + '\'' +
                ", parentId=" + parentId +
                ", icon='" + icon + '\'' +
                ", sort=" + sort +
                ", path='" + path + '\'' +
                ", state='" + state + '\'' +
                ", aa01=" + aa01 +
                ", aa02=" + aa02 +
                ", ab01=" + ab01 +
                ", ab02=" + ab02 +
                '}';
    }
}

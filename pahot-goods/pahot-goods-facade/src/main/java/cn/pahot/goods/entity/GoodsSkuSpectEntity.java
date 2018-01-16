package cn.pahot.goods.entity;

import java.io.Serializable;

/**
 * 商品sku属性
 */
public class GoodsSkuSpectEntity implements Serializable {
    /**
     * 商品规格编号
     */
    private Integer id;
    /**
     * 商品规格分类,1-销售属性1,2-销售属性2,3-销售属性3
     */
    private String type;
    /**
     * 商品规格分类名称
     */
    private String typename;
    /**
     * 商品规格显示名称
     */
    private String name;
    /**
     * 商品规格显示值
     */
    private String val;
    /**
     * 排序
     */
    private Integer sort;

    public Integer getId() {
        return id;
    }

    public GoodsSkuSpectEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public GoodsSkuSpectEntity setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getType() {
        return type;
    }

    public GoodsSkuSpectEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getTypename() {
        return typename;
    }

    public GoodsSkuSpectEntity setTypename(String typename) {
        this.typename = typename;
        return this;
    }

    public String getName() {
        return name;
    }

    public GoodsSkuSpectEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getVal() {
        return val;
    }

    public GoodsSkuSpectEntity setVal(String val) {
        this.val = val;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    @Override
    public String toString() {
        return "GoodsSkuSpectEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", typename='" + typename + '\'' +
                ", name='" + name + '\'' +
                ", val='" + val + '\'' +
                ", sort=" + sort +
                '}';
    }
}

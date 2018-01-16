package cn.pahot.goods.entity;

import java.io.Serializable;

/**
 * 商品sku属性
 */
public class GoodsSkuAttrEntity implements Serializable {
    /**
     * 商品SKU属性ID,自动增长列,默认值=1000
     */
    private Integer id;
    /**
     * 商品分类ID,关联PT_GOODS.PT_GOODS_TYPE.ID
     */
    private Integer goodsTypeId;   //
    /**
     * 商品SKU规格ID,关联PT_GOODS.PT_GOODS_SKU_SPECT.ID
     */
    private Integer skuSpecId;
    /**
     * 商品SKU规格分类ID,关联PT_GOODS.PT_GOODS_SKU_SPECT.TYPE
     */
    private String skuSpecType;

    /**
     * 商品SKU规格分类名称
     */
    private String skuSpecTypeName;
    /**
     * 商品SKU规格显示名称
     */
    private String skuSpecName;
    /**
     * 商品SKU规格显示值
     */
    private String skuSpecVal;
    /**
     * 排序
     */
    private Integer sort;
    private Integer aa01;
    private Long aa02;
    private Integer ab01;
    private Long ab02;

    public Integer getId() {
        return id;
    }

    public GoodsSkuAttrEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public GoodsSkuAttrEntity setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
        return this;
    }

    public Integer getSkuSpecId() {
        return skuSpecId;
    }

    public GoodsSkuAttrEntity setSkuSpecId(Integer skuSpecId) {
        this.skuSpecId = skuSpecId;
        return this;
    }

    public String getSkuSpecType() {
        return skuSpecType;
    }

    public GoodsSkuAttrEntity setSkuSpecType(String skuSpecType) {
        this.skuSpecType = skuSpecType;
        return this;
    }

    public String getSkuSpecTypeName() {
        return skuSpecTypeName;
    }

    public GoodsSkuAttrEntity setSkuSpecTypeName(String skuSpecTypeName) {
        this.skuSpecTypeName = skuSpecTypeName;
        return this;
    }

    public String getSkuSpecName() {
        return skuSpecName;
    }

    public GoodsSkuAttrEntity setSkuSpecName(String skuSpecName) {
        this.skuSpecName = skuSpecName;
        return this;
    }

    public String getSkuSpecVal() {
        return skuSpecVal;
    }

    public GoodsSkuAttrEntity setSkuSpecVal(String skuSpecVal) {
        this.skuSpecVal = skuSpecVal;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public GoodsSkuAttrEntity setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getAa01() {
        return aa01;
    }

    public GoodsSkuAttrEntity setAa01(Integer aa01) {
        this.aa01 = aa01;
        return this;
    }

    public Long getAa02() {
        return aa02;
    }

    public GoodsSkuAttrEntity setAa02(Long aa02) {
        this.aa02 = aa02;
        return this;
    }

    public Integer getAb01() {
        return ab01;
    }

    public GoodsSkuAttrEntity setAb01(Integer ab01) {
        this.ab01 = ab01;
        return this;
    }

    public Long getAb02() {
        return ab02;
    }

    public GoodsSkuAttrEntity setAb02(Long ab02) {
        this.ab02 = ab02;
        return this;
    }

    @Override
    public String toString() {
        return "GoodsSkuAttrEntity{" +
                "id=" + id +
                ", goodsTypeId=" + goodsTypeId +
                ", skuSpecId=" + skuSpecId +
                ", skuSpecType=" + skuSpecType +
                ", skuSpecTypeName='" + skuSpecTypeName + '\'' +
                ", skuSpecName='" + skuSpecName + '\'' +
                ", skuSpecVal='" + skuSpecVal + '\'' +
                ", sort=" + sort +
                ", aa01=" + aa01 +
                ", aa02=" + aa02 +
                ", ab01=" + ab01 +
                ", ab02=" + ab02 +
                '}';
    }
}

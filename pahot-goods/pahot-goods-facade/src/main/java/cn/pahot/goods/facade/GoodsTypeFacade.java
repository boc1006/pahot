package cn.pahot.goods.facade;

import cn.pahot.goods.entity.GoodsSkuAttrEntity;
import cn.pahot.goods.entity.GoodsSkuSpectEntity;
import cn.pahot.goods.entity.GoodsTypeEntity;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.page.PageParams;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 商品类型接口
 */
public interface GoodsTypeFacade {
    /**
     * 添加商品分类
     *
     * @param goodsTypeEntity goodsTypeEntity.name 分类名称
     *                        goodsTypeEntity.parentId  父级分类id 无父级分类是填0
     *                        goodsTypeEntity.sort  序号 默认俺降序排列
     *                        goodsTypeEntity.aa01  添加人
     * @throws BizException
     */
    void addGoodsType(GoodsTypeEntity goodsTypeEntity) throws BizException;

    /**
     * 更新商品分类
     *
     * @param goodsTypeEntity goodsTypeEntity.id  序号 默认俺降序排列
     *                        goodsTypeEntity.ab01  添加人
     * @throws BizException
     */
    void updateGoodsType(GoodsTypeEntity goodsTypeEntity) throws BizException;

    /**
     * 更新商品分类状态
     *
     * @param id     商品分类id
     * @param state  商品分类状态  00 ,01 02
     * @param userId 操作人id
     * @throws BizException
     */
    void updateGoodsTypeState(int id, String state, int userId) throws BizException;

    /**
     * 分页查询 商品分类信息
     *
     * @param params
     * @return
     */
    PageInfo<GoodsTypeEntity> getGoodsTypeForPage(PageParams<DTO<String, String>> params) throws BizException;


    /**
     * 查询所有商品分类
     *
     * @param goodsTypeEntity
     * @return
     */
    List<GoodsTypeEntity> getGoodsTypeList(GoodsTypeEntity goodsTypeEntity) throws BizException;


    // 商品属性 接口

    /**
     * 添加商品属性
     * 参数都是必填项
     *
     * @param goodsSkuAttrEntity goodsSkuAttrEntity.goodsTypeId 商品分类id
     *                           goodsSkuAttrEntity.skuSpecId 商品sku规格id
     *                           goodsSkuAttrEntity.skuSpecType 商品sku规格分类
     *                           goodsSkuAttrEntity.skuSpecTypeName 商品sku规格分类名称
     *                           goodsSkuAttrEntity.skuSpecName 商品sku规格显示名称
     *                           goodsSkuAttrEntity.skuSpecVal sku值
     *                           goodsSkuAttrEntity.sort 排序
     *                           goodsSkuAttrEntity.aa01  添加人id
     * @throws BizException
     */
    void addGoodsSkuAttr(GoodsSkuAttrEntity goodsSkuAttrEntity) throws BizException;

    /**
     * 批量添加或者更新商品属性
     *
     * @param gsaeList 待更新或者添加的 商品属性 参数参见 addGoodsSkuAttr ,updateGoodsSkuAttr
     */
    void addOrUpdateGoodsSkuAttr(List<GoodsSkuAttrEntity> gsaeList);

    /**
     * 更新商品属性
     * 参数都是必填项
     *
     * @param goodsSkuAttrEntity goodsSkuAttrEntity.id
     *                           goodsSkuAttrEntity.goodsTypeId 商品分类id
     *                           goodsSkuAttrEntity.skuSpecId 商品sku规格id
     *                           goodsSkuAttrEntity.skuSpecType 商品sku规格分类
     *                           goodsSkuAttrEntity.skuSpecTypeName 商品sku规格分类名称
     *                           goodsSkuAttrEntity.skuSpecName 商品sku规格显示名称
     *                           goodsSkuAttrEntity.skuSpecVal sku值
     *                           goodsSkuAttrEntity.sort 排序
     *                           goodsSkuAttrEntity.ab01  更新人id
     * @throws BizException
     */
    void updateGoodsSkuAttr(GoodsSkuAttrEntity goodsSkuAttrEntity) throws BizException;

    /**
     * 删除商品属性
     *
     * @param id 商品属性Id
     * @throws BizException
     */
    void delGoodsSkuAttr(Integer id) throws BizException;

    /**
     * 删除商品属性
     *
     * @param goodsTypeId 商品分类id
     * @throws BizException
     */
    void delGoodsSkuAttrByGoodsTypeId(Integer goodsTypeId) throws BizException;

    /**
     * 分页查询商品sku属性
     *
     * @param pageParams pageParams.params.goodsTypeId  商品分类ID
     *                   pageParams.params.skuSpecId    商品SKU规格ID,
     *                   //主要参考前面两个
     *                   pageParams.params.skuSpecType   商品SKU规格分类
     *                   pageParams.params.skuSpecName  商品SKU规格分类名称
     * @return
     * @throws BizException
     */
    PageInfo<GoodsSkuAttrEntity> getGoodsSkuAttr(PageParams<DTO<String, String>> pageParams) throws BizException;


    /**
     * 查询商品属性列表
     *
     * @param params params.goodsTypeId  商品分类ID
     *               params.skuSpecId    商品SKU规格ID,
     *               主要参考前面两个
     *               params.skuSpecType   商品SKU规格分类
     *               params.skuSpecName  商品SKU规格分类名称
     * @return
     * @throws BizException
     */
    List<GoodsSkuAttrEntity> getGoodsSkuAttr(DTO<String, String> params) throws BizException;

    /**
     * 查询商品sku默认规则
     *
     * @param goodsSkuSpectEntity goodsSkuSpectEntity.TYPE  商品规格分类,1-销售属性1,2-销售属性2,3-销售属性3  可以为空
     *                            goodsSkuSpectEntity.TYPENAME
     * @return
     * @throws BizException
     */
    List<GoodsSkuSpectEntity> getGoodsSkuSpect(GoodsSkuSpectEntity goodsSkuSpectEntity) throws BizException;

    /**
     * 根据商品分类,商品skuType分类更新分类名称 (都是必传递参数)
     *
     * @param goodsTypeId     商品分类id
     * @param skuSpecType     商品skuType
     * @param skuSpecTypeName 商品skuType名称
     * @param userId          　操作用户id
     */
    void updateSkuTypeNaeByGoodsTypeIdAndSkuSpecType(Integer goodsTypeId, String skuSpecType, String skuSpecTypeName, Integer userId);
}

package cn.pahot.goods.dao;

import cn.pahot.goods.entity.GoodsTypeEntity;
import com.boc.common.metatype.DTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类Dao接口定义
 * <p>@Title: GoodsTypeMapper.java
 * <p>@Package cn.pahot.goods.dao
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com
 * <p>@date 2017年12月13日 下午5:14:38
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface GoodsTypeMapper {

    void addGoodsType(GoodsTypeEntity goodsTypeEntity);

    void updateGoodsType(GoodsTypeEntity goodsTypeEntity);

    void updateGoodsTypeState(DTO<String, String> dto);

    Page<GoodsTypeEntity> getGoodsTypeForPage(DTO<String, String> dto);

    List<GoodsTypeEntity> getGoodsTypeList(GoodsTypeEntity goodsTypeEntity);

    GoodsTypeEntity getGoodsTypeById(@Param("id") Integer id);

    List<GoodsTypeEntity> getGoodsTypeList2(DTO<String,String> params);


}

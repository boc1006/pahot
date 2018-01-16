package cn.pahot.goods.dao;


import cn.pahot.goods.entity.GoodsSkuAttrEntity;
import cn.pahot.goods.entity.GoodsSkuSpectEntity;
import com.boc.common.metatype.DTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsSkuAttrMapper {


    void addGoodsSkuAttr(GoodsSkuAttrEntity goodsSkuAttrEntity);

    List<String> getSkuSpecTypeName(DTO<String, String> dto);


    void updateGoodsSkuAttr(GoodsSkuAttrEntity goodsSkuAttrEntity);


    void delGoodsSkuAttr(@Param("id") Integer id);

    void delGoodsSkuAttrByGoodsTypeId(@Param("goodsTypeId") Integer goodsTypeId);

    Page<GoodsSkuAttrEntity> getGoodsSkuAttr(DTO<String, String> params);

    List<GoodsSkuAttrEntity> getGoodsSkuAttrList(DTO<String, String> params);


    List<GoodsSkuSpectEntity> getGoodsSkuSpect(GoodsSkuSpectEntity goodsSkuSpectEntity);


    void updateSkuTypeNaeByGoodsTypeIdAndSkuSpecType(DTO<String, String> dto);


}

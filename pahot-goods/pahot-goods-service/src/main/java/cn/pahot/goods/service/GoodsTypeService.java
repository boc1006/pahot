package cn.pahot.goods.service;

import cn.pahot.goods.biz.GoodsSkuAttrBiz;
import cn.pahot.goods.biz.GoodsTypeBiz;
import cn.pahot.goods.entity.GoodsSkuAttrEntity;
import cn.pahot.goods.entity.GoodsSkuSpectEntity;
import cn.pahot.goods.entity.GoodsTypeEntity;
import cn.pahot.goods.facade.GoodsTypeFacade;
import com.boc.common.core.test.annotation.DubboProviderMethod;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.page.PageParams;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类服务实现
 * <p>@Title: GoodsTypeService.java
 * <p>@Package cn.pahot.goods.service
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com
 * <p>@date 2017年12月13日 下午5:12:33
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */

@DubboProviderMethod(ip = "192.168.3.151", name = "徐杰", system = "商品管理子系统", systemNum = "100003")
@Service("goodsTypeFacade")
public class GoodsTypeService implements GoodsTypeFacade {

    @Autowired
    private GoodsTypeBiz goodsTypeBiz;

    @Override
    public void addGoodsType(GoodsTypeEntity goodsTypeEntity) throws BizException {
        goodsTypeBiz.addGoodsType(goodsTypeEntity);
    }

    @Override
    public void updateGoodsType(GoodsTypeEntity goodsTypeEntity) throws BizException {
        this.goodsTypeBiz.updateGoodsType(goodsTypeEntity);
    }

    @Override
    public void updateGoodsTypeState(int id, String state, int userId) throws BizException {
        this.goodsTypeBiz.updateGoodsTypeState(id, state, userId);
    }

    @Override
    public PageInfo<GoodsTypeEntity> getGoodsTypeForPage(PageParams<DTO<String, String>> params) throws BizException {
        return this.goodsTypeBiz.getGoodsTypeForPage(params);
    }

    @Override
    public List<GoodsTypeEntity> getGoodsTypeList(GoodsTypeEntity goodsTypeEntity) throws BizException {
        return this.goodsTypeBiz.getGoodsTypeList(goodsTypeEntity);
    }


    //商品属性接口

    @Autowired
    private GoodsSkuAttrBiz goodsSkuAttrBiz;

    @Override
    public void addGoodsSkuAttr(GoodsSkuAttrEntity goodsSkuAttrEntity) throws BizException {
        this.goodsSkuAttrBiz.addGoodsSkuAttr(goodsSkuAttrEntity);
    }

    @Override
    public void addOrUpdateGoodsSkuAttr(List<GoodsSkuAttrEntity> gsaeList) {
        goodsSkuAttrBiz.addOrUpdateGoodsSkuAttr(gsaeList);
    }

    @Override
    public void updateGoodsSkuAttr(GoodsSkuAttrEntity goodsSkuAttrEntity) throws BizException {
        goodsSkuAttrBiz.updateGoodsSkuAttr(goodsSkuAttrEntity);
    }

    @Override
    public void delGoodsSkuAttr(Integer id) throws BizException {
        goodsSkuAttrBiz.delGoodsSkuAttr(id);
    }

    @Override
    public void delGoodsSkuAttrByGoodsTypeId(Integer goodsTypeId) throws BizException {
        goodsSkuAttrBiz.delGoodsSkuAttrByGoodsTypeId(goodsTypeId);
    }

    @Override
    public PageInfo<GoodsSkuAttrEntity> getGoodsSkuAttr(PageParams<DTO<String, String>> pageParams) throws BizException {
        return goodsSkuAttrBiz.getGoodsSkuAttr(pageParams);
    }

    @Override
    public List<GoodsSkuAttrEntity> getGoodsSkuAttr(DTO<String, String> params) throws BizException {
        return goodsSkuAttrBiz.getGoodsSkuAttrList(params);
    }

    @Override
    public List<GoodsSkuSpectEntity> getGoodsSkuSpect(GoodsSkuSpectEntity goodsSkuSpectEntity) throws BizException {
        return goodsSkuAttrBiz.getGoodsSkuSpect(goodsSkuSpectEntity);
    }

    @Override
    public void updateSkuTypeNaeByGoodsTypeIdAndSkuSpecType(Integer goodsTypeId, String skuSpecType, String skuSpecTypeName, Integer userId) {
        goodsSkuAttrBiz.updateSkuTypeNaeByGoodsTypeIdAndSkuSpecType(goodsTypeId, skuSpecType, skuSpecTypeName, userId);
    }
}

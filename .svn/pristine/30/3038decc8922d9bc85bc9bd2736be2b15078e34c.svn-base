package cn.pahot.goods.biz;

import cn.pahot.goods.Exception.GoodsSkuAttrException;
import cn.pahot.goods.dao.GoodsSkuAttrMapper;
import cn.pahot.goods.dao.GoodsTypeMapper;
import cn.pahot.goods.entity.GoodsSkuAttrEntity;
import cn.pahot.goods.entity.GoodsSkuSpectEntity;
import cn.pahot.goods.entity.GoodsTypeEntity;
import cn.pahot.goods.enums.GoodsTypeStateEnum;
import com.boc.common.core.biz.AbstractPageBuilder;
import com.boc.common.core.biz.DefaultPageBuilder;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;
import com.boc.common.page.PageParams;
import com.boc.common.utils.DateUtils;
import com.boc.common.utils.string.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品分类业务逻辑实现
 * <p>@Title: GoodsTypeBiz.java
 * <p>@Package cn.pahot.goods.biz
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com
 * <p>@date 2017年12月13日 下午5:13:21
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@Service("goodsSkuAttrBiz")
public class GoodsSkuAttrBiz {
    @Autowired
    private GoodsSkuAttrMapper goodsSkuAttrDao;

    @Autowired
    private GoodsTypeMapper goodsTypeDao;

    public void addGoodsSkuAttr(GoodsSkuAttrEntity goodsSkuAttrEntity) throws BizException {
        this.validateGoodsSkuAttrEntity(goodsSkuAttrEntity, "addGoodsSkuAttr",
                "goodsTypeId", "skuSpecId", "skuSpecType", "skuSpecTypeName", "skuSpecName", "skuSpecVal", "sort", "aa01");

        GoodsTypeEntity goodsTypeEntity = goodsTypeDao.getGoodsTypeById(goodsSkuAttrEntity.getGoodsTypeId());

        if (!goodsTypeEntity.getState().equals(GoodsTypeStateEnum.GOODS_TYPE_STATE_DELETE.key)) {
            goodsSkuAttrEntity.setAa02(DateUtils.getCurrDateTimeToLong());
            this.goodsSkuAttrDao.addGoodsSkuAttr(goodsSkuAttrEntity);
        } else {
            throw GoodsSkuAttrException.PARAM_HINT_ERROR.newInstance("商品分类已被删除.");
        }

    }


    public void updateGoodsSkuAttr(GoodsSkuAttrEntity goodsSkuAttrEntity) throws BizException {
        this.validateGoodsSkuAttrEntity(goodsSkuAttrEntity, "updateGoodsSkuAttr",
                "id", "goodsTypeId", "skuSpecId", "skuSpecType", "skuSpecTypeName", "skuSpecName", "skuSpecVal", "sort", "ab01");

        GoodsTypeEntity goodsTypeEntity = goodsTypeDao.getGoodsTypeById(goodsSkuAttrEntity.getGoodsTypeId());
        if (goodsTypeEntity.getState().equals(GoodsTypeStateEnum.GOODS_TYPE_STATE_DELETE.key)) {
            throw GoodsSkuAttrException.PARAM_HINT_ERROR.newInstance("商品分类已被删除");
        }
        goodsSkuAttrEntity.setAb02(DateUtils.getCurrDateTimeToLong());
        this.goodsSkuAttrDao.updateGoodsSkuAttr(goodsSkuAttrEntity);
    }

    public void delGoodsSkuAttr(Integer id) throws BizException {
        this.goodsSkuAttrDao.delGoodsSkuAttr(id);
    }


    public PageInfo<GoodsSkuAttrEntity> getGoodsSkuAttr(PageParams<DTO<String, String>> pageParams) throws BizException {
        PageInfo<GoodsSkuAttrEntity> pi = DefaultPageBuilder.build(pageParams,
                new AbstractPageBuilder<GoodsSkuAttrEntity, DTO<String, String>>() {
                    @Override
                    public Page<GoodsSkuAttrEntity> build(DTO<String, String> param) throws BizException {
                        return goodsSkuAttrDao.getGoodsSkuAttr(param);
                    }
                });
        return pi;
    }

    public List<GoodsSkuAttrEntity> getGoodsSkuAttrList(DTO<String, String> params) throws BizException {
        return this.goodsSkuAttrDao.getGoodsSkuAttrList(params);
    }

    public List<GoodsSkuSpectEntity> getGoodsSkuSpect(GoodsSkuSpectEntity goodsSkuSpectEntity) throws BizException {
        List<GoodsSkuSpectEntity> list = this.goodsSkuAttrDao.getGoodsSkuSpect(goodsSkuSpectEntity);
        return list;
    }


    public void updateSkuTypeNaeByGoodsTypeIdAndSkuSpecType(Integer goodsTypeId, String skuSpecType, String skuSpecTypeName, Integer userId) {
        DTO<String, String> dto = new BaseDTO<>();
        dto.put("goodsTypeId", String.valueOf(goodsTypeId));
        dto.put("skuSpecType", skuSpecType);
        dto.put("skuSpecTypeName", skuSpecTypeName);
        dto.put("ab01", String.valueOf(userId));
        dto.put("ab02", String.valueOf(DateUtils.getCurrDateTimeToLong()));
        this.goodsSkuAttrDao.updateSkuTypeNaeByGoodsTypeIdAndSkuSpecType(dto);
    }


    /**
     * 校验GoodsSkuAttrEntity
     *
     * @param gsae
     * @param method
     * @param fields
     */
    private void validateGoodsSkuAttrEntity(GoodsSkuAttrEntity gsae, String method, String... fields) {
        for (String field : fields) {
            if ("id".equalsIgnoreCase(field) && gsae.getId() == null) {
                throw GoodsSkuAttrException.PARAM_IS_NULL.newInstance(method + ":id");
            } else if ("goodsTypeId".equalsIgnoreCase(field) && gsae.getGoodsTypeId() == null) {
                throw GoodsSkuAttrException.PARAM_IS_NULL.newInstance(method + ":goodsTypeId");
            } else if ("skuSpecId".equalsIgnoreCase(field) && gsae.getSkuSpecId() == null) {
                throw GoodsSkuAttrException.PARAM_IS_NULL.newInstance(method + ":skuSpecId");
            } else if ("skuSpecType".equalsIgnoreCase(field) && StringUtil.isEmpty(gsae.getSkuSpecType())) {
                throw GoodsSkuAttrException.PARAM_IS_NULL.newInstance(method + ":skuSpecType");
            } else if ("skuSpecTypeName".equalsIgnoreCase(field) && StringUtil.isEmpty(gsae.getSkuSpecTypeName())) {
                throw GoodsSkuAttrException.PARAM_IS_NULL.newInstance(method + ":skuSpecTypeName");
            } else if ("skuSpecName".equalsIgnoreCase(field) && StringUtil.isEmpty(gsae.getSkuSpecName())) {
                throw GoodsSkuAttrException.PARAM_IS_NULL.newInstance(method + ":skuSpecName");
            } else if ("skuSpecVal".equalsIgnoreCase(field) && StringUtil.isEmpty(gsae.getSkuSpecVal())) {
                throw GoodsSkuAttrException.PARAM_IS_NULL.newInstance(method + ":skuSpecVal");
            } else if ("sort".equalsIgnoreCase(field) && gsae.getSort() == null) {
                throw GoodsSkuAttrException.PARAM_IS_NULL.newInstance(method + ":sort");
            } else if ("aa01".equalsIgnoreCase(field) && gsae.getAa01() == null) {
                throw GoodsSkuAttrException.PARAM_IS_NULL.newInstance(method + ":aa01");
            } else if ("ab01".equalsIgnoreCase(field) && gsae.getAb01() == null) {
                throw GoodsSkuAttrException.PARAM_IS_NULL.newInstance(method + ":ab01");
            }

        }
    }

    @Transactional
    public void addOrUpdateGoodsSkuAttr(List<GoodsSkuAttrEntity> gsaeList) {
        String skuSpecTypeName = null;
        GoodsSkuAttrEntity gsae = null;
        DTO<String, String> dto = new BaseDTO<>();
        long time = DateUtils.getCurrDateTimeToLong();
        for (GoodsSkuAttrEntity goodsSkuAttrEntity : gsaeList) {
//            GoodsTypeEntity goodsTypeEntity = goodsTypeDao.getGoodsTypeById(goodsSkuAttrEntity.getGoodsTypeId());
//            if (goodsTypeEntity.getState().equals(GoodsTypeStateEnum.GOODS_TYPE_STATE_DELETE.key)) {
//                throw GoodsSkuAttrException.PARAM_HINT_ERROR.newInstance("当前商品属性已被删除,禁止操作");
//            }
            if (goodsSkuAttrEntity.getId() != null && goodsSkuAttrEntity.getId() != 0) {
                /*this.validateGoodsSkuAttrEntity(goodsSkuAttrEntity, "updateGoodsSkuAttr",
                        "id", "goodsTypeId", "skuSpecId", "skuSpecType", "skuSpecTypeName", "skuSpecName", "skuSpecVal", "sort", "ab01");
                goodsSkuAttrEntity.setAb02(time);
                goodsSkuAttrDao.updateGoodsSkuAttr(goodsSkuAttrEntity);*/
                this.updateGoodsSkuAttr(goodsSkuAttrEntity);
                skuSpecTypeName = goodsSkuAttrEntity.getSkuSpecTypeName();
                gsae = goodsSkuAttrEntity;
            } else {
              /*  this.validateGoodsSkuAttrEntity(goodsSkuAttrEntity, "addGoodsSkuAttr",
                        "goodsTypeId", "skuSpecId", "skuSpecType", "skuSpecTypeName", "skuSpecName", "skuSpecVal", "sort", "aa01");
                goodsSkuAttrEntity.setAa02(time);
                goodsSkuAttrDao.addGoodsSkuAttr(goodsSkuAttrEntity);*/
                this.addGoodsSkuAttr(goodsSkuAttrEntity);
                skuSpecTypeName = goodsSkuAttrEntity.getSkuSpecTypeName();
                gsae = goodsSkuAttrEntity;
            }
            if (skuSpecTypeName != null && gsae != null) {
                dto = new BaseDTO<>();
                dto.put("goodsTypeId", String.valueOf(gsae.getGoodsTypeId()));
                dto.put("skuSpecType", String.valueOf(gsae.getSkuSpecType()));
                List<String> list = this.goodsSkuAttrDao.getSkuSpecTypeName(dto);
                dto.put("skuSpecTypeName", skuSpecTypeName);
                dto.put("skuSpecTypeName2", skuSpecTypeName);
                dto.put("ab01", String.valueOf(gsae.getAa01() != null ? gsae.getAa01() : gsae.getAb01()));
                dto.put("ab02", String.valueOf(time));
                //存在多个名称
                if (list.size() > 1) {
                    this.goodsSkuAttrDao.updateSkuTypeNaeByGoodsTypeIdAndSkuSpecType(dto);
                } else if (list.size() == 1) {
                    //名称不同
                    if (!skuSpecTypeName.equals(list.get(0))) {
                        this.goodsSkuAttrDao.updateSkuTypeNaeByGoodsTypeIdAndSkuSpecType(dto);
                    }
                }

            }


        }

    }

    public void delGoodsSkuAttrByGoodsTypeId(Integer goodsTypeId) {

        if (goodsTypeId == null || goodsTypeId == 0)
            throw GoodsSkuAttrException.PARAM_IS_NULL.newInstance("delGoodsSkuAttrByGoodsTypeId" + ":goodsTypeId");
        this.goodsSkuAttrDao.delGoodsSkuAttrByGoodsTypeId(goodsTypeId);
    }
}

package cn.pahot.goods.biz;

import cn.pahot.goods.Exception.GoodsTypeException;
import cn.pahot.goods.dao.GoodsSkuAttrMapper;
import cn.pahot.goods.dao.GoodsTypeMapper;
import cn.pahot.goods.entity.GoodsTypeEntity;
import cn.pahot.goods.enums.GoodsTypeStateEnum;
import com.boc.common.core.biz.AbstractPageBuilder;
import com.boc.common.core.biz.DefaultPageBuilder;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;
import com.boc.common.page.PageParams;
import com.boc.common.utils.DateUtils;
import com.boc.common.utils.Hanyu4PinyinHelper;
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
@Service("goodsTypeBiz")
public class GoodsTypeBiz {
    @Autowired
    private GoodsTypeMapper goodsTypeDao;

    @Autowired
    private GoodsSkuAttrMapper goodsSkuAttrMapper;

    private GoodsTypeEntity findGoodsTypeById(int id) {
        return goodsTypeDao.getGoodsTypeById(id);
    }

    /**
     * 校验数据有效
     *
     * @param id
     * @param state
     * @param msg
     */
    private void validDataState(int id, GoodsTypeStateEnum state, String msg) {
        GoodsTypeEntity goodsTypeEntity = this.findGoodsTypeById(id);
        this.valiDataState(goodsTypeEntity, state, msg);

    }

    /**
     * 校验数据有效
     *
     * @param goodsTypeEntity
     * @param state
     * @param msg
     */
    private void valiDataState(GoodsTypeEntity goodsTypeEntity, GoodsTypeStateEnum state, String msg) {
        if (goodsTypeEntity == null)
            throw GoodsTypeException.PARAM_HINT_ERROR.newInstance("根据id未找到对应的商品类型,请确认!");
        GoodsTypeStateEnum goodsTypeStateEnum = GoodsTypeStateEnum.toEnum(goodsTypeEntity.getState());
        if (goodsTypeStateEnum != state)
            throw GoodsTypeException.PARAM_HINT_ERROR.newInstance(msg);
    }

    /**
     * 校验数据无效
     *
     * @param id
     * @param state
     * @param msg
     */
    private void invalidDataState(int id, GoodsTypeStateEnum state, String msg) {
        GoodsTypeEntity goodsTypeEntity = this.findGoodsTypeById(id);
        this.invalidDataState(goodsTypeEntity, state, msg);

    }
    //private void validated()


    /**
     * 验证数据无效
     *
     * @param goodsTypeEntity
     * @param state
     * @param msg
     */
    private void invalidDataState(GoodsTypeEntity goodsTypeEntity, GoodsTypeStateEnum state, String msg) {
        if (goodsTypeEntity == null)
            throw GoodsTypeException.PARAM_HINT_ERROR.newInstance("根据id未找到对应的商品类型,请确认!");
        GoodsTypeStateEnum goodsTypeStateEnum = GoodsTypeStateEnum.toEnum(goodsTypeEntity.getState());
        if (goodsTypeStateEnum == state)
            throw GoodsTypeException.PARAM_HINT_ERROR.newInstance(msg);
    }

    public void addGoodsType(GoodsTypeEntity goodsTypeEntity) throws BizException {
        this.validateGoodsTypeEntity(goodsTypeEntity, "addGoodsType", "name", "parentId", "sort", "aa01");

        goodsTypeEntity.setEname(Hanyu4PinyinHelper.toHanyuPinyin(goodsTypeEntity.getName()));
        setGoodsTypePath(goodsTypeEntity);
        goodsTypeEntity.setAa02(DateUtils.getCurrDateTimeToLong());
        goodsTypeDao.addGoodsType(goodsTypeEntity);
    }


    /*校验上级数据状态*/
    private void setGoodsTypePath(GoodsTypeEntity goodsTypeEntity) {
        Integer parentId = goodsTypeEntity.getParentId();
        if (parentId != null && parentId != 0) {
            GoodsTypeEntity parentGoodsType = this.goodsTypeDao.getGoodsTypeById(parentId);
            this.invalidDataState(parentGoodsType, GoodsTypeStateEnum.GOODS_TYPE_STATE_DELETE, "上级商品分类已经被删除!");
            this.invalidDataState(parentGoodsType, GoodsTypeStateEnum.GOODS_TYPE_STATE_FORBIDDE, "上级商品分类已经禁用!");
            goodsTypeEntity.setPath(parentGoodsType.getPath() + "_" + parentGoodsType.getId());
        } else {
            goodsTypeEntity.setPath("0");
        }
    }


    public void updateGoodsType(GoodsTypeEntity goodsTypeEntity) throws BizException {
        this.validateGoodsTypeEntity(goodsTypeEntity, "updateGoodsType", "id", "ab01");
        GoodsTypeEntity pGoodsType = this.goodsTypeDao.getGoodsTypeById(goodsTypeEntity.getId());
        this.invalidDataState(pGoodsType, GoodsTypeStateEnum.GOODS_TYPE_STATE_DELETE, "当前商品类型已经删除,不能操作!");

        if (!StringUtil.isEmpty(goodsTypeEntity.getName())) {
            goodsTypeEntity.setEname(Hanyu4PinyinHelper.toHanyuPinyin(goodsTypeEntity.getName()));
        }
        setGoodsTypePath(goodsTypeEntity);
        goodsTypeEntity.setAb02(DateUtils.getCurrDateTimeToLong());
        goodsTypeDao.updateGoodsType(goodsTypeEntity);
    }

    @Transactional
    public void updateGoodsTypeState(int id, String state, int userId) throws BizException {
        if (id == 0 || userId == 0 || StringUtil.isEmpty(state))
            throw GoodsTypeException.PARAM_IS_NULL.newInstance("updateGoodsTypeState:id|userId|state");
        GoodsTypeStateEnum goodsTypeStateEnum = GoodsTypeStateEnum.toEnum(state);
        if (goodsTypeStateEnum == null)
            throw GoodsTypeException.PARAM_HINT_ERROR.newInstance("updateGoodsTypeState:state状态有误:" + state);
        DTO<String, String> dto = new BaseDTO<>();
        //校验当前商品
        GoodsTypeEntity goodsType = this.goodsTypeDao.getGoodsTypeById(id);
        this.invalidDataState(goodsType, GoodsTypeStateEnum.GOODS_TYPE_STATE_DELETE, "当前商品类型已经删除,不能操作");

        //启用商品类型 ,需要检测它的父类型是否为启用状态
        if (goodsTypeStateEnum == GoodsTypeStateEnum.GOODS_TYPE_STATE_NORMAL) {
            //顶级商品类型不做父级类型校验
            if (goodsType.getParentId() != 0) {
                GoodsTypeEntity parent = this.goodsTypeDao.getGoodsTypeById(goodsType.getParentId());
                this.invalidDataState(parent, GoodsTypeStateEnum.GOODS_TYPE_STATE_DELETE, "当前商品类型的上级类型处于删除状态,不能启用当前商品类型");
                this.invalidDataState(parent, GoodsTypeStateEnum.GOODS_TYPE_STATE_FORBIDDE, "当前商品类型的上级类型处于禁用状态,请先启用上级商品类型");
            }
        } else if (goodsTypeStateEnum == GoodsTypeStateEnum.GOODS_TYPE_STATE_FORBIDDE) //禁商品类型 需要检测其子类型已经全部禁用或者删除
        {
            //查询子类型中是否有处于正常状态的类型
            GoodsTypeEntity gte = new GoodsTypeEntity();
            gte.setParentId(id);
            gte.setState(GoodsTypeStateEnum.GOODS_TYPE_STATE_NORMAL.key);
            List<GoodsTypeEntity> list = this.goodsTypeDao.getGoodsTypeList(gte);
            if (list.size() > 0)
                throw GoodsTypeException.PARAM_HINT_ERROR.newInstance("当前商品类型的子类型尚未禁用,请先禁用或者删除全部子类型");
        } else {//删除数据
            GoodsTypeEntity goodsTypeEntity = new GoodsTypeEntity();
            goodsTypeEntity.setParentId(id);
            List<GoodsTypeEntity> subList = this.goodsTypeDao.getGoodsTypeList(goodsTypeEntity);
            if (subList.size() > 0)
                throw GoodsTypeException.PARAM_HINT_ERROR.newInstance("当前商品类型的子类型尚未删除,请先删除全部子类型");
        }
        // this.goodsTypeDao.getGoodsTypeById(id);
        dto.put("id", String.valueOf(id));
        dto.put("state", state);
        dto.put("ab01", String.valueOf(userId));
        dto.put("ab02", DateUtils.getCurrDateTime());
        goodsTypeDao.updateGoodsTypeState(dto);

        if (state.equals(GoodsTypeStateEnum.GOODS_TYPE_STATE_DELETE.key)) {
            goodsSkuAttrMapper.delGoodsSkuAttrByGoodsTypeId(id);
        }
    }

    public PageInfo<GoodsTypeEntity> getGoodsTypeForPage(PageParams<DTO<String, String>> params) throws BizException {
        PageInfo<GoodsTypeEntity> pi = DefaultPageBuilder.build(params,
                new AbstractPageBuilder<GoodsTypeEntity, DTO<String, String>>() {
                    @Override
                    public Page<GoodsTypeEntity> build(DTO<String, String> param) throws BizException {
                        return goodsTypeDao.getGoodsTypeForPage(param);
                    }
                });
        return pi;
    }

    public List<GoodsTypeEntity> getGoodsTypeList(GoodsTypeEntity goodsTypeEntity) throws BizException {
        return this.goodsTypeDao.getGoodsTypeList(goodsTypeEntity);
    }


    public List<GoodsTypeEntity> getGoodsTypeList2(DTO<String, String> params) throws BizException {
        return this.goodsTypeDao.getGoodsTypeList2(params);
    }


    private void validateGoodsTypeEntity(GoodsTypeEntity gte, String method, String... fields) {

        for (String field : fields) {
            if ("id".equalsIgnoreCase(field) && gte.getId() == null) {
                throw GoodsTypeException.PARAM_IS_NULL.newInstance(method + ":id");
            } else if ("name".equalsIgnoreCase(field) && StringUtil.isEmpty(gte.getName())) {
                throw GoodsTypeException.PARAM_IS_NULL.newInstance(method + ":name");
            } else if ("ename".equalsIgnoreCase(field) && StringUtil.isEmpty(gte.getEname())) {
                throw GoodsTypeException.PARAM_IS_NULL.newInstance(method + ":ename");
            } else if ("parentId".equalsIgnoreCase(field) && gte.getParentId() == null) {
                throw GoodsTypeException.PARAM_IS_NULL.newInstance(method + ":parentId");
            } else if ("icon".equalsIgnoreCase(field) && StringUtil.isEmpty(gte.getIcon())) {
                throw GoodsTypeException.PARAM_IS_NULL.newInstance(method + ":icon");
            } else if ("sort".equalsIgnoreCase(field) && gte.getSort() == null) {
                throw GoodsTypeException.PARAM_IS_NULL.newInstance(method + ":sort");
            } else if ("path".equalsIgnoreCase(field) && StringUtil.isEmpty(gte.getPath())) {
                throw GoodsTypeException.PARAM_IS_NULL.newInstance(method + ":path");
            } else if ("state".equalsIgnoreCase(field) && StringUtil.isEmpty(gte.getState())) {
                throw GoodsTypeException.PARAM_IS_NULL.newInstance(method + ":state");
            } else if ("aa01".equalsIgnoreCase(field) && gte.getAa01() == null) {
                throw GoodsTypeException.PARAM_IS_NULL.newInstance(method + ":aa01");
            } else if ("ab01".equalsIgnoreCase(field) && gte.getAb01() == null) {
                throw GoodsTypeException.PARAM_IS_NULL.newInstance(method + ":ab01");
            }

        }
    }
}

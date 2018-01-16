package cn.pahot.goods.controller;

import cn.pahot.goods.access.AccessCode;
import cn.pahot.goods.access.AccessUrl;
import cn.pahot.goods.entity.GoodsTypeEntity;
import cn.pahot.goods.enums.GoodsTypeStateEnum;
import cn.pahot.goods.facade.GoodsTypeFacade;
import com.boc.annotation.AccessControl;
import com.boc.common.metatype.DTO;
import com.boc.common.utils.string.StringUtil;
import com.boc.common.web.WebResponse;
import com.boc.common.web.springmvc.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * creat by @author:chen in 2017\12\18 0018
 *
 * @description:商品分类控制器
 **/

@Controller
@RequestMapping("/goodType")
public class GoodTypeController extends BaseController {


    @Autowired
    private GoodsTypeFacade goodsTypeFacade;

    @ResponseBody
    @RequestMapping(value = "/addItem.sec", method = RequestMethod.POST)
    @AccessControl(url = AccessUrl.DETAIL_PAGE, code = AccessCode.ADD)
    public WebResponse addGoodType() {
        WebResponse webResponse = new WebResponse();

        DTO dto = getParamAsDTO();

        String name = dto.getAsString("name");


        if (StringUtil.isEmpty(name)) {
            webResponse.setAjaxMsg(false, "商品分类名称为空", "", "");
            return webResponse;
        }

        Integer parentId = dto.getAsInteger("parentId");
        Integer sort = dto.getAsInteger("sort");
        String icon = dto.getAsString("icon");

        GoodsTypeEntity goodsTypeEntity = new GoodsTypeEntity();
        goodsTypeEntity.setName(name);
        goodsTypeEntity.setParentId(parentId == null ? 0 : parentId);
        goodsTypeEntity.setSort(sort);
        goodsTypeEntity.setIcon(icon);


        goodsTypeEntity.setAa01(getUserId());
        goodsTypeEntity.setAa02(getCurrentTime());
        goodsTypeFacade.addGoodsType(goodsTypeEntity);
        webResponse.setAjaxMsg(true, "添加商品分类成功", "", "");
        return webResponse;

    }


    @ResponseBody
    @RequestMapping(value = "/changeState.sec", method = RequestMethod.POST)
    public WebResponse updateGoodsTypeState() {
        WebResponse webResponse = new WebResponse();


        DTO dto = getParamAsDTO();

        Integer id = dto.getAsInteger("id");
        String state = dto.getAsString("state");


        if (id == null) {
            webResponse.setAjaxMsg(false, "请求参数id为空", "", "");
            return webResponse;
        } else if (StringUtil.isEmpty(state)) {
            webResponse.setAjaxMsg(false, "请求参数state为空", "", "");
            return webResponse;
        }

        if (StringUtil.equals(state, GoodsTypeStateEnum.GOODS_TYPE_STATE_DELETE.key)) {
            checkArights(AccessUrl.DETAIL_PAGE, AccessCode.DELETE);
        } else if (StringUtil.equals(state, GoodsTypeStateEnum.GOODS_TYPE_STATE_NORMAL.key)) {
            checkArights(AccessUrl.DETAIL_PAGE, AccessCode.ENABLED);
        } else if (StringUtil.equals(state, GoodsTypeStateEnum.GOODS_TYPE_STATE_FORBIDDE.key)) {
            checkArights(AccessUrl.DETAIL_PAGE, AccessCode.DISABLED);
        }

        goodsTypeFacade.updateGoodsTypeState(id, state, getUserId());
        webResponse.setAjaxMsg(true, "修改商品分类状态成功", "", "");
        return webResponse;
    }


    @ResponseBody
    @RequestMapping(value = "/update.sec", method = RequestMethod.POST)
    @AccessControl(url = AccessUrl.DETAIL_PAGE, code = AccessCode.EDIT)
    public WebResponse updateGoodsType() {
        WebResponse webResponse = new WebResponse();


        DTO dto = getParamAsDTO();
        Integer id = dto.getAsInteger("id");

        if (id == null) {
            webResponse.setAjaxMsg(false, "请求参数id为空", "", "");
            return webResponse;
        }

        GoodsTypeEntity goodsTypeEntity = new GoodsTypeEntity();
        goodsTypeEntity.setId(id);


        goodsTypeEntity.setName(dto.getAsString("name"));
        goodsTypeEntity.setIcon(dto.getAsString("icon"));
        goodsTypeEntity.setSort(dto.getAsInteger("sort"));
        goodsTypeEntity.setParentId(dto.getAsInteger("parentId"));


        if (goodsTypeEntity == null) {
            webResponse.setAjaxMsg(false, "请求参数为空", "", "");
            return webResponse;
        }


        goodsTypeEntity.setAb01(getUserId());
        goodsTypeFacade.updateGoodsType(goodsTypeEntity);
        webResponse.setAjaxMsg(true, "成功更新商品分类信息", "", "");
        return webResponse;
    }


    @ResponseBody
    @RequestMapping(value = "/getList.sec")
    public WebResponse getGoodsTypeList() {
        WebResponse webResponse = new WebResponse();
        DTO params = getParamAsDTO();
        String goodsTypeEntity = params.getAsString("getListType");
        if (StringUtil.isEmpty(goodsTypeEntity)) {
            webResponse.setAjaxMsg(false, "请求参数为空", "", "");
            return webResponse;
        }

        GoodsTypeEntity goodsType = new GoodsTypeEntity();

        //0:不分页获取列表 1:分页获取列表
        if (goodsTypeEntity.equals("0")) {

            webResponse.setAjaxMsg(true, "", "", goodsTypeFacade.getGoodsTypeList(goodsType));
        } else {
//            PageParams<DTO<String, String>> params = new PageParams<>();
//            params.setPageNo(goodsTypeEntity.getPageNum());
//            params.setPageSize(goodsTypeEntity.getPageSize());
//
//            webResponse.setAjaxMsg(false, "请求参数为空", "", goodsTypeFacade.getGoodsTypeForPage(params));
        }

        return webResponse;
    }


}

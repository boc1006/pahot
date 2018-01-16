package cn.pahot.goods.controller;

import cn.pahot.goods.access.AccessCode;
import cn.pahot.goods.access.AccessUrl;
import cn.pahot.goods.entity.GoodsSkuAttrEntity;
import cn.pahot.goods.entity.GoodsSkuSpectEntity;
import cn.pahot.goods.facade.GoodsTypeFacade;
import com.boc.annotation.AccessControl;
import com.boc.common.metatype.DTO;
import com.boc.common.web.WebResponse;
import com.boc.common.web.permissions.SecurityUtils;
import com.boc.common.web.springmvc.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/goodsSkuAttr")
public class GoodsSkuAttrController extends BaseController {
    @Autowired
    private GoodsTypeFacade goodsTypeFacade;

    @RequestMapping("/index.sec")
    public ModelAndView page() {
        ModelAndView mv = getModelAndView(AccessUrl.DETAIL_PAGE);
        mv.setViewName("goodsSkuAttr/index");
        return mv;
    }

    /**
     * 参数 type: 商品规格分类  允许null
     * typename :商品规格分类名称 允许null
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getGoodsSkuSpect.sec")
    public WebResponse getGoodsSkuSpect() {
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();
        GoodsSkuSpectEntity goodsSkuSpectEntity = new GoodsSkuSpectEntity();
        String type = params.getAsString("type");
        String typename = params.getAsString("typename");
        if (!StringUtils.isEmpty(type))
            goodsSkuSpectEntity.setType(type);
        if (!StringUtils.isEmpty(typename))
            goodsSkuSpectEntity.setTypename(typename);
        List<GoodsSkuSpectEntity> gsseList = goodsTypeFacade.getGoodsSkuSpect(goodsSkuSpectEntity);
        wb.setAjaxMsg(true, "查询成功", "", gsseList);
        return wb;
    }

    //

    /**
     * 批量更新商品类型名称
     * goodsTypeId 商品分类id
     * skuSpecType  商品sku属性分裂
     * skuSpecTypeName 商品sku属性分类名称
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateSkuTypeNaeByGoodsTypeIdAndSkuSpecType.sec", method = RequestMethod.POST)
    @AccessControl(url = AccessUrl.DETAIL_PAGE, code = AccessCode.EDIT)
    public WebResponse updateSkuTypeNaeByGoodsTypeIdAndSkuSpecType() {
        WebResponse wb = new WebResponse();
        DTO<String, String> dto = this.getParamAsDTO();
        int goodsTypeId = dto.getAsInteger("goodsTypeId");
        String skuSpecType = dto.getAsString("skuSpecType");
        String skuSpecTypeName = dto.getAsString("skuSpecTypeName");
        goodsTypeFacade.updateSkuTypeNaeByGoodsTypeIdAndSkuSpecType(goodsTypeId, skuSpecType, skuSpecTypeName, Integer.valueOf(SecurityUtils.getSubject().getUserid()));
        wb.setAjaxMsg(true, "操作成功", "", "");
        return wb;
    }

    /**
     * 添加或者更新 商品分类
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addOrUpdateGoodsSkuAttr.sec", method = RequestMethod.POST)
    @AccessControl(url = AccessUrl.DETAIL_PAGE, code = AccessCode.ADD)
    public WebResponse addOrUpdateGoodsSkuAttr(@RequestBody List<GoodsSkuAttrEntity> list) {
        WebResponse wb = new WebResponse();
        list.forEach(gse -> {
            gse.setAa01(Integer.parseInt(SecurityUtils.getSubject().getUserid()));
            gse.setAb01(Integer.parseInt(SecurityUtils.getSubject().getUserid()));
        });
        goodsTypeFacade.addOrUpdateGoodsSkuAttr(list);
        wb.setAjaxMsg(true, "操作成功", "", "");
        return wb;
    }


    /**
     * 查询商品属性列表
     * 参数
     * goodsTypeId  商品分类id
     * skuSpecId  商品sku规格Id
     * skuSpecType 分类类型
     * skuSpecTypeName 分类名称
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getGoodsSkuAttrList.sec")
    public WebResponse getGoodsSkuAttrList() {

        WebResponse wb = new WebResponse();
        DTO<String, String> dto = getParamAsDTO();
        List list = goodsTypeFacade.getGoodsSkuAttr(dto);
        wb.setDataGridMsg(true, "查询成功", list, (long) list.size());
        wb.put("userid", getUserId());
        return wb;
    }


    /**
     * 删除商品属性
     * id  待删除的 商品分类id 必传
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/delGoodsSkuAttr.sec")
    @AccessControl(url = AccessUrl.DETAIL_PAGE, code = AccessCode.DELETE)
    public WebResponse delGoodsSkuAttr() {
        WebResponse wb = new WebResponse();
        DTO<String, String> dto = getParamAsDTO();
        Integer id = dto.getAsInteger("id");
        goodsTypeFacade.delGoodsSkuAttr(id);
        wb.setAjaxMsg(true, "删除成功", "", "");
        return wb;
    }

}

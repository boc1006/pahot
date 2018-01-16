package cn.pahot.business.controller;

import cn.pahot.business.access.AccessCode;
import cn.pahot.business.access.AccessUrl;
import cn.pahot.business.entity.BusinessApplayLogEntity;
import cn.pahot.business.entity.BusinessInfoEntity;
import cn.pahot.business.entity.ShopInfoEntity;
import cn.pahot.business.facade.MerchantFacade;
import com.boc.annotation.AccessControl;
import com.boc.common.metatype.DTO;
import com.boc.common.page.PageParams;
import com.boc.common.web.WebResponse;
import com.boc.common.web.springmvc.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/verify")
public class VerifyBusinessController extends BaseController {

    @Resource
    private MerchantFacade merchantFacade;

    @RequestMapping("/page.sec")
    @AccessControl(url = AccessUrl.VERIFY_PAGE)
    public ModelAndView page(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("verify/page");
        return mv;
    }

    @RequestMapping("/getUserList.sec")
    @ResponseBody
    public WebResponse getUserList(){
        DTO params = getParamAsDTO();
        WebResponse wb = new WebResponse();
        PageParams<DTO<String, String>> page = new PageParams<>();
        page.setPageSize(params.getAsInteger("pageSize"));
        page.setPageNo(params.getAsInteger("psgeNo"));
        PageInfo<BusinessInfoEntity> rst = merchantFacade.getBusinessList(page);
        wb.setDataGridMsg(true, "", rst.getList(), rst.getTotal());
        return wb;
    }

    @ResponseBody
    @RequestMapping("/state.sec")
    @AccessControl(url = AccessUrl.VERIFY_PAGE,code = AccessCode.VERIFY)
    public WebResponse state(){
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();
        BusinessApplayLogEntity apply = new BusinessApplayLogEntity();
        merchantFacade.addBusinessSubLogInfo(apply);
        wb.setAjaxMsg(true,"","","");
        return wb;
    }

}

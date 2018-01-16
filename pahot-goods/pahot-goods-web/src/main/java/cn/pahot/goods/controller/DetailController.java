package cn.pahot.goods.controller;

import cn.pahot.goods.access.AccessUrl;
import com.boc.annotation.AccessControl;
import com.boc.common.web.springmvc.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/detail")
public class DetailController extends BaseController {

    @RequestMapping("/page.sec")
    @AccessControl(url = AccessUrl.DETAIL_PAGE)
    public ModelAndView page(){
        ModelAndView mv = getModelAndView(AccessUrl.DETAIL_PAGE);
        mv.setViewName("detail/page");
        return mv;
    }
}

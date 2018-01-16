package cn.pahot.upms.controller;

import cn.pahot.upms.access.AccessCode;
import cn.pahot.upms.access.AccessUrl;
import cn.pahot.xa.entity.MessageEntity;
import cn.pahot.xa.facade.CapXaFacade;
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
@RequestMapping("/message")
public class MessageController extends BaseController {

    @Resource
    private CapXaFacade capXaFacade;

    @RequestMapping("/list.sec")
    @AccessControl(url=AccessUrl.MESSAGE_LIST)
    public ModelAndView index() {
        ModelAndView mv = getModelAndView(AccessUrl.MESSAGE_LIST);
        mv.setViewName("message/list");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/messagelist.sec")
    public WebResponse messagelist(){
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();
        PageParams page = new PageParams<DTO>();
        page.setParams(params);
        page.setPageNo(params.getAsInteger("page"));
        page.setPageSize(params.getAsInteger("pagesize"));
        PageInfo<MessageEntity> rst = capXaFacade.listPage(page);
        wb.setDataGridMsg(true,"",rst.getList(),rst.getTotal());
        return wb;
    }


    @ResponseBody
    @RequestMapping("/resend.sec")
    @AccessControl(url=AccessUrl.MESSAGE_LIST,code = AccessCode.RESEND)
    public WebResponse reseng(){
        WebResponse wb = new WebResponse();
        capXaFacade.reSendMessageByMessageId(getParamAsDTO().getAsString("msgid"));
        wb.setAjaxMsg(true,"","",null);
        return wb;
    }

    @ResponseBody
    @RequestMapping("/resendall.sec")
    @AccessControl(url=AccessUrl.MESSAGE_LIST,code = AccessCode.RESEND)
    public WebResponse resendall(){
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();
        capXaFacade.reSendAllDeadMessageByQueueName(params.getAsString("queuename"),Integer.parseInt(params.getAsString("size")));
        wb.setAjaxMsg(true,"","",null);
        return wb;
    }

}

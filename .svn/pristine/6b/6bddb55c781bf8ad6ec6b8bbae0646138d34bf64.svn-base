package cn.pahot.upms.controller;

import cn.pahot.upms.access.AccessCode;
import cn.pahot.upms.access.AccessUrl;
import cn.pahot.upms.entity.SystemManagerEntity;
import cn.pahot.upms.enums.UPMSMenuEnum;
import cn.pahot.upms.facade.SystemFacade;
import com.boc.annotation.AccessControl;
import com.boc.common.metatype.DTO;
import com.boc.common.utils.string.StringUtil;
import com.boc.common.web.WebResponse;
import com.boc.common.web.permissions.SecurityUtils;
import com.boc.common.web.springmvc.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


/**
 * 系统管理
 */
@Controller
@RequestMapping("/sys")
public class SysController extends BaseController {

    @Resource
    private SystemFacade systemFacade;

    @RequestMapping("/sysconfig.sec")
    @AccessControl(url = AccessUrl.SYS_SYSCONFIG)
    public ModelAndView indea() {
        ModelAndView mv = getModelAndView(AccessUrl.SYS_SYSCONFIG);
        mv.setViewName("sys/sysconfig");
        return mv;
    }

    /**
     * 获取系统列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getSysList.sec")
    public WebResponse getSystemList() {
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();
        String state = params.getAsString("state");
        if("".equals(state)){
            state = null;
        }
        List<SystemManagerEntity> sysList = systemFacade.getSysList(state);
        wb.setDataGridMsg(true, "", sysList, null);
        return wb;
    }

    /**
     * 禁用一个系统
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/disabled.sec")
    @AccessControl(url=AccessUrl.SYS_SYSCONFIG,code = AccessCode.DISABLED)
    public WebResponse disab() {
        WebResponse wb = new WebResponse();
        SystemManagerEntity sm = new SystemManagerEntity();
        sm.setId(getParamAsDTO().getAsString("id"));
        sm.setState("00");
        sm.setAb01(getUserId());
        sm.setAb02(getCurrentTime());
        systemFacade.enableOrdisableSys(sm);
        wb.setAjaxMsg(true, "禁用成功", "", "");
        return wb;
    }


    /**
     * 启用一个系统
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/abled.sec")
    @AccessControl(url = AccessUrl.SYS_SYSCONFIG,code = AccessCode.ENABLED)
    public WebResponse abled() {
        WebResponse wb = new WebResponse();
        SystemManagerEntity sm = new SystemManagerEntity();
        sm.setId(getParamAsDTO().getAsString("id"));
        sm.setState("01");
        sm.setAb01(getUserId());
        sm.setAb02(getCurrentTime());
        systemFacade.enableOrdisableSys(sm);
        wb.setAjaxMsg(true, "启用成功", "", "");
        return wb;
    }

    /**
     * 修改和新增一个系统
     * state 2 修改 1 新增
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/addoredit.sec")
    public WebResponse addoredit() {
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();
        String state = params.getAsString("state");
        if (StringUtil.isEmpty(state)) {
            wb.setAjaxMsg(false, "缺失参数", "", "");
            return wb;
        }

        SystemManagerEntity sm = new SystemManagerEntity();

        sm.setType(params.getAsString("type"));
        sm.setName(params.getAsString("name"));
        sm.setUrl(params.getAsString("url"));
        sm.setRemark(params.getAsString("remark"));
        sm.setSort(params.getAsInteger("sort"));
        sm.setId(params.getAsString("sysid"));
        sm.setAa01(getUserId());
        sm.setAa02(getCurrentTime());
        if ("2".equals(state)) {
            checkArights(AccessUrl.SYS_SYSCONFIG,AccessCode.EDIT);
            sm.setAb01(getUserId());
            sm.setAb02(getCurrentTime());
            systemFacade.editSys(sm);
        } else if ("1".equals(state)) {
            checkArights(AccessUrl.SYS_SYSCONFIG,AccessCode.ADD);
            systemFacade.addItemToSys(sm);
        } else {
            wb.setAjaxMsg(false, "失败【state】非法", "", "");
            return wb;
        }
        wb.setAjaxMsg(true, "成功", "", "");
        return wb;
    }
}

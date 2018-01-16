package cn.pahot.upms.controller;

import cn.pahot.upms.access.AccessCode;
import cn.pahot.upms.access.AccessUrl;
import cn.pahot.upms.entity.SystemSettingEntity;
import cn.pahot.upms.facade.SystemSettingFacde;
import com.boc.annotation.AccessControl;
import com.boc.common.metatype.DTO;
import com.boc.common.page.PageParams;
import com.boc.common.web.WebResponse;
import com.boc.common.web.springmvc.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


/**
 * 系统变量管理
 */
@Controller
@RequestMapping("/sysvar")
public class SysVarController extends BaseController {

    //表单字段
    private static final String ID = "id";
    private static final String SID = "sid";
    private static final String SETKEY = "setKey";
    private static final String SETVALUE = "setValue";
    private static final String SETDESC = "setDesc";
    private static final String ENABLE = "enable";
    private static final String EDITMODE = "editMode";
    private static final String AA01 = "aa01";
    private static final String AA02 = "aa02";
    private static final String AB01 = "ab01";
    private static final String AB02 = "ab02";

    //状态码
    private static final String DISABLE_CODE = "00";
    private static final String ENABLE_CODE = "01";

    @Resource
    private SystemSettingFacde systemSettingFacde;

    @RequestMapping("/index.sec")
    @AccessControl(url = AccessUrl.SYSVAR_INDEX)
    public ModelAndView index() {
        ModelAndView mv = getModelAndView(AccessUrl.SYSVAR_INDEX);
        mv.setViewName("sysvar/index");
        return mv;
    }

    /**
     * 获取所有系统变量列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getSettingList.sec")
    public WebResponse getSystemSettingList() {
        WebResponse wb = new WebResponse();


        DTO params = getParamAsDTO();
        PageParams<DTO<String, String>> pageParams = new PageParams<>();
        pageParams.setPageNo(params.getAsInteger("page"));
        pageParams.setPageSize(params.getAsInteger("pagesize"));
        pageParams.setParams(params);

        PageInfo<SystemSettingEntity> result = systemSettingFacde.querySettingList(pageParams);
        wb.setDataGridMsg(true, "", result.getList(), result.getTotal());
        return wb;
    }

    /**
     * 新增系统变量
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/addSetting.sec")
    @AccessControl(url = AccessUrl.SYSVAR_INDEX, code = AccessCode.ADD)
    public WebResponse add() {
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();

        SystemSettingEntity setting = new SystemSettingEntity();
        setting.setSid(params.getAsString(SID));
        setting.setSetKey(params.getAsString(SETKEY));
        setting.setSetValue(params.getAsString(SETVALUE));
        setting.setSetDesc(params.getAsString(SETDESC));
        setting.setAa01(this.getUserId());
        systemSettingFacde.saveSystemSetting(setting);

        wb.setAjaxMsg(true, "成功", "", "");
        return wb;
    }

    /**
     * 修改系统变量
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateSetting.sec")
    @AccessControl(url = AccessUrl.SYSVAR_INDEX, code = AccessCode.EDIT)
    public WebResponse update() {
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();

        SystemSettingEntity setting = systemSettingFacde.queryById(params.getAsInteger(ID));
        setting.setId(params.getAsInteger(ID));
        setting.setSid(params.getAsString(SID));
        setting.setSetKey(params.getAsString(SETKEY));
        setting.setSetValue(params.getAsString(SETVALUE));
        setting.setSetDesc(params.getAsString(SETDESC));
        setting.setEditMode(params.getAsString(EDITMODE));
        setting.setAb01(this.getUserId());
        systemSettingFacde.updateSystemSetting(setting);

        wb.setAjaxMsg(true, "成功", "", "");
        return wb;
    }

    /**
     * 启用系统变量
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/enable.sec")
    @AccessControl(url = AccessUrl.SYSVAR_INDEX, code = AccessCode.ENABLED)
    public WebResponse enable() {
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();

        SystemSettingEntity setting = systemSettingFacde.queryById(params.getAsInteger(ID));
        setting.setEnable(ENABLE_CODE);
        setting.setAb01(this.getUserId());
        systemSettingFacde.updateSystemSetting(setting);

        wb.setAjaxMsg(true, "启用成功", "", "");
        return wb;
    }

    /**
     * 禁用系统变量
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/disable.sec")
    @AccessControl(url = AccessUrl.SYSVAR_INDEX, code = AccessCode.DISABLED)
    public WebResponse disable() {
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();

        SystemSettingEntity setting = systemSettingFacde.queryById(params.getAsInteger(ID));
        setting.setEnable(DISABLE_CODE);
        setting.setAb01(this.getUserId());
        systemSettingFacde.updateSystemSetting(setting);

        wb.setAjaxMsg(true, "禁用成功", "", "");
        return wb;
    }

    /**
     * 将系统变量数据load到redis
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loadToRedis.sec", method = RequestMethod.POST)
    public WebResponse loadSystemSettingToRedis() {

        WebResponse wb = new WebResponse();
        systemSettingFacde.loadSystemSettingToRedis();
        wb.setAjaxMsg(true, "缓存成功", "", "");
        return wb;
    }

}

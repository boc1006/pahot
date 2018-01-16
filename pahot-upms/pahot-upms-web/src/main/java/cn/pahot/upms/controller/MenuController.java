package cn.pahot.upms.controller;

import cn.pahot.upms.access.AccessCode;
import cn.pahot.upms.access.AccessUrl;
import cn.pahot.upms.entity.MenuEntity;
import cn.pahot.upms.entity.MenuRightsEntity;
import cn.pahot.upms.facade.MenuFacade;
import com.boc.annotation.AccessControl;
import com.boc.common.metatype.DTO;
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
 * 菜管管理
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Resource
    private MenuFacade menuFacade;

    @RequestMapping("menuconfig.sec")
    @AccessControl(url=AccessUrl.MENU_MENUCONFIG)
    public ModelAndView page(){
        ModelAndView mv = getModelAndView(AccessUrl.MENU_MENUCONFIG);
        mv.setViewName("menu/page");
        return mv;
    }


    /**
     * 获取菜单列表数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMenuList.sec")
    public WebResponse getMenuList(){
        WebResponse wb = new WebResponse();
        List<MenuEntity> rst = menuFacade.queryMenuBySid(getParamAsDTO().getAsString("sysid"));
        wb.setAjaxMsg(true, "查询成功", null, rst);
        return wb;
    }

    /**
     * 获取菜单下的权限列表
     */
    @ResponseBody
    @RequestMapping("/getRightList.sec")
    public WebResponse getRightList(){
        WebResponse wb = new WebResponse();
        List<MenuRightsEntity> rst = menuFacade.queryMenuRightsByMid(getParamAsDTO().getAsInteger("menuid"));
        wb.setAjaxMsg(true, "查询成功", "", rst);
        return wb;
    }

    @ResponseBody
    @RequestMapping("/addmenu.sec")
    @AccessControl(url=AccessUrl.MENU_MENUCONFIG,code=AccessCode.ADD)
    public WebResponse addmenu(){
        WebResponse wb = new WebResponse();

        DTO params = getParamAsDTO();
        String sid = params.getAsString("sid");
        Integer parentid = params.getAsInteger("parentid");
        String url = params.getAsString("url");
        Integer sort = params.getAsInteger("sort");
        String name = params.getAsString("name");
        String mtype = params.getAsString("mtype");
        String btype = params.getAsString("btype");
        String jump = params.getAsString("jump");
        String level = params.getAsString("level");

        MenuEntity menu = new MenuEntity();
        menu.setSid(sid);
        menu.setParentid(parentid);
        menu.setUri(url);
        menu.setSort(sort);
        menu.setName(name);
        menu.setMtype(mtype);
        menu.setBtype(btype);
        menu.setJump(jump);
        menu.setLevel(level);
        menu.setAa01(Integer.parseInt(SecurityUtils.getSubject().getUserid()));
        menu.setAa02(Long.parseLong(SecurityUtils.getSubject().getUserid()));
        menuFacade.saveMenu(menu);
        wb.setAjaxMsg(true, "新增成功", "", "");
        return wb;
    }

    @ResponseBody
    @RequestMapping("/addfun.sec")
    @AccessControl(url=AccessUrl.MENU_MENUCONFIG,code = AccessCode.ADD)
    public WebResponse addfun(){
        WebResponse wb = new WebResponse();

        DTO params = getParamAsDTO();
        Integer mid = params.getAsInteger("mid");
        String sid = params.getAsString("sid");
        String code = params.getAsString("code");
        String name = params.getAsString("name");
        Integer sort = params.getAsInteger("sort");
        String type = params.getAsString("type");

        MenuRightsEntity menuRights = new MenuRightsEntity();
        menuRights.setSort(sort);
        menuRights.setName(name);
        menuRights.setType(type);
        menuRights.setCode(code);
        menuRights.setSid(sid);
        menuRights.setMid(mid);
        menuRights.setAa01(Integer.parseInt(SecurityUtils.getSubject().getUserid()));
        menuFacade.saveMenuRights(menuRights);
        wb.setAjaxMsg(true, "新增成功", "", "");
        return wb;
    }

    @ResponseBody
    @RequestMapping("/updatefun.sec")
    @AccessControl(url=AccessUrl.MENU_MENUCONFIG,code= AccessCode.EDIT)
    public WebResponse updatefun(){
        WebResponse wb = new WebResponse();

        DTO params = getParamAsDTO();
        Integer id = params.getAsInteger("id");
        String code = params.getAsString("code");
        String name = params.getAsString("name");
        Integer sort = params.getAsInteger("sort");
        String type = params.getAsString("type");
        Integer mid = params.getAsInteger("mid");

        MenuRightsEntity menuRights = new MenuRightsEntity();
        menuRights.setSort(sort);
        menuRights.setName(name);
        menuRights.setType(type);
        menuRights.setCode(code);
        menuRights.setMid(mid);
        menuRights.setId(id);
        menuRights.setAa01(Integer.parseInt(SecurityUtils.getSubject().getUserid()));
        menuFacade.updateMenuRights(menuRights);
        wb.setAjaxMsg(true, "更新成功", "", "");
        return wb;
    }

    /**
     * 启用或禁用菜单状态  状态,01有效,00无效,默认值=01(修改后的状态,必填)
     * @return
     */
    @ResponseBody
    @RequestMapping("updatestate.sec")
    public WebResponse upate(){
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();
        String state = params.getAsString("state");
        if("00".equals(state)){
            checkArights(AccessUrl.MENU_MENUCONFIG,AccessCode.DISABLED);
        }else if("01".equals(state)){
            checkArights(AccessUrl.MENU_MENUCONFIG,AccessCode.ENABLED);
        }
        menuFacade.updateMenuState(params.getAsInteger("id"),params.getAsString("state"));
        wb.setAjaxMsg(true, "更新成功", "", "");
        return wb;
    }

    @ResponseBody
    @RequestMapping("updatmenu.sec")
    @AccessControl(url=AccessUrl.MENU_MENUCONFIG,code = AccessCode.EDIT)
    public WebResponse updateenu(){
        WebResponse wb = new WebResponse();

        DTO params = getParamAsDTO();
        Integer id = params.getAsInteger("mid");
        String url = params.getAsString("url");
        Integer sort = params.getAsInteger("sort");
        String name = params.getAsString("name");
        String mtype = params.getAsString("mtype");
        String btype = params.getAsString("btype");
        String jump = params.getAsString("jump");

        MenuEntity menu = new MenuEntity();
        menu.setId(id);
        menu.setUri(url);
        menu.setSort(sort);
        menu.setName(name);
        menu.setMtype(mtype);
        menu.setBtype(btype);
        menu.setJump(jump);
        menu.setAb01(Integer.parseInt(SecurityUtils.getSubject().getUserid()));
        menuFacade.updateMenu(menu);
        wb.setAjaxMsg(true, "更新成功", "", "");
        return wb;
    }


    /**
     * 启用或禁用功能状态  状态,01有效,00无效,默认值=01(修改后的状态,必填)
     * @return
     */
    @ResponseBody
    @RequestMapping("updatefunstate.sec")
    public WebResponse updatefunstate(){
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();
        String state = params.getAsString("state");
        if("00".equals(state)){
            checkArights(AccessUrl.MENU_MENUCONFIG,AccessCode.DISABLED);
        }else if("01".equals(state)){
            checkArights(AccessUrl.MENU_MENUCONFIG,AccessCode.ENABLED);
        }
        menuFacade.updateMenuRightsState(params.getAsInteger("id"),state);
        wb.setAjaxMsg(true, "更新成功", "", "");
        return wb;
    }
}


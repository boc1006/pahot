package cn.pahot.upms.controller;

import cn.pahot.upms.access.AccessCode;
import cn.pahot.upms.access.AccessUrl;
import cn.pahot.upms.auth.entity.SystemConfEntity;
import cn.pahot.upms.entity.MenuRightsEntity;
import cn.pahot.upms.entity.RoleEntity;
import cn.pahot.upms.entity.SystemManagerEntity;
import cn.pahot.upms.entity.UserRoleEntity;
import cn.pahot.upms.enums.UPMSMenuEnum;
import cn.pahot.upms.facade.MenuFacade;
import cn.pahot.upms.facade.RoleFacade;
import cn.pahot.upms.facade.SystemFacade;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boc.annotation.AccessControl;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;
import com.boc.common.utils.RightsHelper;
import com.boc.common.web.WebResponse;
import com.boc.common.web.permissions.SecurityUtils;
import com.boc.common.web.springmvc.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 角色管理
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Resource
    private SystemFacade systemFacade;

    @Resource
    private RoleFacade roleFacade;

    @Resource
    private MenuFacade menuFacade;

    @RequestMapping("/index.sec")
    @AccessControl(url = AccessUrl.ROLE_INDEX)
    public ModelAndView index() {
        ModelAndView mv = getModelAndView(AccessUrl.ROLE_INDEX);
        mv.setViewName("role/index");
        return mv;
    }


    @ResponseBody
    @RequestMapping("/sysrole.sec")
    public WebResponse sysrole() {
        WebResponse wb = new WebResponse();

        //获取系统列表
        List<SystemManagerEntity> sysList = systemFacade.getSysList("");

        //循环系统列表查询 角色
        ArrayList<DTO> list = new ArrayList<>();
        for (int i = 0; i < sysList.size(); i++) {
            DTO sys = new BaseDTO();
            SystemManagerEntity sysObj = sysList.get(i);
            String sysId = sysObj.getId();
            sys.put("sysId", sysId);
            sys.put("name", sysObj.getName());
            sys.put("state", sysObj.getState());
            sys.put("sort", sysObj.getSort());
            sys.put("type", sysObj.getType());
            sys.put("isMenu", "1");
            List<RoleEntity> roleLsit = roleFacade.queryRolesBySystemId(sysId, "02", false);
            sys.put("children", roleLsit);
            list.add(sys);
        }
        wb.setAjaxMsg(true, "", "", list);
        return wb;
    }


    /**
     * 根据角色编号查询菜单信息
     * id 角色编号
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "menu_role.sec")
    public WebResponse getMenuRole() {
        WebResponse webResponse = new WebResponse();
        int id = getParamAsDTO().getAsInteger("id");
        RoleEntity roleEntity = roleFacade.queryRoleById(id);

        List<cn.pahot.upms.auth.entity.MenuEntity> list = new ArrayList<>();
        List<SystemConfEntity> listSys = (List<SystemConfEntity>) SecurityUtils.getSubject().getSession().getAttribute("menu");
        for (SystemConfEntity sysConfEntity : listSys) {
            if (sysConfEntity.getId().equals(roleEntity.getSid())) {
                list = sysConfEntity.getList();
            }
        }
        webResponse.setAjaxMsg(true, "", "", getJson(list, roleEntity.getArights(), roleEntity.getHrights(), 0, httpRequestUrl()));
        if ("1".equals(roleEntity.getHrights()) && "1".equals(roleEntity.getHrights())) {
            //判断是否是超级管理员
            webResponse.put("isSupper", true);
        }
        return webResponse;
    }


    private JSONArray getJson(List<cn.pahot.upms.auth.entity.MenuEntity> list, String arights, String hright, int parentId, String url) {
        JSONArray jsonArray = new JSONArray();
        for (cn.pahot.upms.auth.entity.MenuEntity menu : list) {
            if (parentId == menu.getParentid().intValue()) {
                JSONObject json = new JSONObject();
                json.put("id", menu.getId());
                json.put("name", menu.getName());
                json.put("type", 1);
                json.put("isMenu", 1);
                //json.put("icon",url+menu.getAsString("icon"));
                //判断是否有此权限
                json.put("checked", StringUtils.isEmpty(arights) ? false : RightsHelper.testRights(arights, menu.getId()));
                JSONArray jsonArray1 = this.getJson(list, arights, hright, menu.getId(), url);
                //判断菜单是否还有子节点，如果没有子节点就加入操作权限作为子集
                if (null != jsonArray1 && jsonArray1.size() > 0) {
                    json.put("children", jsonArray1);
                } else {
                    List<MenuRightsEntity> listR = menuFacade.queryMenuRightsByMid(menu.getId());
                    JSONArray jsonArray2 = new JSONArray();
                    for (MenuRightsEntity menuRightsEntity : listR) {
                        if (menuRightsEntity.getMid().equals(menu.getId()) && menuRightsEntity.getState().equals("01")) {
                            JSONObject json1 = new JSONObject();
                            json1.put("id", menuRightsEntity.getId());
                            json1.put("name", menuRightsEntity.getName());
                            json1.put("type", 2);
                            //json1.put("icon",url+"resources/admin/images/menu/24901_s.gif");
                            //判断是否有此权限
                            json1.put("checked", StringUtils.isEmpty(hright) ? false : RightsHelper.testRights(hright, menuRightsEntity.getId()));
                            jsonArray2.add(json1);
                        }
                    }
                    //判断是否有操作权限
                    if (null != jsonArray2 && jsonArray2.size() > 0) {
                        json.put("state", "closed");
                        json.put("children", jsonArray2);
                    }
                }
                jsonArray.add(json);
            }
        }
        return jsonArray;
    }

    @ResponseBody
    @RequestMapping("addrights.sec")
    @AccessControl(url = AccessUrl.ROLE_INDEX, code = AccessCode.AUTH)
    public WebResponse addrights() {
        WebResponse wb = new WebResponse();
        DTO params = getParamAsSessionDTO();
        roleFacade.roleByGrant(params);
        wb.setAjaxMsg(true, "", "", null);
        return wb;
    }

    @ResponseBody
    @RequestMapping("/updatestate.sec")
    public WebResponse updatestate() {
        WebResponse wb = new WebResponse();
        DTO params = getParamAsSessionDTO();
        String state = params.getAsString("state");
        if ("01".equals(state)) {
            checkArights(AccessUrl.ROLE_INDEX, AccessCode.ENABLED);
        } else if("00".equals(state)){
            checkArights(AccessUrl.ROLE_INDEX, AccessCode.DISABLED);
        }
        roleFacade.handleRoleState(params);
        wb.setAjaxMsg(true, "", "", null);
        return wb;
    }

    @ResponseBody
    @RequestMapping("/addoreditrole.sec")
    public WebResponse addoreditrole() {
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();
        String state = params.getAsString("state");

        String name = params.getAsString("name");
        Integer sort = params.getAsInteger("sort");
        Long validity = params.getAsLong("validity");
        String sid = params.getAsString("sid");

        RoleEntity role = new RoleEntity();
        role.setName(name);
        role.setSort(sort);
        role.setValidity(validity);
        role.setSid(sid);

        if ("1".equals(state)) {
            checkArights(AccessUrl.ROLE_INDEX, AccessCode.EDIT);
            role.setAb01(getUserId());
            role.setAb02(getCurrentTime());
            role.setId(params.getAsInteger("id"));
            roleFacade.updateRole(role);
        } else if ("0".equals(state)) {
            checkArights(AccessUrl.ROLE_INDEX, AccessCode.ADD);
            role.setAa01(getUserId());
            role.setAa02(getCurrentTime());
            roleFacade.saveRole(role);
        } else {
            wb.setAjaxMsg(false, "state不合法", "", "");
            return wb;
        }
        wb.setAjaxMsg(true, "", "", "");
        return wb;
    }


    @ResponseBody
    @RequestMapping("/getsysroleuser.sec")
    public WebResponse getsysroleuser() {
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();
        String userId = params.getAsString("id");
        //获取系统列表
        List<SystemManagerEntity> sysList = systemFacade.getSysList("01");

        List<UserRoleEntity> userRoleLsit = roleFacade.queryUserRoleBySystemIdAndUserId(null, Integer.parseInt(userId), null);

        //循环系统列表查询 角色
        ArrayList<DTO> list = new ArrayList<>();
        for (int i = 0; i < sysList.size(); i++) {
            DTO sys = new BaseDTO();
            SystemManagerEntity sysObj = sysList.get(i);
            String sysId = sysObj.getId();
            sys.put("sId", sysId);
            sys.put("name", sysObj.getName());
            sys.put("state", sysObj.getState());
            sys.put("sort", sysObj.getSort());
            sys.put("type", sysObj.getType());
            List<RoleEntity> roleLsit = roleFacade.queryRolesBySystemId(sysId, "01", false);
            ArrayList<DTO> childrenArr = new ArrayList<>();
            for (int j = 0; j < roleLsit.size(); j++) {
                RoleEntity rol = roleLsit.get(j);
                DTO role = new BaseDTO();
                role.put("id", rol.getId());
                role.put("sId", rol.getSid());
                role.put("name", rol.getName());
                role.put("state", rol.getState());
                role.put("sort", rol.getSort());
                boolean checked = false;

                for (int k = 0; k < userRoleLsit.size(); k++) {
                    UserRoleEntity userrole = userRoleLsit.get(k);
//                    System.out.print(userrole.getSid() + "-");
//                    System.out.print(rol.getSid() + "-");//,,
//                    System.out.print(userrole.getRid() + "-");
//                    System.out.print(rol.getId() + "\n");
                    if (userrole.getSid().intValue() == Integer.parseInt(rol.getSid()) &&
                            userrole.getRid().intValue() == rol.getId().intValue()) {
                        checked = true;
                        role.put("dataid", userrole.getId());
                        break;
                    }
                }
                role.put("checked", checked);
                childrenArr.add(role);
            }
            sys.put("children", childrenArr);
            list.add(sys);
        }
        wb.setAjaxMsg(true, "", "", list);
        return wb;
    }

    @ResponseBody
    @RequestMapping("/updaterolelink.sec")
    public WebResponse updaterolelink() {
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();
        String state = params.getAsString("state");

        Integer dataId = null;
        // 新增
        if ("1".equals(state)) {
            checkArights(AccessUrl.ROLE_INDEX, AccessCode.AUTH);
            UserRoleEntity userrole = new UserRoleEntity();
            userrole.setUid(params.getAsInteger("uid"));
            userrole.setSid(params.getAsInteger("sid"));
            userrole.setRid(params.getAsInteger("rid"));
            userrole.setAa01(getUserId());
            dataId = roleFacade.saveUserRole(userrole);
        } else if ("0".equals(state)) {
            checkArights(AccessUrl.ROLE_INDEX, AccessCode.AUTH);
            roleFacade.delUserRole(params.getAsInteger("id"));
        } else {
            wb.setAjaxMsg(false, "state不合法", "", "");
            return wb;
        }
        wb.setAjaxMsg(true, "", "", dataId);
        return wb;
    }


}

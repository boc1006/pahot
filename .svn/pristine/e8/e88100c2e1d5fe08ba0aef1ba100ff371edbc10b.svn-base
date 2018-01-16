package cn.pahot.upms.controller;

import cn.pahot.upms.access.AccessCode;
import cn.pahot.upms.access.AccessUrl;
import cn.pahot.upms.entity.OrganizationEntity;
import cn.pahot.upms.entity.UserEntity;
import cn.pahot.upms.enums.UPMSMenuEnum;
import cn.pahot.upms.facade.OrganizationFacade;
import com.boc.annotation.AccessControl;
import com.boc.annotation.AccessLog;
import com.boc.common.metatype.DTO;
import com.boc.common.page.PageParams;
import com.boc.common.utils.string.StringUtil;
import com.boc.common.web.WebResponse;
import com.boc.common.web.permissions.SecurityUtils;
import com.boc.common.web.springmvc.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


/**
 * 组织机构管理
 */
@Controller
@RequestMapping("/org")
public class OrgController extends BaseController {

    @Resource
    private OrganizationFacade organizationFacade;

    @RequestMapping("/orgAndUser.sec")
    @AccessControl(url= AccessUrl.ORG_ORGANDUSER)
    public ModelAndView index() {
        ModelAndView mv = getModelAndView(AccessUrl.ORG_ORGANDUSER);
        mv.setViewName("org/orgAndUser");
        return mv;
    }

    /**
     * 添加用户
     * name 姓名
     * .orgid  所属部门id
     * .birthday 生日
     * .remark 备注
     * .phone 手机号码
     * .sex 性别 性别(1男,0女,9其它)
     * .ab01 更新人id
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/adduser.sec")
    @AccessControl(url = AccessUrl.ORG_ORGANDUSER, code = AccessCode.ADD)
    public WebResponse adduser() {
        WebResponse wb = new WebResponse();

        DTO params = getParamAsDTO();
        String username = params.getAsString("username");
        String name = params.getAsString("name");
        Integer orgid = params.getAsInteger("orgid");
        String birthday = params.getAsString("birthday");
        String remark = params.getAsString("remark");
        String phone = params.getAsString("phone");
        String sex = params.getAsString("sex");

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setName(name);
        user.setOrgid(orgid);
        user.setBirthday(birthday);
        user.setRemark(remark);
        user.setPhone(phone);
        user.setSex(Short.parseShort(sex));
        user.setAa01(Integer.parseInt(SecurityUtils.getSubject().getUserid()));
        user.setAa02(getCurrentTime());
        organizationFacade.addUser(user);
        wb.setAjaxMsg(true, "新增成功", "", "");
        return wb;
    }


    /**
     * 获取用户列表，分页
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getuserlist.sec")
    public WebResponse getuserlist() {
        WebResponse wb = new WebResponse();
        DTO params = getParamAsDTO();
        PageParams<DTO<String, String>> pageParams = new PageParams<>();
        pageParams.setPageNo(params.getAsInteger("page"));
        pageParams.setPageSize(params.getAsInteger("pagesize"));
        pageParams.setParams(params);
        PageInfo<UserEntity> rst = organizationFacade.findByUserListPage(pageParams);
        wb.setDataGridMsg(true, "查询成功", rst.getList(), rst.getTotal());
        return wb;
    }

    /**
     * 更新用户
     * name 姓名
     * .orgid  所属部门id
     * .birthday 生日
     * .remark 备注
     * .phone 手机号码
     * .sex 性别 性别(1男,0女,9其它)
     * .ab01 更新人id
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateuser.sec")
    @AccessControl(url = AccessUrl.ORG_ORGANDUSER, code = AccessCode.EDIT)
    public WebResponse updateuser() {
        WebResponse wb = new WebResponse();

        DTO params = getParamAsDTO();
        String username = params.getAsString("username");
        String name = params.getAsString("name");
        Integer orgid = params.getAsInteger("orgid");
        String birthday = params.getAsString("birthday");
        String remark = params.getAsString("remark");
        String phone = params.getAsString("phone");
        String sex = params.getAsString("sex");

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setName(name);
        user.setOrgid(orgid);
        user.setBirthday(birthday);
        user.setRemark(remark);
        user.setPhone(phone);
        user.setId(params.getAsInteger("id"));
        user.setSex(Short.parseShort(sex));
        user.setAb01(Integer.parseInt(SecurityUtils.getSubject().getUserid()));
        organizationFacade.updateUser(user);
        wb.setAjaxMsg(true, "更新成功", "", "");
        return wb;
    }

    /**
     * 冻结01，解冻02， 注销03,04重置密码
     */
    @ResponseBody
    @RequestMapping("/updatestate.sec")
    public WebResponse updatestate() {
        WebResponse wb = new WebResponse();
        UserEntity user = new UserEntity();
        DTO params = getParamAsDTO();
        user.setId(params.getAsInteger("id"));
        String state = params.getAsString("state");
        user.setAb01(Integer.parseInt(SecurityUtils.getSubject().getUserid()));
        if ("01".equals(state)) {
            checkArights(AccessUrl.ORG_ORGANDUSER,AccessCode.FREEZE);
            organizationFacade.freezeUser(user);
        } else if ("02".equals(state)) {
            checkArights(AccessUrl.ORG_ORGANDUSER,AccessCode.THAW);
            organizationFacade.unFreezeUser(user);
        } else if ("03".equals(state)) {
            checkArights(AccessUrl.ORG_ORGANDUSER,AccessCode.LOGOUT);
            organizationFacade.cancelUser(user);
        } else if ("04".equals(state)) {
            checkArights(AccessUrl.ORG_ORGANDUSER,AccessCode.PWD);
            organizationFacade.resetPwd(user);
        } else {
            wb.setAjaxMsg(false, "state不合法", "", "");
            return wb;
        }
        wb.setAjaxMsg(true, "更新成功", "", "");
        return wb;
    }

    /**
     * 更新密码
     * @return
     */
    @AccessLog
    @ResponseBody
    @RequestMapping("updatepwd.sec")
    public WebResponse updatepwd(){
        WebResponse wb = new WebResponse();

        DTO params = getParamAsDTO();
        Integer id = params.getAsInteger("id");
        String oldPwd = params.getAsString("oldPwd");
        String newPwd = params.getAsString("newPwd");
        if(StringUtil.isEmpty(oldPwd) || id == null || StringUtil.isEmpty(newPwd)){
            wb.setAjaxMsg(false, "缺少参数", "", "");
            return wb;
        }
        organizationFacade.updUserPwd(id,oldPwd,newPwd);
        wb.setAjaxMsg(true, "重置成功", "", "");
        return wb;
    }

    /**
     * 添加部门
     * @return
     */
    @ResponseBody
    @RequestMapping("/addorg.sec")
    @AccessControl(url = AccessUrl.ORG_ORGANDUSER, code = AccessCode.ADD)
    public WebResponse addorg() {
        WebResponse wb = new WebResponse();

        DTO params = getParamAsDTO();
        String name = params.getAsString("name");
        Integer parentid = params.getAsInteger("parentid");
        String tel = params.getAsString("tel");
        String fax = params.getAsString("fax");
        String address = params.getAsString("address");
        String uid = params.getAsString("uid");
        String remark = params.getAsString("remark");
        String levels = params.getAsString("levels");
        String sort = params.getAsString("sort");


        OrganizationEntity user = new OrganizationEntity();

        user.setName(name);
        user.setParentid(parentid);
        user.setTel(tel);
        user.setFax(fax);
        user.setAddress(address);
        user.setUid(uid);
        user.setRemark(remark);
        user.setLevels(levels);
        user.setSort(Short.parseShort(sort));

        user.setAa01(Integer.parseInt(SecurityUtils.getSubject().getUserid()));
        user.setAa02(getCurrentTime());
        organizationFacade.addOrganization(user);
        wb.setAjaxMsg(true, "添加成功", "", "");
        return wb;
    }


    /**
     * 更新部门
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateorg.sec")
    @AccessControl(url = AccessUrl.ORG_ORGANDUSER, code = AccessCode.EDIT)
    public WebResponse updateorg() {
        WebResponse wb = new WebResponse();

        DTO params = getParamAsDTO();
        String name = params.getAsString("name");
        Integer parentid = params.getAsInteger("parentid");
        Integer id = params.getAsInteger("id");
        String tel = params.getAsString("tel");
        String fax = params.getAsString("fax");
        String address = params.getAsString("address");
        String remark = params.getAsString("remark");
        String levels = params.getAsString("levels");
        String sort = params.getAsString("sort");


        OrganizationEntity user = new OrganizationEntity();

        user.setName(name);
        user.setParentid(parentid);
        user.setTel(tel);
        user.setFax(fax);
        user.setAddress(address);
        user.setRemark(remark);
        user.setLevels(levels);
        user.setId(id);
        user.setSort(Short.parseShort(sort));

        user.setAb01(Integer.parseInt(SecurityUtils.getSubject().getUserid()));
        organizationFacade.updOrganization(user);
        wb.setAjaxMsg(true, "更新成功", "", "");
        return wb;
    }


    @ResponseBody
    @RequestMapping("/getorglist.sec")
    public WebResponse getorg(){
        WebResponse wb = new WebResponse();
        List<OrganizationEntity> rst = organizationFacade.queryOrganization(getParamAsDTO());
        wb.setDataGridMsg(true,"查询成功", rst, null);
        return wb;
    }


    /**
     * 启用01，警用02，删除03  部门
     */
    @ResponseBody
    @RequestMapping("/updateorgstate.sec")
    public WebResponse updateorgff() {
        WebResponse wb = new WebResponse();
        OrganizationEntity user = new OrganizationEntity();
        user.setId(getParamAsDTO().getAsInteger("id"));
        String state = getParamAsDTO().getAsString("state");
        user.setAb01(Integer.parseInt(SecurityUtils.getSubject().getUserid()));
        if ("01".equals(state)) {
            checkArights(AccessUrl.ORG_ORGANDUSER,AccessCode.ENABLED);
            organizationFacade.startOrganization(user);
        } else if ("02".equals(state)) {
            checkArights(AccessUrl.ORG_ORGANDUSER,AccessCode.DISABLED);
            organizationFacade.forbiddenOrganization(user);
        } else if ("03".equals(state)) {
            checkArights(AccessUrl.ORG_ORGANDUSER,AccessCode.DELETE);
            organizationFacade.delOrganization(user);
        }else {
            wb.setAjaxMsg(false, "state不合法", "", "");
            return wb;
        }
        wb.setAjaxMsg(true, "更新成功", "", "");
        return wb;
    }
}

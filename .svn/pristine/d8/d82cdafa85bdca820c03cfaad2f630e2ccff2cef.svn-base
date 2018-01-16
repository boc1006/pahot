package cn.pahot.upms.biz;

import cn.pahot.upms.dao.OrganizationMapper;
import cn.pahot.upms.entity.OrganizationEntity;
import cn.pahot.upms.entity.UserEntity;
import cn.pahot.upms.exception.OrganizationException;
import com.boc.common.core.biz.AbstractPageBuilder;
import com.boc.common.core.biz.DefaultPageBuilder;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;
import com.boc.common.page.PageParams;
import com.boc.common.utils.DateUtils;
import com.boc.common.utils.MD5Helper;
import com.boc.common.utils.string.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("organizationBiz")
public class OrganizationBiz {
    @Autowired
    private OrganizationMapper organizationMapper;

    /**
     * 验证用户信息
     *
     * @param user   用户对象
     * @param method 调用方法
     * @param fields 需要验证的属性数组
     */
    private void userValidate(UserEntity user, String method, String... fields) {
        for (String field : fields) {
            if ("id".equalsIgnoreCase(field) && user.getId() == null) {
                throw OrganizationException.PARAM_IS_NULL.newInstance(method + ":id");
            } else if ("username".equalsIgnoreCase(field) && StringUtil.isEmpty(user.getUsername())) {
                throw OrganizationException.PARAM_IS_NULL.newInstance(method + ":username");
            } else if ("orgid".equalsIgnoreCase(field) && user.getOrgid() == null) {
                throw OrganizationException.PARAM_IS_NULL.newInstance(method + ":orgId");
            } else if ("phone".equalsIgnoreCase(field) && StringUtil.isEmpty(user.getPhone())) {
                throw OrganizationException.PARAM_IS_NULL.newInstance(method + ":phone");
            } else if ("aa01".equalsIgnoreCase(field) && user.getAa01() == null) {
                throw OrganizationException.PARAM_IS_NULL.newInstance(method + ":aa01");
            } else if ("ab01".equalsIgnoreCase(field) && user.getAb01() == null) {
                throw OrganizationException.PARAM_IS_NULL.newInstance(method + ":ab01");
            }
        }
    }

    /**
     * 验证机构信息
     *
     * @param org
     * @param method
     * @param fields
     */
    private void orgValidate(OrganizationEntity org, String method, String... fields) {
        for (String field : fields) {
            if ("id".equalsIgnoreCase(field) && org.getId() == null) {
                throw OrganizationException.PARAM_IS_NULL.newInstance(method + ":id");
            } else if ("name".equalsIgnoreCase(field) && StringUtil.isEmpty(org.getName())) {
                throw OrganizationException.PARAM_IS_NULL.newInstance(method + ":name");
            } else if ("parentId".equalsIgnoreCase(field) && org.getParentid() == null) {
                throw OrganizationException.PARAM_IS_NULL.newInstance(method + ":parentId");
            } else if ("tel".equalsIgnoreCase(field) && StringUtil.isEmpty(org.getTel())) {
                throw OrganizationException.PARAM_IS_NULL.newInstance(method + ":tel");
            } else if ("address".equalsIgnoreCase(field) && StringUtil.isEmpty(org.getAddress())) {
                throw OrganizationException.PARAM_IS_NULL.newInstance(method + ":address");
            } else if ("tel".equalsIgnoreCase(field) && StringUtil.isEmpty(org.getTel())) {
                throw OrganizationException.PARAM_IS_NULL.newInstance(method + ":tel");
            } else if ("sort".equalsIgnoreCase(field) && org.getSort() == null) {
                throw OrganizationException.PARAM_IS_NULL.newInstance(method + ":sort");
            } else if ("aa01".equalsIgnoreCase(field) && org.getAa01() == null) {
                throw OrganizationException.PARAM_IS_NULL.newInstance(method + ":aa01");
            } else if ("ab01".equalsIgnoreCase(field) && org.getAb01() == null) {
                throw OrganizationException.PARAM_IS_NULL.newInstance(method + ":ab01");
            }
        }
    }

    public void addUser(UserEntity user) {
        this.userValidate(user, "addUser", "username", "orgid", "phone", "aa01");

        Integer orgId = user.getOrgid();
        OrganizationEntity porg = organizationMapper.findOrgByOrgId(orgId);
        if ("00".equals(porg.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该部门已经删除不能进行操作.");
        if ("02".equals(porg.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该部门已经禁用,不能在该部门下添加用户.");
        //设置添加时间
        user.setAa02(DateUtils.getCurrDateTimeToLong());
        long count = organizationMapper.findUserByUserName(user.getUsername());
        if (count > 0)
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("用户名:" + user.getUsername() + "已经被占用.");
        organizationMapper.addUser(user);
    }

    public void updUser(UserEntity user) {
        this.userValidate(user, "updUser", "id", "username", "orgid", "phone", "ab01");
        Integer orgId = user.getOrgid();
        OrganizationEntity porg = organizationMapper.findOrgByOrgId(orgId);
        if ("00".equals(porg.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该部门已经删除不能进行操作.");
        if ("02".equals(porg.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该部门已经禁用,不能在该部门下添加用户.");
        user.setAb02(DateUtils.getCurrDateTimeToLong());
        UserEntity pUser = organizationMapper.findUserByUserId(user.getId());
        if (pUser == null)
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("未找到id:" + user.getId() + "的账号.");
        if ("02".equals(pUser.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该用户账号已经冻结");
        if ("00".equals(pUser.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该用户账号已近注销.");
        organizationMapper.updUser(user);
    }


    /**
     * 分页查询用户
     *
     * @param params
     * @return
     * @throws BizException
     */
    public PageInfo<UserEntity> findByUserListPage(PageParams<DTO<String, String>> pageParams) throws BizException {
        DTO<String, String> param = pageParams.getParams();
        String orgIdStr = param.get("orgId");
        if (orgIdStr == null) {
            orgIdStr = param.get("orgid");
            param.put("orgId", orgIdStr);
            param.remove("orgid");
        }
        //如果部门不为空则需要加上 levels 模糊查询
        if (orgIdStr != null && orgIdStr.trim().matches("\\d+")) {
            Integer orgId = Integer.parseInt(orgIdStr);
            OrganizationEntity po = organizationMapper.findOrgByOrgId(orgId);
            param.put("levels", po.getLevels() + "_" + po.getId());
        } else {
            param.remove("orgId");
        }

        PageInfo<UserEntity> pi = DefaultPageBuilder.build(pageParams,
                new AbstractPageBuilder<UserEntity, DTO<String, String>>() {
                    @Override
                    public Page<UserEntity> build(DTO<String, String> param) throws BizException {
                        return organizationMapper.findByUserListPage(param);
                    }
                });
        return pi;
    }

    public UserEntity findUserByUserId(Integer userId) {
        if (userId == null || userId == 0)
            throw OrganizationException.PARAM_IS_NULL.newInstance("用户id不能为null或者0.");
        return organizationMapper.findUserByUserId(userId);
    }

    public void unFreezeUser(UserEntity user) {
        this.userValidate(user, "unFreezeUser", "id", "ab01");
        user.setAb02(DateUtils.getCurrDateTimeToLong());
        UserEntity pUser = organizationMapper.findUserByUserId(user.getId());
        if ("01".equals(pUser.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该用户账号尚未冻结,不能解除冻结.");
        if ("00".equals(pUser.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该用户账号已近注销.");
        organizationMapper.unFreezeUser(user);
    }

    public void freezeUser(UserEntity user) {
        this.userValidate(user, "freezeUser", "id", "ab01");
        user.setAb02(DateUtils.getCurrDateTimeToLong());
        UserEntity pUser = organizationMapper.findUserByUserId(user.getId());
        if ("02".equals(pUser.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该用户账号已经冻结,请勿重复操作.");
        if ("00".equals(pUser.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该用户账号已近注销.");
        organizationMapper.freezeUser(user);
    }

    public void cancelUser(UserEntity user) {
        this.userValidate(user, "cancelUser", "id", "ab01");
        user.setAb02(DateUtils.getCurrDateTimeToLong());
        UserEntity pUser = organizationMapper.findUserByUserId(user.getId());
        if ("00".equals(pUser.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该用户账号已近注销.");
        organizationMapper.cancelUser(user);
    }

    public void resetPwd(UserEntity user) {
        this.userValidate(user, "resetPwd", "id");
        UserEntity pUser = organizationMapper.findUserByUserId(user.getId());
        if ("02".equals(pUser.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该用户账号已经冻结.");
        if ("00".equals(pUser.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该用户账号已近注销.");
        organizationMapper.resetPwd(user);
    }

    public void addOrganization(OrganizationEntity organization) {
        this.orgValidate(organization, "addOrganization", "name", "parentid", "tel", "address", "sort", "aa01");
        organization.setAa02(DateUtils.getCurrDateTimeToLong());
        Integer parentId = organization.getParentid();
        if (parentId == null || parentId == 0)
            organization.setLevels("0");
        else {
            OrganizationEntity po = this.organizationMapper.findOrgByOrgId(parentId);
            if ("00".equals(po.getState()))
                throw OrganizationException.PARAM_HINT_ERROR.newInstance("参数错误,上级部门:" + parentId + "已经删除.");
            if ("02".equals(po.getState()))
                throw OrganizationException.PARAM_HINT_ERROR.newInstance("参数错误,上级部门:" + parentId + "已经禁用.");
            organization.setLevels(po.getLevels() + "_" + po.getId());
        }
        organizationMapper.addOrganization(organization);
    }

    public void updOrganization(OrganizationEntity organization) {
        this.orgValidate(organization, "updOrganization", "id", "name", "parentid", "tel", "address", "sort", "ab01");
        organization.setAb02(DateUtils.getCurrDateTimeToLong());
        //检测当前部门
        OrganizationEntity porganization = organizationMapper.findOrgByOrgId(organization.getId());
        String msg = null;
        if ("00".equals(porganization.getState()))
            msg = "该部门已经删除不能操作!";
        else if ("02".equals(porganization.getState()))
            msg = "该部门处于冻结状态!";
        if (msg != null)
            throw OrganizationException.PARAM_HINT_ERROR.newInstance(msg);

        //检测上级部门
        OrganizationEntity parentOrg = organizationMapper.findOrgByOrgId(organization.getParentid());
        msg = null;
        if ("00".equals(parentOrg.getState()))
            msg = "上级部门已经删除不能操作!";
        else if ("02".equals(parentOrg.getState()))
            msg = "上级部门处于冻结状态!";
        if (msg != null)
            throw OrganizationException.PARAM_HINT_ERROR.newInstance(msg);
        Integer parentId = organization.getParentid();
        if (parentId == null || parentId == 0)
            organization.setLevels("0");
        else {
            OrganizationEntity parendO = this.organizationMapper.findOrgByOrgId(parentId);
            if ("00".equals(parendO.getState()))
                throw OrganizationException.PARAM_HINT_ERROR.newInstance("参数错误,上级部门:" + parentId + "已经删除");
            organization.setLevels(parendO.getLevels() + "_" + parendO.getId());
        }
        organizationMapper.updOrganization(organization);
    }

    public List<OrganizationEntity> queryOrganization(DTO param) {
        return organizationMapper.queryOrganization(param);
    }

    /**
     * 启用部门
     *
     * @param organization
     */
    public void startOrganization(OrganizationEntity organization) {
        this.orgValidate(organization, "startOrganization", "id", "ab01");
        organization.setAb02(DateUtils.getCurrDateTimeToLong());
        OrganizationEntity pOrganization = organizationMapper.findOrgByOrgId(organization.getId());
        //01启用,02禁用,00删除
        if ("00".equals(pOrganization.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该部门已经删除不能进行操作.");
        if ("01".equals(pOrganization.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该部门已经已经是启用状态,不能重复操作.");
        DTO<String, Object> param = new BaseDTO<>();
        param.put("id", organization.getId());
        param.put("state", "02");
        //检查他的父级部门是否是禁用状态
        long count = organizationMapper.getParentOrgCountByOrgIdAndState(param);
        if (count > 0)
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该部门的上级部门处于禁用状态 ,请先启用上级部门.");
        //启用部门
        organizationMapper.startOrganization(organization);
    }

    /**
     * 禁用部门
     *
     * @param organization
     */
    public void forbiddenOrganization(OrganizationEntity organization) {
        this.orgValidate(organization, "forbiddenOrganization", "id", "ab01");
        organization.setAb02(DateUtils.getCurrDateTimeToLong());
        OrganizationEntity pOrganization = organizationMapper.findOrgByOrgId(organization.getId());
        //01启用,02禁用,00删除
        if ("00".equals(pOrganization.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该部门已经删除不能进行操作.");
        if ("02".equals(pOrganization.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该部门已经已经是禁用状态,不能重复操作.");

        DTO<String, Object> param = new BaseDTO<>();
        param.put("id", organization.getId());
        param.put("state", "01");
        //检查下级部门是否有处于正常状态的
        long count = organizationMapper.getSubOrgCountByOrgIdAndState(param);
        if (count > 0)
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("请先禁用该部门所有下级部门.");
        //启用部门

        organizationMapper.forbiddenOrganization(organization);
    }

    public void delOrganization(OrganizationEntity organization) {
        this.orgValidate(organization, "delOrganization", "id", "ab01");
        organization.setAb02(DateUtils.getCurrDateTimeToLong());
        OrganizationEntity pOrganization = organizationMapper.findOrgByOrgId(organization.getId());
        if ("00".equals(pOrganization.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该部门已经已经是删除状态,不能重复操作.");
        DTO<String, Object> param = new BaseDTO<>();
        param.put("id", organization.getId());
        param.put("state", "00");
        //查询未删除的子部门数量
        long count = organizationMapper.getSubOrgCountByOrgIdAndNotState(param);
        if (count > 0)
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("请先删除该部门所有下级部门.");
        //查询该部门下未删除的用户
        /*DTO<String, Object> param2 = new BaseDTO<>();
        param2.put("id", organization.getId());
        param2.put("state", "00");*/
        //查询未删除的子部门数量
        long userCount = organizationMapper.getCurrOrgUserCountByNotState(param);
        if (userCount > 0)
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("该部门存在未删除的用户 ,不能删除.");
        organizationMapper.delOrganization(organization);
    }

    public void updUserPwd(Integer id, String oldPasswd, String newPasswd) {
        if (id == null)
            throw OrganizationException.PARAM_IS_NULL.newInstance("updUserPwd:id");
        if (StringUtil.isEmpty(newPasswd))
            throw OrganizationException.PARAM_IS_NULL.newInstance("updUserPwd:newPasswd");
       /* if(StringUtil.isEmpty(oldPasswd))
            throw OrganizationException.PARAM_IS_NULL.newInstance("delOrganization:oldPasswd");*/
        if (!newPasswd.matches("\\w{6,20}"))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("updUserPwd:密码长度必须为6-20位(字母数字和下划线组成)且必须包含大写,小写字母和数字");
        if (!newPasswd.matches(".*[A-Z].*"))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("updUserPwd:密码长度必须为6-20位(字母数字和下划线组成)且必须包含大写,小写字母和数字");
        if (!newPasswd.matches(".*[a-z].*"))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("updUserPwd:密码长度必须为6-20位(字母数字和下划线组成)且必须包含大写,小写字母和数字");
        if (!newPasswd.matches(".*[\\d].*"))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("updUserPwd:密码长度必须为6-20位(字母数字和下划线组成)且必须包含大写,小写字母和数字");
        UserEntity user = organizationMapper.findUserByUserId(id);
        if (user == null)
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("updUserPwd:未找到用户id:" + id + "的用户信息");
        UserEntity pUser = organizationMapper.findUserByUserId(user.getId());
        if (pUser == null)
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("updUserPwd:未找到id:" + user.getId() + "的账号.");
        if ("02".equals(pUser.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("updUserPwd:该用户账号已经冻结");
        if ("00".equals(pUser.getState()))
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("updUserPwd:该用户账号已近注销.");
        //
        String oldpwd = MD5Helper.encrypt(oldPasswd);
        if (!user.getPasswd().equals(oldpwd)) {
            throw OrganizationException.PARAM_HINT_ERROR.newInstance("updUserPwd:密码错误!.");
        }
        DTO<String, String> dto = new BaseDTO();
        dto.put("id", String.valueOf(id));
        dto.put("oldPasswd", oldpwd);
        dto.put("newPasswd", MD5Helper.encrypt(newPasswd));
        organizationMapper.updUserPwd(dto);

    }
}

package cn.pahot.upms.service;

import cn.pahot.upms.biz.OrganizationBiz;
import cn.pahot.upms.entity.OrganizationEntity;
import cn.pahot.upms.entity.UserEntity;
import cn.pahot.upms.facade.OrganizationFacade;
import com.boc.common.metatype.DTO;
import com.boc.common.page.PageParams;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("organizationFacade")
public class OrganizationService implements OrganizationFacade {

    @Autowired
    private OrganizationBiz organizationBiz;

    /**
     * 添加用户
     *
     * @param user
     */
    @Override
    public void addUser(UserEntity user) {
        this.organizationBiz.addUser(user);
    }

    /**
     * 更新用户
     *
     * @param user
     */
    @Override
    public void updateUser(UserEntity user) {
        this.organizationBiz.updUser(user);
    }

    /**
     * 分页查询用户
     *
     * @param param
     * @return
     */
    @Override
    public PageInfo<UserEntity> findByUserListPage(PageParams<DTO<String, String>> param) {
        return organizationBiz.findByUserListPage(param);
    }

    /**
     * 解冻用户账号
     *
     * @param user
     */
    @Override
    public void unFreezeUser(UserEntity user) {
        organizationBiz.unFreezeUser(user);
    }

    /**
     * 冻结用户账号
     *
     * @param user
     */
    @Override
    public void freezeUser(UserEntity user) {
        organizationBiz.freezeUser(user);
    }

    /**
     * 注销用户账号
     *
     * @param user
     */
    @Override
    public void cancelUser(UserEntity user) {
        organizationBiz.cancelUser(user);
    }

    /**
     * 重置密码
     *
     * @param user
     */
    @Override
    public void resetPwd(UserEntity user) {
        organizationBiz.resetPwd(user);
    }

    /**
     * 添加机构
     *
     * @param organization
     */
    @Override
    public void addOrganization(OrganizationEntity organization) {
        organizationBiz.addOrganization(organization);
    }

    /**
     * 更新机构
     *
     * @param organization
     */
    @Override
    public void updOrganization(OrganizationEntity organization) {
        organizationBiz.updOrganization(organization);
    }

    /**
     * 查询所有机构
     *
     * @param param
     * @return
     */
    @Override
    public List<OrganizationEntity> queryOrganization(DTO param) {
        return organizationBiz.queryOrganization(param);
    }

    /**
     * 启用机构
     *
     * @param organization
     */
    @Override
    public void startOrganization(OrganizationEntity organization) {
        organizationBiz.startOrganization(organization);
    }

    /**
     * 禁用机构
     *
     * @param organization
     */
    @Override
    public void forbiddenOrganization(OrganizationEntity organization) {
        organizationBiz.forbiddenOrganization(organization);
    }

    /**
     * 删除机构
     *
     * @param organization
     */
    @Override
    public void delOrganization(OrganizationEntity organization) {
        organizationBiz.delOrganization(organization);
    }

    /**
     * 更新密码
     *
     * @param id
     * @param oldPasswd
     * @param newPasswd
     */
    @Override
    public void updUserPwd(Integer id, String oldPasswd, String newPasswd) {
        organizationBiz.updUserPwd(id, oldPasswd, newPasswd);
    }
}

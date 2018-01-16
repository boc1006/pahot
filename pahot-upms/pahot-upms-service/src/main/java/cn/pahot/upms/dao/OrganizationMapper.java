package cn.pahot.upms.dao;


import cn.pahot.upms.entity.OrganizationEntity;
import cn.pahot.upms.entity.UserEntity;
import com.boc.common.metatype.DTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrganizationMapper {
    /**
     * 添加用户
     *
     * @param user
     */
    void addUser(UserEntity user);

    /**
     * 更新用户
     *
     * @param user
     */
    void updUser(UserEntity user);

    long findUserByUserName(@Param("username") String username);

    /**
     * 查询所有用户
     *
     * @param param
     * @return
     */
    List<OrganizationEntity> queryUser(DTO param);


    /**
     * 分页查询用户
     *
     * @return
     */
    Page<UserEntity> findByUserListPage(DTO param);

    /**
     * 根据id查询用户信息
     *
     * @param userId
     * @return
     */
    UserEntity findUserByUserId(@Param("id") Integer userId);

    /**
     * 解冻用户
     *
     * @param user
     */
    void unFreezeUser(UserEntity user);

    /**
     * 冻结用户
     *
     * @param user
     */
    void freezeUser(UserEntity user);

    /**
     * 注销用户
     *
     * @param user
     */
    void cancelUser(UserEntity user);

    /**
     * 重置密码
     *
     * @param user
     */
    void resetPwd(UserEntity user);


    //--------------------组织机构相关-----------------------------------

    /**
     * 添加组织机构
     *
     * @param organization
     */
    void addOrganization(OrganizationEntity organization);

    /**
     * 更新组织机构
     *
     * @param organization
     */
    void updOrganization(OrganizationEntity organization);

    /**
     * 查询组织机构
     *
     * @param param
     * @return
     */
    List<OrganizationEntity> queryOrganization(DTO param);

    /**
     * 启动
     *
     * @param organization
     */
    void startOrganization(OrganizationEntity organization);

    /**
     * 禁用
     *
     * @param organization
     */
    void forbiddenOrganization(OrganizationEntity organization);

    /**
     * 删除
     *
     * @param organization
     */
    void delOrganization(OrganizationEntity organization);

    /**
     * 查询机构id
     *
     * @param orgId
     * @return
     */
    OrganizationEntity findOrgByOrgId(@Param("id") Integer orgId);

    /**
     * 通过orgId和state查询父级别机构数据量
     */

    long getParentOrgCountByOrgIdAndState(DTO parms);

    /**
     * 通过orgId和子机构state查询满足条件的子机构数量
     */
    long getSubOrgCountByOrgIdAndState(DTO parms);

    /**
     * 查询指定机构的子机构中状态不满足的数据条数
     *
     * @param parms
     * @return
     */
    long getSubOrgCountByOrgIdAndNotState(DTO parms);


    //  SELECT  * from pt_upms_user where ORGID = 1 and  STATE != '00';

    /**
     * 查询机构下的用户 且不满足指定状态的用户
     *
     * @param parms
     * @return
     */
    long getCurrOrgUserCountByNotState(DTO parms);


    /**
     * 更新用户密码
     *
     * @param parms
     * @return
     */
    void updUserPwd(DTO parms);


}

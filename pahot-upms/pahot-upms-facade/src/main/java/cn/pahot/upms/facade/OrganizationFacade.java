package cn.pahot.upms.facade;

import cn.pahot.upms.entity.OrganizationEntity;
import cn.pahot.upms.entity.UserEntity;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.page.PageParams;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 组织部门管理接口定义
 * <p>@Title: OrganizationFacade.java
 * <p>@Package cn.pahot.upms.facade
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com
 * <p>@date 2017年12月6日 下午1:16:31
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface OrganizationFacade {

    /**
     * 添加用户
     *
     * @param user user.username 登录名
     *             user.name 姓名
     *             user.orgid  所属部门Id
     *             user.birthday 生日
     *             user.remark 备注
     *             user.phone 手机号码
     *             user.sex 性别 性别(1男,0女,9其它)
     *             user.aa01 添加人id
     *             user.aa02  添加时间 如 201712071611
     */
    void addUser(UserEntity user) throws BizException;

    /**
     * 更新用户
     *
     * @param user user.name 姓名
     *             user.orgid  所属部门id
     *             user.birthday 生日
     *             user.remark 备注
     *             user.phone 手机号码
     *             user.sex 性别 性别(1男,0女,9其它)
     *             user.ab01 更新人id
     *             user.ab02  更新时间 如 201712071611
     */
    void updateUser(UserEntity user) throws BizException;


    /**
     * 分页查询用户列表
     *
     * @param param param.pageNo 当前第几页  从1开始
     *              param.pageSize 每页显示数据条数
     *              param.params  查询条件 (map键值对 参数 orgId )
     * @return
     */
    PageInfo<UserEntity> findByUserListPage(PageParams<DTO<String, String>> param) throws BizException;


    /**
     * 冻结用户
     *
     * @param user user.id 用户id
     *             user.ab01 更新人id
     *             user.ab02  更新时间 如 201712071611
     */
    void freezeUser(UserEntity user) throws BizException;

    /**
     * 解冻用户
     *
     * @param user user.id 用户id
     *             user.ab01 更新人id
     *             user.ab02  更新时间 如 201712071611
     */
    void unFreezeUser(UserEntity user) throws BizException;


    /**
     * 注销用户
     *
     * @param user user.id 用户id
     *             user.ab01 更新人id
     *             user.ab02  更新时间 如 201712071611
     */
    void cancelUser(UserEntity user) throws BizException;

    /**
     * 重置密码
     *
     * @param user user.id 用户id
     *             user.ab01 更新人id
     *             user.ab02  更新时间 如 201712071611
     */
    void resetPwd(UserEntity user) throws BizException;

    /**
     * 添加部门
     *
     * @param organization organization.name 部门名称
     *                     organization.parentid  上级部门id
     *                     organization.tel  部门电话
     *                     organization.fax 部门传真
     *                     organization.address 部门地址
     *                     organization.uid   部门主管  现在没有 默认为0
     *                     organization.remark 备注
     *                     organization.levels  部门等级
     *                     organization.sort   部门排序   如 1 ,2,3
     *                     organization.aa01  新增人id
     *                     organization.aa02  新增时间 201712071645
     */
    void addOrganization(OrganizationEntity organization) throws BizException;

    /**
     * 更新部门
     *
     * @param organization organization.name 部门名称
     *                     organization.parentid  上级部门id
     *                     organization.tel  部门电话
     *                     organization.fax 部门传真
     *                     organization.address 部门地址
     *                     organization.remark 备注
     *                     organization.levels  部门等级
     *                     organization.sort   部门排序   如 1 ,2,3
     *                     organization.ab01  更新人id
     *                     organization.ab02  更新时间 201712071645
     */
    void updOrganization(OrganizationEntity organization) throws BizException;

    /**
     * 查询部门
     *
     * @param param
     * @return
     */
    List<OrganizationEntity> queryOrganization(DTO param) throws BizException;

    /**
     * 启动部门
     *
     * @param organization organization.id 部门id
     *                     organization.ab01  更新人id
     *                     organization.ab02  更新时间 201712071645
     */
    void startOrganization(OrganizationEntity organization) throws BizException;

    /**
     * 禁用部门
     *
     * @param organization organization.id 部门id
     *                     organization.ab01  更新人id
     *                     organization.ab02  更新时间 201712071645
     */
    void forbiddenOrganization(OrganizationEntity organization) throws BizException;

    /**
     * 删除部门
     *
     * @param organization organization.id 部门id
     *                     organization.ab01  更新人id
     *                     organization.ab02  更新时间 201712071645
     */
    void delOrganization(OrganizationEntity organization) throws BizException;

    /**
     * 更新用户密码
     *
     * @param id        用户id
     * @param oldPasswd 旧密码
     * @param newPasswd 新密码 (6-20位,只能是字母数字和下划线,必须同时包含大小写字母和数字)
     */
    void updUserPwd(Integer id, String oldPasswd, String newPasswd) throws BizException;
}

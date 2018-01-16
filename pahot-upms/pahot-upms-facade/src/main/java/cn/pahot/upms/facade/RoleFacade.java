package cn.pahot.upms.facade;

import cn.pahot.upms.entity.RoleEntity;
import cn.pahot.upms.entity.UserRoleEntity;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;

import java.util.List;

/**
 * 角色管理
 * <p>@Title: RoleFacade.java
 * <p>@Package cn.pahot.upms.facade
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com
 * <p>@date 2017年12月7日 上午10:43:26
 * <p>@version V1.0
 * <p>Copyright © bocgroup.All Rights Reserved.
 */
public interface RoleFacade {

    /**
     * 根据系统编号查询指定系统下的角色列表
     *
     * @param sysId  系统编号
     * @param state  指定状态,状态定义 -> cn.pahot.upms.consts.RoleStateEnum
     * @param expire true:只查询有效期内角色,false:查询所有
     * @return
     */
    List<RoleEntity> queryRolesBySystemId(String sysId, String state, boolean expire) throws BizException;

    /**
     * 新增角色
     *
     * @param roleEntity 
     * <p>roleEntity.sid 系统编号</p>
     * <p>roleEntity.name 角色名称</p>
     * <p>roleEntity.validity 角色有效期,0表示长期有效,大于0表示指定有效期,格式:20171207110903</p>
     * <p>roleEntity.sort 排序</p>
     * <p>roleEntity.aa01 操作人ID</p>
     * @return 角色编号
     * @throws BizException
     */
    Integer saveRole(RoleEntity roleEntity) throws BizException;

    /**
     * 修改角色基本信息
     *
     * @param roleEntity roleEntity.id 角色ID
     *                   roleEntity.name 角色名称
     *                   roleEntity.validity 角色有效期,0表示长期有效,大于0表示指定有效期,格式:20171207110903
     *                   roleEntity.sort 排序
     *                   roleEntity.ab01 操作人ID
     * @throws BizException
     */
    void updateRole(RoleEntity roleEntity) throws BizException;

    /**
     * 变更角色状态
     *
     * @param param 传入SessionDTO<String,String> 对象
     *              key=roleId:角色编号
     *              key=state:变更后状态,状态定义 -> cn.pahot.upms.consts.RoleStateEnum
     */
    void handleRoleState(DTO<String, String> param) throws BizException;

    /**
     * 根据角色ID查询角色信息
     *
     * @param roleId 角色id
     * @return
     */
    RoleEntity queryRoleById(Integer roleId) throws BizException;

    /**
     * 对角色进行授权
     *
     * @param param 传入SessionDTO<String,String> 对象
     *              key=roleId 角色编号
     *              key=aRights 菜单访问权限,多个菜单编号用","分隔
     *              key=hRights 菜单操作权限,多个菜单操作权限编号用","分隔
     * @throws BizException
     */
    void roleByGrant(DTO<String, String> param) throws BizException;


    /**
     * 更具系统id和用户id查询用户的权限列表
     *
     * @param sysId  子系统id 可以为null
     * @param userId 用户id  非空
     * @param roleId 角色id  可以为null
     * @return
     * @throws BizException
     */
    List<UserRoleEntity> queryUserRoleBySystemIdAndUserId(Integer sysId, Integer userId, Integer roleId) throws BizException;

    /**
     * 新怎用户角色关系
     *
     * @param userRoleEntity 角色关系
     *                       userRoleEntity.uid 用户id
     *                       userRoleEntity.sid 系统id
     *                       userRoleEntity.rid 角色id
     *                       userRoleEntity.aa01 操作人id
     * @throws BizException
     */
    Integer saveUserRole(UserRoleEntity userRoleEntity) throws BizException;

    /**
     * 删除用户角色关系
     *
     * @param id 用户角色关系id
     * @throws BizException
     */
    void delUserRole(Integer id) throws BizException;
}

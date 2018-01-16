package cn.pahot.upms.dao;

import cn.pahot.upms.entity.RoleEntity;
import cn.pahot.upms.entity.UserRoleEntity;
import com.boc.common.metatype.DTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色管理DAO
 * <p>@Title: RoleMapper.java
 * <p>@Package cn.pahot.upms.dao
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com
 * <p>@date 2017年12月7日 上午10:59:31
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface RoleMapper {

    /**
     * 查询指定业务子系统下的角色列表
     *
     * @param param
     * @return
     */
    List<RoleEntity> queryRolesBySystemId(DTO<String, String> param);

    /**
     * 新增角色
     *
     * @param roleEntity
     */
    void saveRole(RoleEntity roleEntity);

    /**
     * 修改角色信息
     *
     * @param roleEntity
     */
    void updateRole(RoleEntity roleEntity);

    /**
     * 根据角色编号查询角色信息
     *
     * @param id
     * @return
     */
    RoleEntity queryRoleById(Integer id);

    /**
     * 查询用户角色关系
     *
     * @param param
     * @return
     */
    List<UserRoleEntity> queryUserRole(DTO<String, Integer> param);

    /**
     * 保存用户角色关系
     *
     * @param roleEntity
     */
    void saveUserRole(UserRoleEntity roleEntity);

    /**
     * 删除用户菜单
     */
    void delUserRole(@Param("id") Integer id);
}

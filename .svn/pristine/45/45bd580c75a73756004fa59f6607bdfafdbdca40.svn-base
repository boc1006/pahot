package cn.pahot.business.dao;

import cn.pahot.business.entity.BusinessRoleEntity;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 商家角色数据库接口
 */
public interface BusinessRoleDao {

    /*添加一个商家的角色*/
    void addBusinessRole(BusinessRoleEntity businessRoleEntity);

    /*查找一个商家角色*/
    BusinessRoleEntity getBusinessRoleItem(@Param("id") Long id);

    /*获取角色列表*/
    Page<BusinessRoleEntity> getBusinessRoleList();

    /*更新角色信息*/
    void updateBusinessRoleItem(BusinessRoleEntity businessRoleEntity);

}

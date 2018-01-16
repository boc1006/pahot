package cn.pahot.upms.dao;

import cn.pahot.upms.entity.MenuRightsEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuRightsMapper {

    /**
     * 根据id查询菜单权限
     * @param id
     * @return
     */
    MenuRightsEntity selectById(Integer id);

    /**
     * 查找选中菜单下的操作权限列表
     * @param mid
     * @eturn
     */
    List<MenuRightsEntity> selectByMid(Integer mid);

    /**
     * 新增操作权限
     * @param menuRights
     */
    void insert(MenuRightsEntity menuRights);

    /**
     * 修改操作权限
     * @param menuRight
     */
    void update(MenuRightsEntity menuRight);

    /**
     * 启用或者禁用
     * @param id
     */
    void updateState(@Param("id") Integer id, @Param("state")String state);

}

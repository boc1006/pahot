package cn.pahot.upms.dao;

import cn.pahot.upms.entity.MenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {

    /**
     * 根据id查找菜单
     *
     * @param id
     * @return
     */
    MenuEntity selectById(Integer id);

    /**
     * 查找选中系统下的菜单列表
     *
     * @param sid
     * @return
     */
    List<MenuEntity> selectBySid(String sid);

    /**
     * 新增菜单
     *
     * @param menu
     */
    void insert(MenuEntity menu);

    /**
     * 修改菜单
     *
     * @param menu
     */
    void update(MenuEntity menu);

    /**
     * 启用或者禁用
     *
     * @param id
     */
    void updateState(@Param("id") Integer id, @Param("state")String state);

}

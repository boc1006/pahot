package cn.pahot.upms.facade;

import cn.pahot.upms.entity.MenuEntity;
import cn.pahot.upms.entity.MenuRightsEntity;
import com.boc.common.exception.BizException;

import java.util.List;

/**
 * 菜单管理接口定义
 * <p>@Title: MenuFacade.java
 * <p>@Package cn.pahot.upms.facade 
 * <p>@Description: TODO
 * <p>@author yuzhiyun
 * <p>@date 2017年12月7日
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface MenuFacade {

    /**
     * 根据id查询菜单详情
     *
     * @param id 菜单id(必填)
     * @return
     */
    MenuEntity queryMenuById(Integer id);

    /**
     * 查找选中系统下的菜单列表(树形结构）
     *
     * @param sid 系统id(必填)
     * @return
     */
    List<MenuEntity> queryMenuBySid(String sid);

    /**
     * 新增菜单
     *
     * @param menu
     *  menu.sid 系统id(必填)
     *  menu.parentid 上级菜单编号,顶级为0(必填)
     *  menu.name 菜单名称(必填)
     *  menu.mtype 菜单类型,01结构型,02功能型(必填)
     *  menu.btype 业务类型,01系统管理型,02业务操作型,03综合类型(必填)
     *  menu.icon 显示图标
     *  menu.jump 跳转方式,01主窗口,02弹出窗口,03新开窗口,默认值为01(必填)
     *  menu.uri 跳转路径,默认值=#
     *  menu.sort 排序序号
     *  menu.level 菜单层级,编码规则:菜单编号_菜单编号(必填)
     *  menu.aa01 创建人(必填)
     *
     * @return
     */
    void saveMenu(MenuEntity menu);

    /**
     * 修改菜单
     *
     * @param menu
     *  menu.id 菜单id(必填)
     *  menu.sid 系统id
     *  menu.parentid 上级菜单编号,顶级为0
     *  menu.name 菜单名称
     *  menu.mtype 菜单类型,01结构型,02功能型
     *  menu.btype 业务类型,01系统管理型,02业务操作型,03综合类型
     *  menu.icon 显示图标
     *  menu.jump 跳转方式,01主窗口,02弹出窗口,03新开窗口,默认值为01
     *  menu.uri 跳转路径,默认值=#
     *  menu.sort 排序序号,默认为1
     *  menu.level 菜单层级,编码规则:菜单编号_菜单编号
     *  menu.ab01 修改人
     */
    void updateMenu(MenuEntity menu);

    /**
     * 启用或者禁用
     *
     * @param id 菜单id(必填)
     * @param state 状态,01有效,00无效,默认值=01(修改后的状态,必填)
     */
    void updateMenuState(Integer id, String state);


    /**
     * 根据id查询菜单权限详情
     *
     * @param id 菜单权限id(必填)
     * @return
     */
    MenuRightsEntity queryMenuRightsById(Integer id);

    /**
     * 查找选中菜单下的操作权限列表
     * @param mid 菜单id
     * @return
     */
    List<MenuRightsEntity> queryMenuRightsByMid(Integer mid);

    /**
     * 新增操作权限
     * @param menuRights
     *  menuRights.mid 菜单id(必填)
     *  menuRights.sid 系统id(必填)
     *  menuRights.code 能通过该字段快速判断用户权限信息(必填)
     *  menuRights.name 显示名称(必填)
     *  menuRights.type 操作权限类型,01功能操作,02数据访问(列),03数据访问(行),默认值=01
     *  menuRights.sort 排序序号,默认为1
     *  menuRights.aa01 创建人(必填)
     */
    void saveMenuRights(MenuRightsEntity menuRights);

    /**
     * 修改操作权限
     * @param menuRight
     *  menuRights.id 操作权限id(必填)
     *  menuRights.mid 菜单id
     *  menuRights.sid 系统id
     *  menuRights.code 能通过该字段快速判断用户权限信息
     *  menuRights.name 显示名称
     *  menuRights.type 操作权限类型,01功能操作,02数据访问(列),03数据访问(行),默认值=01
     *  menuRights.sort 排序序号,默认为1
     *  menuRights.ab01 修改人
     */
    void updateMenuRights(MenuRightsEntity menuRight);

    /**
     * 启用或者禁用
     * @param id 菜单id(必填)
     * @param state 状态,01有效,00无效,默认值=01(修改后的状态,必填)
     * @param id
     */
    void updateMenuRightsState(Integer id, String state);

}

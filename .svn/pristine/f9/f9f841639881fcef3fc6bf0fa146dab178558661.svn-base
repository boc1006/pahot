package cn.pahot.upms.service;

import cn.pahot.upms.biz.MenuBiz;
import cn.pahot.upms.biz.MenuRightsBiz;
import cn.pahot.upms.entity.MenuEntity;
import cn.pahot.upms.entity.MenuRightsEntity;
import com.boc.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pahot.upms.facade.MenuFacade;

import java.util.List;

@Service("menuFacade")
public class MenuService implements MenuFacade {
    @Autowired
    private MenuBiz menuBiz;
    @Autowired
    private MenuRightsBiz menuRightsBiz;

    @Override
    public MenuEntity queryMenuById(Integer id) {
        return menuBiz.findById(id);
    }

    @Override
    public List<MenuEntity> queryMenuBySid(String sid) {
        return menuBiz.findBySid(sid);
    }

    @Override
    public void saveMenu(MenuEntity menu) {
        menuBiz.add(menu);
    }

    @Override
    public void updateMenu(MenuEntity menu) {
        menuBiz.update(menu);
    }

    @Override
    public void updateMenuState(Integer id, String state) {
        menuBiz.updateState(id, state);
    }

    @Override
    public MenuRightsEntity queryMenuRightsById(Integer id) {
        return menuRightsBiz.findById(id);
    }

    @Override
    public List<MenuRightsEntity> queryMenuRightsByMid(Integer mid) {
        return menuRightsBiz.findByMid(mid);
    }

    @Override
    public void saveMenuRights(MenuRightsEntity menuRights) {
        menuRightsBiz.add(menuRights);
    }

    @Override
    public void updateMenuRights(MenuRightsEntity menuRight) {
        menuRightsBiz.update(menuRight);
    }

    @Override
    public void updateMenuRightsState(Integer id, String state) {
        menuRightsBiz.updateState(id, state);
    }
}

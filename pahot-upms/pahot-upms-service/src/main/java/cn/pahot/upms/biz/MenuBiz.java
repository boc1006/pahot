package cn.pahot.upms.biz;

import cn.pahot.upms.dao.MenuMapper;
import cn.pahot.upms.dao.MenuRightsMapper;
import cn.pahot.upms.entity.MenuEntity;
import cn.pahot.upms.entity.MenuRightsEntity;
import cn.pahot.upms.exception.MenuException;
import cn.pahot.upms.exception.MenuRightsException;
import com.boc.common.utils.DateUtils;
import com.boc.common.utils.MD5Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.util.List;
import java.util.Objects;

@Service("menuBiz")
public class MenuBiz {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuRightsMapper menuRightsMapper;

    /**
     * 根据id查询菜单详情
     *
     * @param id
     * @return
     */
    public MenuEntity findById(Integer id) {
        if (StringUtils.isEmpty(id))
            throw MenuException.PARAM_IS_NULL.newInstance("菜单ID");

        return menuMapper.selectById(id);
    }

    /**
     * 查找选中系统下的菜单列表(树形结构）
     *
     * @param sid
     * @return
     */
    public List<MenuEntity> findBySid(String sid) {
        if (StringUtils.isEmpty(sid))
            throw MenuException.PARAM_IS_NULL.newInstance("系统ID");

        return menuMapper.selectBySid(sid);
    }

    /**
     * 新增菜单
     *
     * @param menu
     */
    public void add(MenuEntity menu) {
        if (Objects.isNull(menu))
            throw MenuException.PARAM_IS_NULL.newInstance("菜单信息");
        this.validateParams(menu);

        String hashKey = this.generateHashKey(menu.getUri());
        if (!StringUtils.isEmpty(hashKey)) menu.setHashkey(hashKey);
        menu.setAa02(DateUtils.getCurrDateTimeToLong());
        menuMapper.insert(menu);
    }

    /**
     * 修改菜单
     *
     * @param menu
     */
    public void update(MenuEntity menu) {
        if (StringUtils.isEmpty(menu))
            throw MenuException.PARAM_IS_NULL.newInstance("菜单信息");
        Integer id = menu.getId();
        if (StringUtils.isEmpty(id))
            throw MenuException.PARAM_IS_NULL.newInstance("菜单ID");
        MenuEntity m = this.findById(id);
        if (Objects.isNull(m)) throw MenuException.INSTANCE.newInstance("要修改的菜单信息不存在！");
        String hashKey = this.generateHashKey(menu.getUri());
        if (!StringUtils.isEmpty(hashKey)) menu.setHashkey(hashKey);
        menu.setAb02(DateUtils.getCurrDateTimeToLong());
        menuMapper.update(menu);
        MenuEntity m2 = this.findById(id);
        //不等则更新
        if (!(m2.getUri() == null && m.getUri() == null)) {
            boolean isEquals = true;
            if (m2.getUri() != null)
                isEquals = m2.getUri().equals(m.getUri());
            else
                isEquals = m.getUri().equals(m2.getUri());
            if (!isEquals) {
                List<MenuRightsEntity> list = this.menuRightsMapper.selectByMid(id);
                for (MenuRightsEntity menuRightsEntity : list) {
                    String key = this.generateHashKey(m2, menuRightsEntity.getCode());
                    MenuRightsEntity updatemre = new MenuRightsEntity();
                    updatemre.setHashkey(key);
                    updatemre.setId(menuRightsEntity.getId());
                    menuRightsMapper.update(updatemre);
                }
            }
        }
    }

    private String generateHashKey(MenuEntity menu, String code) {
        return MD5Helper.encrypt(menu.getUri() + "!" + code);
    }


    /**
     * 启用或者禁用
     *
     * @param id
     */
    public void updateState(Integer id, String state) {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(state))
            throw MenuException.PARAM_IS_NULL.newInstance("菜单ID或者state");

        MenuEntity m = this.findById(id);
        if (Objects.isNull(m)) throw MenuException.INSTANCE.newInstance("要修改的菜单信息不存在！");

        menuMapper.updateState(id, state);
    }

    /**
     * 生成hashKey
     * 生成规则为：HASHCODE=new MD5().getMD5ofStr(url)
     *
     * @param url
     * @return
     */
    private String generateHashKey(String url) {
        return StringUtils.isEmpty(url) ? null : MD5Helper.encrypt(url);
    }

    /**
     * 新增数据校验
     *
     * @param menu
     */
    private void validateParams(MenuEntity menu) {
        if (StringUtils.isEmpty(menu.getSid())) throw MenuException.PARAM_IS_NULL.newInstance("系统编号");
        if (menu.getParentid() == null) throw MenuException.PARAM_IS_NULL.newInstance("上级菜单编号");
        if (StringUtils.isEmpty(menu.getName())) throw MenuException.PARAM_IS_NULL.newInstance("显示名称");
        if (StringUtils.isEmpty(menu.getMtype())) throw MenuException.PARAM_IS_NULL.newInstance("菜单类型");
        if (StringUtils.isEmpty(menu.getBtype())) throw MenuException.PARAM_IS_NULL.newInstance("业务类型");
        if (StringUtils.isEmpty(menu.getLevel())) throw MenuException.PARAM_IS_NULL.newInstance("菜单层级");
        if (menu.getAa01() == null) throw MenuException.PARAM_IS_NULL.newInstance("创建人");
        if (!StringUtils.isEmpty(menu.getState())) throw MenuException.INSTANCE.newInstance("可编辑内容不包括菜单权限状态！");
    }

}

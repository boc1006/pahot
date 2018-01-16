package cn.pahot.upms.biz;

import cn.pahot.upms.dao.MenuMapper;
import cn.pahot.upms.dao.MenuRightsMapper;
import cn.pahot.upms.entity.MenuEntity;
import cn.pahot.upms.entity.MenuRightsEntity;
import cn.pahot.upms.exception.MenuRightsException;
import com.boc.common.exception.BizException;
import com.boc.common.utils.DateUtils;
import com.boc.common.utils.MD5Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service("menuRightsBiz")
public class MenuRightsBiz {

    @Autowired
    private MenuRightsMapper menuRightsMapper;
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 根据id查询菜单权限详情
     *
     * @param id
     * @return
     */
    public MenuRightsEntity findById(Integer id) {
        if (StringUtils.isEmpty(id))
            throw MenuRightsException.PARAM_IS_NULL.newInstance("菜单权限ID");

        return menuRightsMapper.selectById(id);
    }

    /**
     * 查找选中菜单下的操作权限列表
     *
     * @param mid
     * @return
     */
    public List<MenuRightsEntity> findByMid(Integer mid) {
        if (StringUtils.isEmpty(mid))
            throw MenuRightsException.PARAM_IS_NULL.newInstance("菜单ID");

        return menuRightsMapper.selectByMid(mid);
    }

    /**
     * 新增操作权限
     *
     * @param menuRights
     */
    public void add(MenuRightsEntity menuRights) {
        if (Objects.isNull(menuRights))
            throw MenuRightsException.PARAM_IS_NULL.newInstance("菜单权限信息");
        this.validateParams(menuRights);

        String hashKey = this.generateHashKey(menuRights.getMid(), menuRights.getCode());
        if (null != hashKey) menuRights.setHashkey(hashKey);
        menuRights.setAa02(DateUtils.getCurrDateTimeToLong());
        menuRightsMapper.insert(menuRights);
    }

    /**
     * 修改操作权限
     *
     * @param menuRights
     */
    public void update(MenuRightsEntity menuRights) {
        if (Objects.isNull(menuRights))
            throw MenuRightsException.PARAM_IS_NULL.newInstance("菜单权限信息");

        MenuRightsEntity mr = this.findById(menuRights.getId());
        if (Objects.isNull(mr)) throw MenuRightsException.INSTANCE.newInstance("要修改的权限信息不存在！");

        String hashKey = this.generateHashKey(menuRights.getMid(), menuRights.getCode());
        if (null != hashKey) menuRights.setHashkey(hashKey);
        menuRights.setAb02(DateUtils.getCurrDateTimeToLong());
        menuRightsMapper.update(menuRights);
    }

    /**
     * 启用或者禁用
     *
     * @param id
     */
    public void updateState(Integer id, String state) {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(state))
            throw MenuRightsException.PARAM_IS_NULL.newInstance("菜单权限ID或者state");

        menuRightsMapper.updateState(id, state);
    }

    /**
     * 生成hashKey
     * 生成规则为：HASHCODE=new MD5().getMD5ofStr(url!code)
     *
     * @param mid
     * @param code
     * @return
     */
    private String generateHashKey(Integer mid, String code) {
        if (!StringUtils.isEmpty(mid) && !StringUtils.isEmpty(code)) {
            MenuEntity menu = menuMapper.selectById(mid);
            if (Objects.isNull(menu))
                throw MenuRightsException.PARAM_IS_NULL.newInstance("menu[" + mid + "]");
            return MD5Helper.encrypt(menu.getUri() + "!" + code);
        }
        return null;
    }

    /**
     * 新增数据校验
     *
     * @param menuRights
     */
    private void validateParams(MenuRightsEntity menuRights) {
        if (menuRights.getMid() == null) throw MenuRightsException.PARAM_IS_NULL.newInstance("菜单编号");
        if (StringUtils.isEmpty(menuRights.getSid())) throw MenuRightsException.PARAM_IS_NULL.newInstance("系统编号");
        if (StringUtils.isEmpty(menuRights.getCode())) throw MenuRightsException.PARAM_IS_NULL.newInstance("权限code");
        if (StringUtils.isEmpty(menuRights.getName())) throw MenuRightsException.PARAM_IS_NULL.newInstance("显示名称");
        if (menuRights.getAa01() == null) throw MenuRightsException.PARAM_IS_NULL.newInstance("创建人");
        if (!StringUtils.isEmpty(menuRights.getState())) throw MenuRightsException.INSTANCE.newInstance("可编辑内容不包括菜单权限状态！");
    }

}

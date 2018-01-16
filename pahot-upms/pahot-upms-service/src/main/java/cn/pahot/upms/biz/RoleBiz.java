package cn.pahot.upms.biz;

import cn.pahot.upms.consts.RoleStateEnum;
import cn.pahot.upms.dao.RoleMapper;
import cn.pahot.upms.entity.RoleEntity;
import cn.pahot.upms.entity.UserRoleEntity;
import cn.pahot.upms.exception.RoleException;
import cn.pahot.upms.exception.UserRoleException;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;
import com.boc.common.utils.DateUtils;
import com.boc.common.utils.RightsHelper;
import com.boc.common.utils.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 角色管理业务逻辑实现
 * <p>@Title: RoleBiz.java
 * <p>@Package cn.pahot.upms.biz
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com
 * <p>@date 2017年12月7日 上午10:58:07
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@Service("roleBiz")
public class RoleBiz {

    @Autowired
    private RoleMapper roleDao;

    /**
     * 根据系统编号查询指定系统下的角色列表
     *
     * @param sysId  系统编号
     * @param state  指定状态,状态定义 -> cn.pahot.upms.consts.RoleStateEnum
     * @param expire true:只查询有效期内角色,false:查询所有
     * @return
     */
    public List<RoleEntity> queryRolesBySystemId(String sysId, String state, boolean expire) {
        if (StringUtils.isEmpty(sysId) || StringUtils.isEmpty(state)) {
            throw RoleException.PARAM_IS_NULL.newInstance("queryRolesBySystemId:sysId|state");
        }
        DTO<String, String> param = new BaseDTO<String, String>();
        if (!state.equals(RoleStateEnum.USER_STATE_ALL.key)) {
            param.put("state", state);
        }
        if (expire) {
            param.put("validity", DateUtils.getCurrDateTime());
        }

        param.put("sid", sysId);

        return roleDao.queryRolesBySystemId(param);
    }

    /**
     * 新增角色
     *
     * @param roleEntity roleEntity.sid 系统编号
     *                   roleEntity.name 角色名称
     *                   roleEntity.validity 角色有效期,0表示长期有效,大于0表示指定有效期,格式:20171207110903
     *                   roleEntity.sort 排序
     *                   roleEntity.aa01 操作人ID
     * @return 角色编号
     * @throws BizException
     */
    public Integer saveRole(RoleEntity roleEntity) {
        if (roleEntity == null) {
            throw RoleException.PARAM_IS_NULL.newInstance("saveRole:roleEntity");
        }
        if (StringUtils.isEmpty(roleEntity.getSid()) ||
                StringUtils.isEmpty(roleEntity.getName()) ||
                roleEntity.getAa01() == 0) {
            throw RoleException.PARAM_IS_NULL.newInstance("saveRole:sid|name|aa01");
        }

        roleEntity.setAa02(DateUtils.getCurrDateTimeToLong());
        roleDao.saveRole(roleEntity);

        return roleEntity.getId();
    }

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
    public void updateRole(RoleEntity roleEntity) {
        if (roleEntity == null) {
            throw RoleException.PARAM_IS_NULL.newInstance("updateRole:roleEntity");
        }
        if (StringUtils.isEmpty(roleEntity.getId()) ||
                StringUtils.isEmpty(roleEntity.getName()) ||
                roleEntity.getAb01() == 0) {
            throw RoleException.PARAM_IS_NULL.newInstance("updateRole:id|name|ab01");
        }

        RoleEntity role = roleDao.queryRoleById(roleEntity.getId());
        if (role == null) {
            throw RoleException.PARAM_HINT_ERROR.newInstance("角色不存在:" + roleEntity.getId());
        }

        roleEntity.setState("");//防止注入
        roleEntity.setArights("");//防止注入
        roleEntity.setHrights("");//防止注入

        roleEntity.setAb02(DateUtils.getCurrDateTimeToLong());
        roleDao.updateRole(roleEntity);
    }

    /**
     * 变更角色状态
     *
     * @param param 传入SessionDTO<String,String> 对象
     *              key=roleId:角色编号
     *              key=state:变更后状态,状态定义 -> cn.pahot.upms.consts.RoleStateEnum
     */
    public void handleRoleState(DTO<String, String> param) {
        if (param == null) {
            throw RoleException.PARAM_IS_NULL.newInstance("handleRoleState:param");
        }
        Integer roleId = param.getAsInteger("roleId");
        String state = param.getAsString("state");
        Integer ab01 = param.getUserId();
        Long ab02 = param.getClientTime();
        if (roleId == 0 || StringUtils.isEmpty(state) || ab01 == 0) {
            throw RoleException.PARAM_IS_NULL.newInstance("handleRoleState:roleId|state|ab01");
        }

        RoleEntity role = roleDao.queryRoleById(roleId);
        if (role == null) {
            throw RoleException.PARAM_HINT_ERROR.newInstance("角色不存在:" + roleId);
        }

        if (role.getState().equals(state)) {
            throw RoleException.PARAM_HINT_WARN.newInstance("当前角色状态已处于变更状态!");
        }

        RoleEntity roleParam = new RoleEntity();
        roleParam.setId(roleId);
        roleParam.setState(state);
        roleParam.setAb01(ab01);
        roleParam.setAb02(ab02);

        roleDao.updateRole(roleParam);
    }

    /**
     * 根据角色ID查询角色信息
     *
     * @param roleId 角色id
     * @return
     */
    public RoleEntity queryRoleById(Integer roleId) {
        if (roleId == 0) {
            throw RoleException.PARAM_IS_NULL.newInstance("queryRoleById:roleId");
        }

        return roleDao.queryRoleById(roleId);
    }

    /**
     * 对角色进行授权
     *
     * @param param 传入SessionDTO<String,String> 对象
     *              key=roleId 角色编号
     *              key=aRights 菜单访问权限,多个菜单编号用","分隔
     *              key=hRights 菜单操作权限,多个菜单操作权限编号用","分隔
     * @throws BizException
     */
    public void roleByGrant(DTO<String, String> param) {
        if (param == null) {
            throw RoleException.PARAM_IS_NULL.newInstance("roleByGrant:param");
        }

        Integer roleId = param.getAsInteger("roleId");
        String aRights = param.getAsString("aRights");
        String hRights = param.getAsString("hRights");

        if (roleId == 0) {
            throw RoleException.PARAM_IS_NULL.newInstance("roleByGrant:roleId");
        }

        if (StringUtils.isEmpty(aRights) && !StringUtils.isEmpty(hRights)) {
            throw RoleException.PARAM_HINT_ERROR.newInstance("未授予任何访问权限,但授予了操作权限!");
        }

        RoleEntity role = roleDao.queryRoleById(roleId);
        if (role == null) {
            throw RoleException.PARAM_HINT_ERROR.newInstance("角色不存在:" + roleId);
        }

        RoleEntity roleParam = new RoleEntity();

        if (StringUtils.isEmpty(aRights)) {//清空角色权限
            roleParam.setId(roleId);
            roleParam.setHrights("0");
            roleParam.setArights("0");
            roleParam.setAb01(param.getUserId());
            roleParam.setAb02(param.getClientTime());
            roleDao.updateRole(roleParam);
            return;
        }


        String aRightsArr[];
        String hRightsArr[];

        try {
            aRightsArr = aRights.split(",");
            hRightsArr = hRights.split(",");
        } catch (Exception e) {
            throw RoleException.PARAM_HINT_ERROR.newInstance("授权的权限字符串格式不正确!");
        }
        try {
            roleParam.setId(roleId);

            if (StringUtils.isEmpty(hRights)) {
                roleParam.setHrights("0");
            } else {
                roleParam.setHrights(RightsHelper.sumRights(hRightsArr).toString());
            }
            roleParam.setArights(RightsHelper.sumRights(aRightsArr).toString());
            roleParam.setAb01(param.getUserId());
            roleParam.setAb02(param.getClientTime());
            roleDao.updateRole(roleParam);
        } catch (Exception e) {
            throw RoleException.PARAM_HINT_ERROR.newInstance("授权的权限字符串格式不正确!" + e.getMessage());
        }
    }


    /**
     * 更具子系统id和用户id查询用户角色
     *
     * @param sid 子系统id
     * @param uid 用户id
     * @param uid 角色id
     * @return
     */
    public List<UserRoleEntity> queryUserRole(Integer sid, Integer uid, Integer rid) {
        if (uid == null)
            throw UserRoleException.PARAM_IS_NULL.newInstance("queryUserRole:uid");
        DTO<String, Integer> param = new BaseDTO();
        param.put("sid", sid);
        param.put("uid", uid);
        param.put("rid", rid);
        return roleDao.queryUserRole(param);
    }

    /**
     * 新增角色
     *
     * @param userRoleEntity roleEntity.sid 系统编号
     *                       roleEntity.name 角色名称
     *                       roleEntity.validity 角色有效期,0表示长期有效,大于0表示指定有效期,格式:20171207110903
     *                       roleEntity.sort 排序
     *                       roleEntity.aa01 操作人ID
     * @return 角色编号
     * @throws BizException
     */
    public Integer saveUserRole(UserRoleEntity userRoleEntity) {
        if (userRoleEntity == null) {
            throw UserRoleException.PARAM_IS_NULL.newInstance("saveUserRole:userRoleEntity");
        }
        if (userRoleEntity.getSid() == null ||
                userRoleEntity.getUid() == null || userRoleEntity.getRid() == null ||
                userRoleEntity.getAa01() == null) {
            throw UserRoleException.PARAM_IS_NULL.newInstance("saveUserRole:sid|uid|rid|aa01");
        }
        userRoleEntity.setAa02(DateUtils.getCurrDateTimeToLong());
        DTO<String, Integer> dto = new BaseDTO<>();
        dto.put("sid", userRoleEntity.getSid());
        dto.put("uid", userRoleEntity.getUid());
        dto.put("rid", userRoleEntity.getRid());
        List<UserRoleEntity> list = this.roleDao.queryUserRole(dto);
        if (list.size() > 0) {
            throw UserRoleException.PARAM_HINT_ERROR.newInstance("用户已经拥有该角色的权限,请勿重复添加.");
        }
        roleDao.saveUserRole(userRoleEntity);
        return userRoleEntity.getId();
    }


    public void delUserRole(Integer id) {
        if (id == null)
            throw UserRoleException.PARAM_IS_NULL.newInstance("delUserRole:id");
        roleDao.delUserRole(id);
    }

    //   DateUtils.getCurrDateTimeToLong()
}

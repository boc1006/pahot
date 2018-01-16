package dubbo.test;

import cn.pahot.upms.entity.UserRoleEntity;
import cn.pahot.upms.facade.RoleFacade;
import com.google.common.base.Preconditions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dubbo-upms-consumer.xml")
public class UserRoleTest {
    @Autowired
    private RoleFacade roleFacade;


    @Test
    public void saveRole() {
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setUid(100002);
        userRole.setSid(100000);
        userRole.setRid(10001);
        userRole.setAa01(100002);
        int roleId = roleFacade.saveUserRole(userRole);
        System.out.println("新增角色编号=" + roleId);
    }

    @Test
    public void queryUserRole() {
        List<UserRoleEntity> urlist = roleFacade.queryUserRoleBySystemIdAndUserId(100000, 100002, null);
        System.out.println("新增角色编号=" + urlist);
    }


    @Test
    public void delUserRole() {
        roleFacade.delUserRole(10003);
     //   System.out.println("新增角色编号=");
    }




  /*  @Test
    public void queryRolesBySystemId() {
        List<RoleEntity> list = roleFacade.queryRolesBySystemId("100000", RoleStateEnum.USER_STATE_ALL.key, true);
        System.out.println(JSONObject.toJSONString(list));
    }

    @Test
    public void saveRole() {
        RoleEntity role = new RoleEntity();
        role.setSid("100000");
        role.setName("运营角色");
        role.setValidity(0l);
        role.setSort(1);
        role.setAa01(100000);
        int roleId = roleFacade.saveRole(role);
        System.out.println("新增角色编号=" + roleId);
    }

    @Test
    public void updateRole() {
        RoleEntity role = new RoleEntity();
        role.setId(10002);
        role.setName("运营角色-修改");
        role.setValidity(DateUtils.getCurrDateTimeToLong() + 10 * 60 * 1000);
        role.setSort(2);
        role.setAb01(100002);
        roleFacade.updateRole(role);
    }

    @Test
    public void handleRoleState() {
        DTO<String, String> param = new SessionDTO<String, String>();

        param.put("roleId", "10002");
        param.put("state", RoleStateEnum.ROLE_STATE_OFF.key);
        try {
            roleFacade.handleRoleState(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryRoleById() {
        RoleEntity role = roleFacade.queryRoleById(10002);
        System.out.println(JSONObject.toJSONString(role));
    }

    @Test
    public void roleByGrant() {
        DTO<String, String> param = new SessionDTO<String, String>();
        param.put("roleId", "10002");
        param.put("aRights", "1,2,3");
        param.put("hRights", "4,5,6");
        try {
            roleFacade.roleByGrant(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        String s = null;
        Preconditions.checkNotNull(s, "用户名不能为空");
    }
}

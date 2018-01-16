package dubbo.test;

import cn.pahot.upms.entity.OrganizationEntity;
import cn.pahot.upms.entity.UserEntity;
import cn.pahot.upms.facade.OrganizationFacade;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;
import com.boc.common.page.PageParams;
import com.github.pagehelper.PageInfo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.yaml.snakeyaml.tokens.Token;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dubbo-upms-consumer.xml")
public class OrganizationTest {
    @Autowired
    private OrganizationFacade organizationFacade;

    @org.junit.Test
    public void testAddOrg() {
        OrganizationEntity oe = new OrganizationEntity();
        oe.setAddress("四川成都");
        //  oe.setLevels("0001");
        oe.setAa01(100002);
        // oe.setAa02(Da);
        oe.setFax("fax");
        oe.setName("部门xx");
        oe.setParentid(10006);
        oe.setRemark("remark");
        oe.setTel("13308056290");
        oe.setSort((short) 1);
        //oe.setUid("0");
        //oe.set
        organizationFacade.addOrganization(oe);

    }

    @org.junit.Test
    public void testAddUser() {
        UserEntity oe = new UserEntity();
        oe.setName("hj1");
        oe.setOrgid(10006);
        oe.setBirthday("2011-01-01");
        oe.setPhone("13308056390");
        oe.setRemark("first preson");
        oe.setSex((short) 1);
        oe.setUsername("huagjie5");
        oe.setAa01(2);
        oe.setAa02(201712071425L);
        //oe.set
        organizationFacade.addUser(oe);

    }

    @org.junit.Test
    public void testupdUser() {
        UserEntity oe = new UserEntity();
        oe.setId(100008);
        oe.setName("");
        oe.setOrgid(10008);
        oe.setBirthday("2011-01-01");
        oe.setPhone("13308056390");
        oe.setRemark("second preson");
        oe.setSex((short) 1);
        oe.setUsername("huagjie");
        oe.setAb01(10);
        oe.setAb02(201712071433L);
        //oe.set
        organizationFacade.updateUser(oe);

    }

    @org.junit.Test
    public void testresetPwd() {
        UserEntity oe = new UserEntity();
        oe.setId(100007);
        oe.setAb01(11);
        oe.setAb02(201712071536L);
        //oe.set
        organizationFacade.resetPwd(oe);

    }


    @org.junit.Test
    public void testfreezeUser() {
        UserEntity oe = new UserEntity();
        oe.setId(100007);
        oe.setAb01(12);
        oe.setAb02(201712071544L);
        organizationFacade.freezeUser(oe);
    }

    @org.junit.Test
    public void testQuerOrg() {
        DTO param = new BaseDTO();
        List<OrganizationEntity> listOrg = organizationFacade.queryOrganization(param);
        System.out.println(listOrg);
    }

    @org.junit.Test
    public void testunFreezeUser() {
        UserEntity oe = new UserEntity();
        oe.setId(100007);
        oe.setAb01(13);
        oe.setAb02(201712071546L);
        organizationFacade.unFreezeUser(oe);
    }

    @org.junit.Test
    public void testUpdOrg() {  //更新upd
        OrganizationEntity oe = new OrganizationEntity();
        oe.setId(10028);
        //oe.setAddress("四川成都_u");
        //  oe.setLevels("0001_up");
        oe.setAb01(100002);
        // oe.setAb02(3l);
        oe.setFax("fax_up");
        oe.setAddress("四川眉山");
        oe.setName("测试部门_up");
        oe.setParentid(0);
        oe.setRemark("remark_up");
        oe.setTel("13308056291");
        oe.setSort((short) 1);
        //  oe.setUid("0");
        //oe.set
        organizationFacade.updOrganization(oe);

    }


    @org.junit.Test
    public void testStartOrg() {  //更新upd
        OrganizationEntity oe = new OrganizationEntity();
        oe.setId(10005);
        oe.setAb01(5);
        oe.setAb02(201712071153L);
        //  oe.setUid("0");
        //oe.set
        organizationFacade.startOrganization(oe);
    }

    @org.junit.Test
    public void testJinyongOrg() {  //更新upd
        OrganizationEntity oe = new OrganizationEntity();
        oe.setId(10006);
        oe.setAb01(5);
        oe.setAb02(201712071153L);
        //  oe.setUid("0");
        //oe.set
        organizationFacade.forbiddenOrganization(oe);
    }


    @org.junit.Test
    public void testDelOrg() {  //更新upd
        OrganizationEntity oe = new OrganizationEntity();
        oe.setId(10006);
        oe.setAb01(5);
        oe.setAb02(201712071153L);
        organizationFacade.delOrganization(oe);
    }

    @org.junit.Test
    public void testDelUser() {  //更新upd
        UserEntity oe = new UserEntity();
        oe.setId(100007);
        oe.setAb01(5);
        oe.setAb02(201712071428L);
        //  oe.setUid("0");
        //oe.set
        organizationFacade.cancelUser(oe);
    }


    @org.junit.Test
    public void updUserpwd() {  //更新upd
        organizationFacade.updUserPwd(100055, "000000", "xujiE1");
    }


    @org.junit.Test
    public void testFindUserByPage() {  //更新upd
        PageParams<DTO<String, String>> pageParams = new PageParams<>();
        pageParams.setPageNo(1);
        pageParams.setPageSize(3);
        DTO<String, String> dto = new BaseDTO<>();
       dto.put("orgId", "1");
        pageParams.setParams(dto);
        PageInfo<UserEntity> page = organizationFacade.findByUserListPage(pageParams);
        System.out.println(page);
    }
}

package dubbo.test;

import cn.pahot.upms.entity.SystemSettingEntity;
import cn.pahot.upms.facade.SystemSettingFacde;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yuzhiyun
 * @Date: 2017/12/11
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dubbo-upms-consumer.xml")
public class SystemSettingTest {

    @Autowired
    private SystemSettingFacde systemSettingFacde;

    private static final String TABLE_NAME = "pt_upms_system_setting";

    @Test
    public void queryById() throws Exception {
        SystemSettingEntity result = systemSettingFacde.queryById(10000);
        Assert.assertNotNull(result);
    }

    @Test
    public void queryBySid() throws Exception {
        List<SystemSettingEntity> result = systemSettingFacde.queryBySid("1");
        Assert.assertTrue(result.size() > 0);
    }

    @Test
    public void queryByKey() throws Exception {
        SystemSettingEntity result = systemSettingFacde.queryByKey("userN");
        Assert.assertNotNull(result);
    }

    @Test
    public void saveSystemSetting() throws Exception {
        SystemSettingEntity entity = new SystemSettingEntity();
        entity.setSid("1");
        entity.setSetKey("userN");
        entity.setSetValue("zhangsan");
        entity.setAa01(1);
        systemSettingFacde.saveSystemSetting(entity);
    }

    @Test
    public void updateSystemSetting() throws Exception {
        SystemSettingEntity entity = new SystemSettingEntity();
        entity.setId(10000);
        entity.setSetDesc("名字");
        systemSettingFacde.updateSystemSetting(entity);
    }

    @Test
    public void updateSystemSettingState() throws Exception {
        systemSettingFacde.updateSystemSettingState(10000, "00");
    }

    @Test
    public void loadSystemSettingToRedis() throws Exception {
        systemSettingFacde.loadSystemSettingToRedis();
    }

}
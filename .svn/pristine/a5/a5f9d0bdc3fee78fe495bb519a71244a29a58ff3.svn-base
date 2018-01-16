package dubbo.test;

import cn.pahot.upms.entity.DataIndexEntity;
import cn.pahot.upms.entity.SystemManagerEntity;
import com.alibaba.fastjson.JSON;
import com.boc.common.page.PageParams;
import com.boc.common.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.pahot.upms.facade.SystemFacade;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dubbo-upms-consumer.xml")
public class SystemTest {
    @Autowired
    private SystemFacade systemFacade;

    @org.junit.Test
    public void getSysList() {
        System.out.println(JSON.toJSONString(systemFacade.getSysList("")));
    }

    @org.junit.Test
    public void getSysItem() {
        System.out.println(JSON.toJSONString(systemFacade.getSysItem("100000")));
    }

    @org.junit.Test
    public void enableOrdisableSys() {

        SystemManagerEntity systemManagerEntity = new SystemManagerEntity();
        systemManagerEntity.setState("01");
        systemManagerEntity.setId("10002");
        systemManagerEntity.setAb01(100000);
        systemManagerEntity.setAb02(DateUtils.getCurrDateTimeToLong());
        systemFacade.enableOrdisableSys(systemManagerEntity);
    }

    @org.junit.Test
    public void addSysItem() {
        SystemManagerEntity systemManagerEntity = new SystemManagerEntity();

        systemManagerEntity.setId("10008");
        systemManagerEntity.setName("修改后的测试数据");
        systemManagerEntity.setAa01(100000);
        systemManagerEntity.setAa02(DateUtils.getCurrDateTimeToLong());
        systemManagerEntity.setType("01");
        systemManagerEntity.setUrl("http://admin.pahotest.cn/upms");
        systemFacade.addItemToSys(systemManagerEntity);
    }

    @org.junit.Test
    public void editSysItem() {
        SystemManagerEntity systemManagerEntity = new SystemManagerEntity();

        systemManagerEntity.setId("10008");
        systemManagerEntity.setName("修改后的测试数据");
        systemManagerEntity.setAb01(100000);
        systemManagerEntity.setAb02(DateUtils.getCurrDateTimeToLong());
        systemManagerEntity.setType("01");
        systemManagerEntity.setUrl("http://admin.pahotest.cn/upms");
        systemFacade.editSys(systemManagerEntity);
    }


    @org.junit.Test
    public void addDataIndexEntityItem() {
        DataIndexEntity dataIndexEntity = new DataIndexEntity();

        dataIndexEntity.setSid("10005");
        dataIndexEntity.setField("00001");
        dataIndexEntity.setFieldname("字段名称");
        dataIndexEntity.setCode("123456");
        dataIndexEntity.setCodedesc("对123456的描述");
        dataIndexEntity.setAa01(10000);
        dataIndexEntity.setAa02(DateUtils.getCurrDateTimeToLong());

        systemFacade.addDataIndexItem(dataIndexEntity);
    }

    @org.junit.Test
    public void getDataIndexEntityItem() {
        System.out.println(JSON.toJSONString(systemFacade.getDataIndexItem(10000L)));
    }


    @org.junit.Test
    public void enableOrDisableDataIndexItem() {

        DataIndexEntity dataIndexEntity = new DataIndexEntity();

        dataIndexEntity.setId(10000L);
        dataIndexEntity.setEnabled("01");
        dataIndexEntity.setAb01(10000);
        dataIndexEntity.setAb02(DateUtils.getCurrDateTimeToLong());
        systemFacade.enableOrDisableDataIndexItem(dataIndexEntity);
    }

    @org.junit.Test
    public void disableEditDataIndexItem() {
        DataIndexEntity dataIndexEntity = new DataIndexEntity();

        dataIndexEntity.setId(10000L);
        dataIndexEntity.setAb01(10000);
        dataIndexEntity.setAb02(DateUtils.getCurrDateTimeToLong());
        systemFacade.disableEditDataIndexItem(dataIndexEntity);
    }

    @Test
    public void getDataIndexList() {

        DataIndexEntity dataIndexEntity = new DataIndexEntity();

        dataIndexEntity.setSid("10005");
        dataIndexEntity.setField("10001");

        PageParams<DataIndexEntity> pageParams = new PageParams<DataIndexEntity>();

        pageParams.setPageNo(1);
        pageParams.setPageSize(3);
        pageParams.setParams(dataIndexEntity);

        System.out.println(systemFacade.getDataIndexList(pageParams));
    }


    @Test
    public void editDataIndexItem() {
        DataIndexEntity dataIndexEntity = new DataIndexEntity();

        dataIndexEntity.setId(10000L);
//        dataIndexEntity.setSid("10005");
//        dataIndexEntity.setField("10001");
//        dataIndexEntity.setFieldname("新的字段名称");
//        dataIndexEntity.setCode("123456");
//        dataIndexEntity.setCodedesc("对123456的描述");
//        dataIndexEntity.setAb01(10000);
//        dataIndexEntity.setSort(1);
        dataIndexEntity.setAb02(DateUtils.getCurrDateTimeToLong());

        systemFacade.editDataIndexItem(dataIndexEntity);
    }
}
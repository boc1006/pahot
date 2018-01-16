package dubbo.test;

import cn.pahot.business.entity.BusinessAccountEntity;
import cn.pahot.business.entity.BusinessApplayInfoEntity;
import cn.pahot.business.entity.BusinessApplayLogEntity;
import cn.pahot.business.facade.MerchantFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dubbo-business-consumer.xml")
public class MerchantTest {

    @Autowired
    private MerchantFacade merchantFacade;

    @Test
    public void addBusinessAccount() {
        BusinessApplayInfoEntity businessApplayInfoEntity = new BusinessApplayInfoEntity();

        businessApplayInfoEntity.setAddress("天府五街美年广场B座");
        businessApplayInfoEntity.setIdcard("513811111111111111");
        businessApplayInfoEntity.setAddress2("花漾锦江b座");
        businessApplayInfoEntity.setBiz_scope("小吃");
        businessApplayInfoEntity.setShop_desc("店铺描述");

        businessApplayInfoEntity.setLocated_type("00");
        businessApplayInfoEntity.setMerchant_name("浮游科技");
        businessApplayInfoEntity.setInfo("稳当稳当");
        businessApplayInfoEntity.setName("浮游小吃店");
        businessApplayInfoEntity.setPhone("13308056290");
        businessApplayInfoEntity.setUsername("username");
        businessApplayInfoEntity.setRemark("测试!");
        // businessApplayInfoEntity.set
        merchantFacade.businessSubInfo(businessApplayInfoEntity);
    }

    @Test
    public void auditBusinessInfo( )
    {
        BusinessApplayLogEntity  BusinessApplayLogEntity  = new  BusinessApplayLogEntity();
        BusinessApplayLogEntity.setMer_ask_id(944134765731254272l);
        BusinessApplayLogEntity.setExpiration("2017-12-12");
        BusinessApplayLogEntity.setRemark("审核通过");
        BusinessApplayLogEntity.setState("01");
        BusinessApplayLogEntity.setAa01(1);
        merchantFacade.addBusinessSubLogInfo(BusinessApplayLogEntity);
    }

}

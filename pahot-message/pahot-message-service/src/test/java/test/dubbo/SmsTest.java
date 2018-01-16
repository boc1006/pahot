package test.dubbo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import cn.pahot.message.consts.SmsChannelEnum;
import cn.pahot.message.facade.SmsFacade;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dubbo-msg-consumer.xml")
public class SmsTest {
    @Autowired
    private SmsFacade smsFacade;
    
    @Test
    public void sendSmsCode() {
    	JSONObject jo = new JSONObject();
    	jo.put("code", "102400");
    	jo.put("product", "耙货");
    	smsFacade.sendSmsCode("SMS_89150021", SmsChannelEnum.CHANNEL_ALIDY.key, "1000000000", "13458585957", jo.toJSONString(),300, "", "测试验证码", "192.168.3.220");
    }
    
    @Test
    public void verifyCode() {
    	boolean verify = smsFacade.verifyCode("13458585957", "102400");
    	System.out.println("=========>"+verify);
    }
    
    @Test
    public void sendBizSms() {
    	JSONObject jo = new JSONObject();
		jo.put("goodsName", "测试商品1");
		jo.put("supShopName", "供货商1");
		jo.put("salShopName", "销售商1");
		jo.put("num", "1");
		jo.put("money", "12.8");
		jo.put("orderCode", "10000000");
		jo.put("payDate", "2017-12-11");
		smsFacade.sendBizSms("SMS_114845010", SmsChannelEnum.CHANNEL_ALIDY.key, "100000", "13458585957", jo.toJSONString()+"sssssssss)", "", "测试短信2", 2,1);
    }
    
}

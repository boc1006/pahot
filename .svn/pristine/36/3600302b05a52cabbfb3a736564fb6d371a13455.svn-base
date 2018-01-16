package dubbo.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.boc.common.utils.DateUtil;

import cn.pahot.xa.entity.MessageSubEntity;
import cn.pahot.xa.facade.CapXaFacade;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dubbo-submsg-consumer.xml")
public class TestSubMessage {
	@Resource
	private CapXaFacade capXaFacade;

	@org.junit.Test
	public void saveSubMessage() {
		MessageSubEntity sub = new MessageSubEntity();
		sub.setId(2017092201);
		sub.setSubname("测试订阅消息");
		sub.setType("1");
		sub.setSource("001");
		sub.setQueuename("message.xa.queueName.test");
		sub.setDbid("upms");
		sub.setRemark("备注");
		sub.setAa01("50125302");
		sub.setAa11("黄杰");
		sub.setAa02(DateUtil.dateTimeToLongByNewDate());
		try {

			capXaFacade.saveSubMessage(sub);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@org.junit.Test
	public void updateSubMessage() {
		MessageSubEntity sub = new MessageSubEntity();
		sub.setId(2017092201);
		sub.setSubname("测试订阅消息2");
		sub.setType("1");
		sub.setSource("001");
		sub.setQueuename("message.xa.queueName.test,message.xa.queueName.test2");
		sub.setDbid("upms");
		sub.setRemark("备注1");
		sub.setAb01("501253021");
		sub.setAb11("黄杰1");
		sub.setAb02(DateUtil.dateTimeToLongByNewDate());
		try {

			capXaFacade.updateSubMessage(sub);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@org.junit.Test
	public void updateSubMessageStateForId() {
		MessageSubEntity sub = new MessageSubEntity();
		sub.setId(2017092201);
		sub.setState("1");
		sub.setAb01("501253021");
		sub.setAb11("黄杰1");
		sub.setAb02(DateUtil.dateTimeToLongByNewDate());
		try {

			capXaFacade.updateSubMessageStateForId(sub);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@org.junit.Test
	public void querySubMessage() {
		MessageSubEntity sub = new MessageSubEntity();
		sub.setSubname("订阅");
		// sub.setId(2017092201);
		// sub.setState("1");
		// sub.setAb01("501253021");
		// sub.setAb11("黄杰1");
		// sub.setAb02(DateUtil.dateTimeToLongByNewDate());
		try {

			List<MessageSubEntity> list = capXaFacade.querySubMessage(sub);
			for (MessageSubEntity s : list) {
				System.out.println(JSONObject.toJSON(s));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@org.junit.Test
	public void saveSubMessageWaitingConfirm() {
		try {

			long pid = capXaFacade.saveSubMessageWaitingConfirm(2017092201, "{'id':232,name:'zhangsan22'}", "test",
					"50125302", "huangjie");
			System.out.println("===============>pid=" + pid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@org.junit.Test
	public void saveAndSendSubMessage() {
		try {

			long pid = capXaFacade.saveAndSendSubMessage(2017092201, "{'id':24,name:'lisi'}", "test",
					"50125302", "huangjie");
			System.out.println("===============>pid=" + pid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

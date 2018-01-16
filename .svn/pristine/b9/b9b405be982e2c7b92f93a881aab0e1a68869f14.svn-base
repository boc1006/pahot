package mq.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;


public class TsSend {
	@Test
	public void testSend() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-activemq.xml");

		JmsTemplate notifyJmsTemplate = (JmsTemplate) context.getBean("notifyJmsTemplate");

		// for (int i = 0; i <= 10; i++) {
		//
		// NotifyRecord param = new NotifyRecord();
		// param.setNotifyTimes(0);
		// param.setLimitNotifyTimes(5);
		// param.setStatus(101);
		// param.setUrl("http://boss.xunshipay.com");
		// param.setMerchantOrderNo("abc60012010" + i);
		// param.setMerchantNo("1288888888");
		// param.setNotifyType(NotifyTypeEnum.MERCHANT.getValue());
		//
		// final String str = JSONObject.toJSONString(param);
		//
		// notifyJmsTemplate.send(new MessageCreator() {
		// public Message createMessage(Session session) throws JMSException {
		// return session.createTextMessage(str);
		// }
		// });
		// }

	}
}

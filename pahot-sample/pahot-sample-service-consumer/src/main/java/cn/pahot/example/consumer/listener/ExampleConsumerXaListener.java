package cn.pahot.example.consumer.listener;

import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;

import com.boc.common.core.activemq.listener.AbstractSessionAwareMessageListener;
import com.boc.common.enums.PropertiesHelper;

import cn.pahot.example.consumer.facade.SampleConsumerFacade;
import cn.pahot.xa.entity.MessageEntity;

/**
 * 测试消息消费者
 * <p>@Title: ExampleConsumerXaListener.java 
 * <p>@Package cn.pahot.example.consumer.listener 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月4日 下午12:01:38 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class ExampleConsumerXaListener extends AbstractSessionAwareMessageListener<ActiveMQTextMessage> {
	
	@Autowired
	private SampleConsumerFacade sampleConsumerFacade;

	@Override
	protected void doMessage(ActiveMQTextMessage message, Session session, MessageEntity messageEntity)
			throws JMSException {
		sampleConsumerFacade.sampleXAConsumer(messageEntity.getId(), messageEntity.getContent());
	}

	@Override
	protected String getHandlerSystemId() {
		return PropertiesHelper.SAMPLE_QUEUENAME_DEMO;
	}

}

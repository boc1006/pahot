package cn.pahot.logger.listener;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.boc.common.exception.BizException;

import cn.pahot.logger.entity.AccessLogEntity;
import cn.pahot.logger.facade.LoggerFacade;
import cn.pahot.xa.entity.MessageEntity;

/**
 * 处理消息队列中的系统访问日志
 * <p>@Title: LoggerListener.java 
 * <p>@Package cn.pahot.logger.listener 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月23日 下午2:32:38 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class LoggerAccessMessageListener implements SessionAwareMessageListener<Message> {
	private static final Log log = LogFactory.getLog(LoggerAccessMessageListener.class);
	
	@Resource
	private LoggerFacade loggerFacade;

	@Override
	public void onMessage(Message message, Session arg1) throws JMSException {
		try {
			ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
			MessageEntity me = JSONObject.parseObject(msg.getText(),MessageEntity.class);
			AccessLogEntity se = JSONObject.parseObject(me.getContent(), AccessLogEntity.class);
			if (StringUtils.isEmpty(se)) {
				return;
			}
			loggerFacade.insertAccessLog(se);
		} catch (BizException e) {
			e.printStackTrace();
			// 业务异常，不再写会队列
			log.error("==>BizException", e);
		} catch (Exception e) {
			// 不明异常不再写会队列
			log.error("==>Exception", e);
		}
	}
}

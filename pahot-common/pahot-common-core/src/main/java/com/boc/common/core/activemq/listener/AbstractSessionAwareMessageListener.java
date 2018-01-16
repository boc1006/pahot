package com.boc.common.core.activemq.listener;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSONObject;
import com.boc.common.exception.BizException;
import com.boc.common.exception.BizSystemException;

import cn.pahot.xa.entity.ExecutionLogger;
import cn.pahot.xa.entity.MessageEntity;
import cn.pahot.xa.facade.CapXaFacade;

/**
 * 所有分布式消息消费者需要继承至该类
 * <p>@Title: AbstractSessionAwareMessageListener.java 
 * <p>@Package com.boc.common.core.activemq.listener 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月4日 上午11:57:29 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public abstract class AbstractSessionAwareMessageListener<M extends ActiveMQTextMessage> implements SessionAwareMessageListener<M> {

    private static final Log log = LogFactory.getLog(AbstractSessionAwareMessageListener.class);

    @Resource
    protected CapXaFacade capXaFacade;


    public final void onMessage(M message, Session session) throws JMSException {
        MessageEntity entity = JSONObject.parseObject(message.getText(), MessageEntity.class);
        try {

            if (StringUtils.isEmpty(getHandlerSystemId())) {
                throw new NullPointerException("调用处理系统标识不能为空");
            }

            String loggerMessage = new StringBuilder(200).append("[XA事物管理平台]-[").append(getHandlerSystemId()).append("]-第").append(entity.getRepeatcount() + 1).append("次执行消息ID[").append(entity.getId()).append("]的业务").toString();
            capXaFacade.recordExecutionLog(new ExecutionLogger(entity.getId(), getHandlerSystemId(), loggerMessage, "1"));
            log.info(loggerMessage);

            /* 调用具体业务处理 */
            doMessage(message, session, entity);

            /* 无异常表示处理成功，删除该消息 */
            capXaFacade.deleteMessageByMessageId(String.valueOf(entity.getId()));

            loggerMessage = new StringBuilder(200).append("[XA事物管理平台]-[").append(getHandlerSystemId()).append("]-消息ID[").append(entity.getId()).append("]处理成功").toString();
            capXaFacade.recordExecutionLog(new ExecutionLogger(entity.getId(), getHandlerSystemId(), loggerMessage, "2"));
            log.info(loggerMessage);
        } catch (Exception e) {
            // 不明异常不再写会队列
            String loggerMessage = new StringBuilder(200).append("[XA事物管理平台]-[").append(getHandlerSystemId()).append("]-第").append(entity.getRepeatcount() + 1).append("次执行消息ID[").append(entity.getId()).append("]的业务失败").toString();
            log.error(loggerMessage, e);

            if (e instanceof BizException) {
                loggerMessage = new StringBuilder(2000).append(loggerMessage).append(":\n").append("【").append(((BizException) e).getCode()).append("】").append(((BizException) e).getMsg()).append(":\n").append(e.getLocalizedMessage()).toString();
            } else if (e instanceof BizSystemException) {
                loggerMessage = new StringBuilder(2000).append(loggerMessage).append(":\n").append("【").append(((BizSystemException) e).getCode()).append("】").append(((BizSystemException) e).getMsg()).append(":\n").append(e.getLocalizedMessage()).toString();
            } else if (e instanceof RpcException) {
                loggerMessage = new StringBuilder(2000).append(loggerMessage).append(":\n").append(e.getLocalizedMessage()).toString();
            } else {
                loggerMessage = new StringBuilder(2000).append(loggerMessage).append(":\n").append(e.getLocalizedMessage()).toString();
            }
            capXaFacade.recordExecutionLog(new ExecutionLogger(entity.getId(), getHandlerSystemId(), loggerMessage, "3"));
        }
    }

    /**
     * 业务的具体实现逻辑
     *
     * @param message       jms message对象
     * @param session       jms session对象
     * @param messageEntity 业务消息体信息
     * @throws JMSException
     */
    protected abstract void doMessage(M message, Session session, MessageEntity messageEntity) throws JMSException;

    /**
     * 对应的业务处理系统编号
     *
     * @return
     */
    protected abstract String getHandlerSystemId();
}
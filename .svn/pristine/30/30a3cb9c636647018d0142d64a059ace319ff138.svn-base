package cn.pahot.xa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.page.PageParams;
import com.github.pagehelper.PageInfo;

import cn.pahot.xa.biz.CapXaBiz;
import cn.pahot.xa.entity.ExecutionLogger;
import cn.pahot.xa.entity.MessageEntity;
import cn.pahot.xa.entity.MessagePubEntity;
import cn.pahot.xa.entity.MessageSubEntity;
import cn.pahot.xa.facade.CapXaFacade;

/**
 * 分布式事务最终一致性服务接口实现
 * <p>@Title CapXaFacadeImpl</p>
 * <p>@Description com.dgg.message.xa.service.impl</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2017年2月16日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
@Service("capXaFacade")
public class CapXaFacadeImpl implements CapXaFacade {
	@Autowired
	private CapXaBiz capXaBiz;

	@Override
	public long saveMessageWaitingConfirm(MessageEntity messageEntity) throws BizException {
		return capXaBiz.saveMessageWaitingConfirm(messageEntity);
	}

	@Override
	public void confirmAndSendMessage(String messageId) throws BizException {
		capXaBiz.confirmAndSendMessage(messageId);
	}

	@Override
	public int saveAndSendMessage(MessageEntity messageEntity) throws BizException {
		return capXaBiz.saveAndSendMessage(messageEntity);
	}

	@Override
	public void directSendMessage(MessageEntity messageEntity) throws BizException {
		capXaBiz.directSendMessage(messageEntity);
	}

	@Override
	public void reSendMessage(MessageEntity messageEntity) throws BizException {
		capXaBiz.reSendMessage(messageEntity);
	}

	@Override
	public void reSendMessageByMessageId(String messageId) throws BizException {
		capXaBiz.reSendMessageByMessageId(messageId);
	}

	@Override
	public void setMessageToAreadlyDead(String messageId) throws BizException {
		capXaBiz.setMessageToAreadlyDead(messageId);
	}

	@Override
	public MessageEntity getMessageByMessageId(String messageId) throws BizException {
		return capXaBiz.getMessageByMessageId(messageId);
	}

	@Override
	public void deleteMessageByMessageId(String messageId) throws BizException {
		capXaBiz.deleteMessageByMessageId(messageId);
	}

	@Override
	public void reSendAllDeadMessageByQueueName(String queueName, int batchSize) throws BizException {
		capXaBiz.reSendAllDeadMessageByQueueName(queueName, batchSize);
	}
	
	@Override
	public List<MessageEntity> getMessageList(DTO<String,String> param) {
		return capXaBiz.getMessageList(param);
	}

	public void recordExecutionLog(ExecutionLogger logger) {
		capXaBiz.recordExecutionLog(logger);
	}

	@Override
	public PageInfo<MessageEntity> listPage(PageParams<DTO<String,String>> pageParam) throws BizException {
		
		return capXaBiz.listPage(pageParam);
	}

	@Override
	public void confirmAndSendMessageForList(List<String> messageIds) throws BizException {
		capXaBiz.confirmAndSendMessageForList(messageIds);
	}

	@Override
	public long saveSubMessageWaitingConfirm(int subId,String content,String remark,String aa01,String aa11) throws BizException {
		return capXaBiz.saveSubMessageWaitingConfirm(subId, content, remark, aa01, aa11);
	}

	@Override
	public void confirmAndSendSubMessage(long pubId) throws BizException {
		capXaBiz.confirmAndSendSubMessage(pubId);
	}

	@Override
	public long saveAndSendSubMessage(int subId,String content,String remark,String aa01,String aa11) throws BizException {
		return capXaBiz.saveAndSendSubMessage(subId, content, remark, aa01, aa11);
	}

	@Override
	public List<MessagePubEntity> getPubMessageList(long aa02) {
		return capXaBiz.getPubMessageList(aa02);
	}

	@Override
	public void deleteMessageByPubMessageId(long pid) throws BizException {
		capXaBiz.deleteMessageByPubMessageId(pid);
	}

	@Override
	public void saveSubMessage(MessageSubEntity sub) {
		capXaBiz.saveSubMessage(sub);
	}

	@Override
	public void updateSubMessage(MessageSubEntity sub) {
		capXaBiz.updateSubMessage(sub);
	}

	@Override
	public void updateSubMessageStateForId(MessageSubEntity sub) {
		capXaBiz.updateSubMessageStateForId(sub);
	}

	@Override
	public List<MessageSubEntity> querySubMessage(MessageSubEntity sub) {
		return capXaBiz.querySubMessage(sub);
	}

	@Override
	public DTO<String,String> findMessageSubById(Integer id) throws BizException {
		return capXaBiz.findMessageSubById(id);
	}

	@Override
	public void sendConfirmedAndDelaydMessage() throws BizException {
		capXaBiz.sendConfirmedAndDelaydMessage();
	}
}

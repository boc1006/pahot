package cn.pahot.xa.facade;

import java.util.List;

import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.page.PageParams;
import com.github.pagehelper.PageInfo;

import cn.pahot.xa.entity.ExecutionLogger;
import cn.pahot.xa.entity.MessageEntity;
import cn.pahot.xa.entity.MessagePubEntity;
import cn.pahot.xa.entity.MessageSubEntity;

/**
 * 分布式事务最终一致性处理接口
 * <p>@Title CapXaFacade</p>
 * <p>@Description com.dgg.message.xa.service</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2017年2月16日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public interface CapXaFacade {

	/**
	 * 保存待确认消息
	 * @param MessageEntity
	 * @return
	 * @throws BizException
	 */
	long saveMessageWaitingConfirm(MessageEntity messageEntity) throws BizException;
	
	/**
	 * 保存待确认订阅消息
	 * @param subId 消息订阅编号
	 * @param content 消息主体
	 * @param remark 消息备注
	 * @param aa01 消息创建人编号
	 * @param aa11 消息创建人姓名
	 * @return 消息发布编号
	 * @throws BizException
	 */
	long saveSubMessageWaitingConfirm(int subId,String content,String remark,String aa01,String aa11) throws BizException;

	/**
	 * 确认并发送消息.
	 * @param messageId
	 * @throws BizException
	 */
	public void confirmAndSendMessage(String messageId) throws BizException;
	
	/**
	 * 确认并发送订阅消息.
	 * @param pubId 发布消息编号
	 * @throws BizException
	 */
	public void confirmAndSendSubMessage(long pubId) throws BizException;
	
	/**
	 * 确认并发送消息（批量）.
	 * @param messageId
	 * @throws BizException
	 */
	public void confirmAndSendMessageForList(List<String> messageIds) throws BizException;

	/**
	 * 保存并发送消息.
	 * @param MessageEntity
	 * @return
	 * @throws BizException
	 */
	public int saveAndSendMessage(MessageEntity messageEntity) throws BizException;
	
	/**
	 * 保存并发送订阅消息.
	 * @param subId 消息订阅编号
	 * @param content 消息主体
	 * @param remark 消息备注
	 * @param aa01 消息创建人编号
	 * @param aa11 消息创建人姓名
	 * @return 消息发布编号
	 * @throws BizException
	 */
	public long saveAndSendSubMessage(int subId,String content,String remark,String aa01,String aa11) throws BizException;

	/**
	 * 直接发送消息.
	 * @param MessageEntity
	 * @throws BizException
	 */
	public void directSendMessage(MessageEntity messageEntity) throws BizException;

	/**
	 * 重发消息.
	 */
	public void reSendMessage(MessageEntity messageEntity) throws BizException;

	/**
	 * 根据messageId重发某条消息.
	 */
	public void reSendMessageByMessageId(String messageId) throws BizException;

	/**
	 * 将消息标记为死亡消息.
	 */
	public void setMessageToAreadlyDead(String messageId) throws BizException;

	/**
	 * 根据消息ID获取消息
	 */
	public MessageEntity getMessageByMessageId(String messageId) throws BizException;

	/**
	 * 根据消息ID删除消息
	 */
	public void deleteMessageByMessageId(String messageId) throws BizException;
	
	/**
	 * 根据消息ID删除订阅消息
	 */
	public void deleteMessageByPubMessageId(long pid) throws BizException;

	/**
	 * 重发某个消息队列中的全部已死亡的消息.
	 */
	public void reSendAllDeadMessageByQueueName(String queueName, int batchSize) throws BizException;
	
	/**
	 * 获取消息队列中的分布列表
	 * @param pageParam
	 * @return
	 * @throws BizException
	 */
	public PageInfo<MessageEntity> listPage(PageParams<DTO<String,String>> pageParam) throws BizException;
	
	/**
	 * 获取指定条件下的所有消息
	 * @param param
	 * @return
	 */
	public List<MessageEntity> getMessageList(DTO<String,String> param) throws BizException;
	
	/**
	 * 获取指定时间范围内的待确认订阅消息
	 * @param aa02
	 * @return
	 */
	public List<MessagePubEntity> getPubMessageList(long aa02) throws BizException;

	/**
	 * 记录消息执行日志
	 * @param logger
	 */
	void recordExecutionLog(ExecutionLogger logger) throws BizException;
	
	/**
	 * 保存订阅消息
	 * @param sub
	 * int id, 订阅消息编号-必传
	 * String subname, 订阅消息名称-必传
	 * String type,消息类型：1-json,2-xml 必传
	 * String source,消息来源-必传
	 * String queuename,队列名称，多个用','分隔-必传
	 * String dbid,资源编号-必传
	 * String remark,备注-非必传
	 * String aa01,创建人编号
	 * String aa11,创建人姓名
	 */
	void saveSubMessage(MessageSubEntity sub) throws BizException;
	
	/**
	 * 修改订阅消息 
	 * @param sub
	 * int id, 订阅消息编号-必传
	 * String subname, 订阅消息名称-必传
	 * String type,消息类型：1-json,2-xml 必传
	 * String source,消息来源-必传
	 * String queuename,队列名称，多个用','分隔-必传
	 * String dbid,资源编号-必传
	 * String remark,备注-非必传
	 * String ab01,修改人编号
	 * String ab11,修改人姓名
	 */
	void updateSubMessage(MessageSubEntity sub) throws BizException;
	
	/**
	 * 修改订阅消息状态
	 * @param sub
	 * int id, 订阅消息编号-必传
	 * String state,状态,1正常,2停用,0删除-必传
	 * String ab01,修改人编号
	 * String ab11,修改人姓名
	 */
	void updateSubMessageStateForId(MessageSubEntity sub) throws BizException;
	
	/**
	 * 检索订阅消息
	 * @param sub
	 * int id, 订阅消息编号-必传
	 * String subname, 订阅消息名称-必传
	 * String dbid,资源编号-必传
	 * String state,状态,1正常,2停用,0删除-必传
	 * @return
	 */
	List<MessageSubEntity> querySubMessage(MessageSubEntity sub) throws BizException;

	/**
	 * 根据订阅编号查询一条订阅记录
	 * @param id
	 * @return
	 * @throws BizException
     */
	DTO<String,String> findMessageSubById(Integer id) throws BizException;

	/**
	 * 批量发送处理已确认状态且已达到延迟发送时间的消息
	 * @throws BizException
	 */
	void sendConfirmedAndDelaydMessage() throws BizException;
}
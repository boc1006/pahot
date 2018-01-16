package cn.pahot.xa.dao;

import java.util.List;

import com.boc.common.core.dao.BaseDao;
import com.boc.common.metatype.DTO;
import com.github.pagehelper.Page;

import cn.pahot.xa.entity.ExecutionLogger;
import cn.pahot.xa.entity.MessageEntity;
import cn.pahot.xa.entity.MessagePubEntity;
import cn.pahot.xa.entity.MessageSubEntity;

/**
 * 分布式事务最终一致性DAO
 * <p>@Title CapXaMapper</p>
 * <p>@Description com.dgg.message.xa.dao</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2017年2月16日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public interface CapXaMapper<T> extends BaseDao<T> {
	
	/**
	 * 保存消息为待确认状态
	 * @param param
	 * @return
	 */
	int saveMessage(T param);
	
	/**
	 * 根据条件查询消息列表
	 * @param param
	 * @return
	 */
	List<T> queryMessageByList(DTO<String,String> param);
	
	/**
	 * 根据查询条件查询分页消息列表
	 * @param page
	 * @return
	 */
	Page<T> queryMessagelistPage(DTO<String,String> page);
	
	/**
	 * 根据条件查询单条消息
	 * @param param
	 * @return
	 */
	T queryMessageByDTO(DTO<String,String> param);
	
	/**
	 * 修改消息信息
	 * @param param
	 * @return
	 */
	int modifyMessageByCondition(T param);

	/**
	 * 将消息信息转存到历史记录表
	 * @param param
	 * @return
	 */
	int saveMessageToHistory(T param);
	
	/**
	 * 根据消息ID删除消息内容
	 * @param param
	 * @return
	 */
	int deleteMessageById(T param);
	
	/**
	 * 根据消息ID删除订阅消息
	 * @param pid
	 */
	void deleteMessageByPubMessageId(long pid);

	/**
	 * 添加执行日志
	 * @param logger
	 * @return
	 */
	int recordExecutionLog(ExecutionLogger logger);
	
	/**
	 * 根据订阅编号查询订阅信息
	 * @param subid
	 * @return
	 */
	MessageSubEntity getSubMessageForSubid(int subid);
	
	/**
	 * 批量插入发布消息
	 * @param list
	 */
	void insertPubForBatch(List<MessagePubEntity> list);
	
	/**
	 * 批量插入事务消息
	 * @param list
	 */
	void insertMsgForBatch(List<MessageEntity> list);
	
	/**
	 * 根据发布消息编号查询消息列表
	 * @param pid
	 * @return
	 */
	List<MessagePubEntity> queryPubMessageForPid(long pid);
	
	/**
	 * 取待确认的订阅发布消息ID
	 * @param aa02
	 * @return
	 */
	List<MessagePubEntity> queryPubMessageForConfirming(long aa02);
	
	void saveSubMessage(MessageSubEntity sub);
	
	void updateSubMessage(MessageSubEntity sub);
	
	void updateSubMessageStateForId(MessageSubEntity sub);
	
	List<MessageSubEntity> querySubMessage(MessageSubEntity sub);

	DTO<String,String> selectOneMessageSub(Integer id);
}

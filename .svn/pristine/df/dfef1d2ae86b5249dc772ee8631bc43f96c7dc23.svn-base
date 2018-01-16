package com.boc.common.core.dao.ack;

/**
 * 分布式事务-主事务保存接口
 * <p>@Title MainTransactionACKDao</p>
 * <p>@Description com.boc.common.core.ack.dao</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2017年7月4日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public interface MainTransactionACKDao {
	
	/**
	 * 保存主事务提交记录
	 * @param messageId
	 */
	void saveMainTransactionACKDao(long messageId);
	
	/**
	 * 查询消费者消息消费幂等性
	 * @param messageId
	 * @return
	 */
	int countByIdempotents(long messageId);
	
	/**
	 * 向业务幂等性表中插入MessageId
	 * @param message
	 */
	void saveByIdempotents(long message);
}

package cn.pahot.example.consumer.facade;

import com.boc.common.exception.BizException;

/**
 * 分布式事务消息消费者事例接口
 * <p>@Title: SampleConsumerFacade.java 
 * <p>@Package cn.pahot.example.consumer.facade 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月4日 上午11:24:31 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface SampleConsumerFacade {
	
	/**
	 * 测试分布式消息消费
	 * @throws BizException
	 */
	void sampleXAConsumer(long messageId,String userJson) throws BizException;
}

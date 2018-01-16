package cn.pahot.example.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boc.common.exception.BizException;

import cn.pahot.example.consumer.biz.SampleConsumerBiz;
import cn.pahot.example.consumer.facade.SampleConsumerFacade;

/**
 * 分布式事务消息消费者事例服务实现
 * <p>@Title: SampleConsumerService.java 
 * <p>@Package cn.pahot.example.consumer.service 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月4日 上午11:27:15 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@Service("sampleConsumerFacade")
public class SampleConsumerService implements SampleConsumerFacade {
	
	@Autowired
	private SampleConsumerBiz sampleConsumerBiz;

	@Override
	public void sampleXAConsumer(long messageId,String userJson) throws BizException {
		sampleConsumerBiz.sampleXAConsumer(messageId, userJson);
	}

}

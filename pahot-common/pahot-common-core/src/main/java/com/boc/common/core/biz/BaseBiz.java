package com.boc.common.core.biz;

import cn.pahot.logger.entity.BizLogEntity;
import cn.pahot.xa.entity.MessageEntity;
import cn.pahot.xa.enums.MessageTypeEnum;
import cn.pahot.xa.facade.CapXaFacade;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.boc.common.core.dao.ack.MainTransactionACKDao;
import com.boc.common.enums.PropertiesHelper;
import com.boc.common.exception.BizException;
import com.boc.common.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 服务层业务逻辑处理基类
 * <p>@Title pahot2.0
 * <p>@Author boc
 * <p>@Date 2017/12/25
 * <p>@Email 87080234@gmail.com
 * <p>@Department XXX部门
 * <p>@Version V1.0
 * <p>@Copyright © boc Inc. All rights reserved.
 */
public abstract class BaseBiz {

	protected final static Logger logger = Logger.getLogger(BaseBiz.class);

	@Autowired
	protected CapXaFacade capXaFacade;
	//使用redis
	@Autowired
	protected StringRedisTemplate stringRedisTemplate;
	//使用redis
	@Autowired
	protected RedisTemplate redisTemplate;

	@Autowired
	protected MainTransactionACKDao mainTransactionACKDao;

	/**
	 * 写入一个新增业务日志
	 * @param systemId 系统编号
	 * @param afterData 写入数据
	 * @param aa01 操作人ID
	 * @param remark 业务描述
	 */
	protected void bizLogForInsert(String systemId,String afterData,int aa01,String remark) {
		StackTraceElement[] temp = Thread.currentThread().getStackTrace();
		StackTraceElement invokeMethod = (StackTraceElement)temp[2];
		MessageEntity entity = new MessageEntity();
		entity.setQueuename(PropertiesHelper.LOGGER_QUEUENAME_BIZ);
		BizLogEntity bizEntity = new BizLogEntity();
		bizEntity.setSid(systemId);
		bizEntity.setMethod_name(invokeMethod.getClassName()+"."+invokeMethod.getMethodName());
		bizEntity.setBef_data("{}");//修改前数据,如果是新增,则传{},为了更全面记录日志,最好把修改的整条数据写入进去
		bizEntity.setAft_data(afterData);//修改后数据,为了更全面记录日志,最好把修改的整条数据写入进去
		bizEntity.setHandler(BizLogEntity.ADD);
		bizEntity.setRemark(remark);//业务操作描述,尽量清楚描述业务操作的功能
		bizEntity.setAa01(aa01);//操作人编号
		bizEntity.setAa02(DateUtils.getCurrDateTimeToLong());//操作时间
		entity.setContent(JSONObject.toJSONString(bizEntity));
		//为了保证日志记录保存失败导致业务不能正常执行,最好在try-catch块中调用业务日志记录接口
		try {
			capXaFacade.directSendMessage(entity);
		} catch (BizException e) {
			logger.error(e.getLocalizedMessage());//需要打印异常日志,以更好的查找异常日志原因
		}
	}

	/**
	 * 写入一个更新操作业务日志
	 * @param systemId 业务系统编号
	 * @param befData 更新前数据
	 * @param afterData 更新后数据
	 * @param aa01 操作人编号
	 * @param remark 业务描述
	 */
	protected void bizLogForUpdate(String systemId,String befData,String afterData,int aa01,String remark) {
		StackTraceElement[] temp = Thread.currentThread().getStackTrace();
		StackTraceElement invokeMethod = (StackTraceElement)temp[2];
		MessageEntity entity = new MessageEntity();
		entity.setQueuename(PropertiesHelper.LOGGER_QUEUENAME_BIZ);
		BizLogEntity bizEntity = new BizLogEntity();
		bizEntity.setSid(systemId);
		bizEntity.setMethod_name(invokeMethod.getClassName()+"."+invokeMethod.getMethodName());
		bizEntity.setBef_data(befData);//修改前数据,如果是新增,则传{},为了更全面记录日志,最好把修改的整条数据写入进去
		bizEntity.setAft_data(afterData);//修改后数据,为了更全面记录日志,最好把修改的整条数据写入进去
		bizEntity.setHandler(BizLogEntity.UPDATE);
		bizEntity.setRemark(remark);//业务操作描述,尽量清楚描述业务操作的功能
		bizEntity.setAa01(aa01);//操作人编号
		bizEntity.setAa02(DateUtils.getCurrDateTimeToLong());//操作时间
		entity.setContent(JSONObject.toJSONString(bizEntity));
		//为了保证日志记录保存失败导致业务不能正常执行,最好在try-catch块中调用业务日志记录接口
		try {
			capXaFacade.directSendMessage(entity);
		} catch (BizException e) {
			logger.error(e.getLocalizedMessage());//需要打印异常日志,以更好的查找异常日志原因
		}
	}

	/**
	 * 写入一个删除操作的业务日志
	 * @param systemId 业务系统编号
	 * @param befData 被删除的数据
	 * @param aa01 操作人编号
	 * @param remark 业务描述
	 */
	protected void bizLogForDelete(String systemId,String befData,int aa01,String remark) {
		StackTraceElement[] temp = Thread.currentThread().getStackTrace();
		StackTraceElement invokeMethod = (StackTraceElement)temp[2];
		MessageEntity entity = new MessageEntity();
		entity.setQueuename(PropertiesHelper.LOGGER_QUEUENAME_BIZ);
		BizLogEntity bizEntity = new BizLogEntity();
		bizEntity.setSid(systemId);
		bizEntity.setMethod_name(invokeMethod.getClassName()+"."+invokeMethod.getMethodName());
		bizEntity.setBef_data(befData);//修改前数据,如果是新增,则传{},为了更全面记录日志,最好把修改的整条数据写入进去
		bizEntity.setAft_data("{}");//修改后数据,为了更全面记录日志,最好把修改的整条数据写入进去
		bizEntity.setHandler(BizLogEntity.DELETE);
		bizEntity.setRemark(remark);//业务操作描述,尽量清楚描述业务操作的功能
		bizEntity.setAa01(aa01);//操作人编号
		bizEntity.setAa02(DateUtils.getCurrDateTimeToLong());//操作时间
		entity.setContent(JSONObject.toJSONString(bizEntity));
		//为了保证日志记录保存失败导致业务不能正常执行,最好在try-catch块中调用业务日志记录接口
		try {
			capXaFacade.directSendMessage(entity);
		} catch (BizException e) {
			logger.error(e.getLocalizedMessage());//需要打印异常日志,以更好的查找异常日志原因
		}
	}

	/**
	 * 业务中涉及到分布式事务的逻辑处理
	 * @param queueName 队列名称
	 * @param systemId 系统编号
	 * @param content 消息内容
	 * @param aa01 操作人编号
	 * @param remark 业务描述
	 * @param handler 业务处理
	 * @return 事务消息编号
	 */
	protected long distributedTransactional(String queueName,String systemId,String content,int aa01,String remark,IBussiness handler){
		//构建一个MessageEntity
		MessageEntity me = new MessageEntity();
		me.setType(MessageTypeEnum.JSON.key);
		me.setQueuename(queueName);
		me.setAa01(String.valueOf(aa01));
		me.setAa02(DateUtils.getCurrDateTimeToLong());
		me.setSource(systemId);//业务系统编号
		me.setRemark(remark);//可对该业务进行描述
		me.setContent(content);//设置消息内容体,该内容为一个JSON格式

		//返回一个messageId
		long messageId = capXaFacade.saveMessageWaitingConfirm(me);

		handler.doBiz();

		//保存messageId
		mainTransactionACKDao.saveMainTransactionACKDao(messageId);
		return messageId;
	}

	/**
	 * 业务中涉及到可订阅的分布式事务的逻辑处理
	 * @param subId 订阅编号
	 * @param content 消息内容
	 * @param aa01 操作人编号
	 * @param remark 业务描述
	 * @param handler 业务处理
	 * @return 消息编号
	 */
	protected long distributedTransactional(int subId,String content,int aa01,String remark,IBussiness handler) {
		long messageId = capXaFacade.saveSubMessageWaitingConfirm(subId,
				content, remark, String.valueOf(aa01), String.valueOf(aa01));

		handler.doBiz();

		mainTransactionACKDao.saveMainTransactionACKDao(messageId);
		return messageId;
	}

	protected interface IBussiness {
		/**
		 * 实现具体业务代码
		 */
		void doBiz();
	}

	protected interface ICacheable<T>{
		/**
		 * 从数据源加载数据
		 * @return
		 */
		T loadCache();
	}

	/**
	 * 从Redis缓存中读取数据,如果缓存中不存在,则从数据源中读取,并缓存数据到Redis中
	 * @param cacheKey 缓存KEY
	 * @param expire 缓存过期时间
	 * @param unit 缓存过期时间单位
	 * @param clazz 需要把缓存数据转换成指定对象
	 * @param cacheable 数据源获取接口
	 * @param <T> 返回类型
	 * @return 返回类型对象
	 */
	protected <T> T findCache(String cacheKey, long expire , TimeUnit unit,
							  TypeReference<T> clazz,ICacheable<T> cacheable) {
		Boolean hasKey = redisTemplate.hasKey(cacheKey);

		if(hasKey) {
			String json = (String) redisTemplate.opsForValue().get(cacheKey);
			return JSON.parseObject(json,clazz);
		}
		synchronized (this) {
			hasKey = redisTemplate.hasKey(cacheKey);

			if(hasKey) {
				String json = (String) redisTemplate.opsForValue().get(cacheKey);
				return JSON.parseObject(json,clazz);
			}

			T result = cacheable.loadCache();
			redisTemplate.opsForValue().set(cacheKey,JSON.toJSON(result),expire,unit);
			return result;
		}
	}
}

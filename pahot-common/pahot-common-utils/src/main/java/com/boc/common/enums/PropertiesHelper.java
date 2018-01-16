package com.boc.common.enums;

import java.util.Map;

import com.boc.common.utils.ResourceUtils;

/**
 * 全局常量获取工具
 * <p>@Title: PropertiesHelper.java 
 * <p>@Package com.boc.common.enums 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月21日 上午9:56:42 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class PropertiesHelper {
	private final static Map<String, String> GLOBAL_APP = ResourceUtils.getResource("global").getMap();

	/**************************************获取日志记录方式****************************************/
	public final static String LOGGER_RECORD = GLOBAL_APP.get("logger.record");
	/**************************************获取日志记录方式****************************************/

	/**************************************获取分布式锁初始化参数****************************************/
	public final static String CURATOR_LOCK_ZK = GLOBAL_APP.get("curator.lock.zk");
	public final static String CURATOR_LOCK_SESSION_TIMEOUT = GLOBAL_APP.get("curator.lock.session.timeout");
	public final static String CURATOR_LOCK_BASESLEEPTIMEMS = GLOBAL_APP.get("curator.lock.baseSleepTimeMs");
	public final static String CURATOR_LOCK_MAXRETRIES = GLOBAL_APP.get("curator.lock.maxRetries");
	/**************************************获取分布式锁初始化参数****************************************/

	/**************************************获取分布式事务相关参数****************************************/
	public final static String MESSAGE_HANDLE_DURATION = GLOBAL_APP.get("message.handle.duration");
	public final static String MESSAGE_MAX_SEND_TIMES = GLOBAL_APP.get("message.max.send.times");
	public final static String MESSAGE_SEND_1_TIME = GLOBAL_APP.get("message.send.1.time");
	public final static String MESSAGE_SEND_2_TIME = GLOBAL_APP.get("message.send.2.time");
	public final static String MESSAGE_SEND_3_TIME = GLOBAL_APP.get("message.send.3.time");
	public final static String MESSAGE_SEND_4_TIME = GLOBAL_APP.get("message.send.4.time");
	public final static String MESSAGE_SEND_5_TIME = GLOBAL_APP.get("message.send.5.time");
	/**************************************获取分布式事务相关参数****************************************/

	/**************************************获取分布式消息队列****************************************/
	public final static String LOGGER_QUEUENAME_ACCESS = GLOBAL_APP.get("logger.queueName.access");
	public final static String LOGGER_QUEUENAME_EXCEPTION = GLOBAL_APP.get("logger.queueName.exception");
	public final static String LOGGER_QUEUENAME_BIZ = GLOBAL_APP.get("logger.queueName.business");
	public final static String SAMPLE_QUEUENAME_DEMO = GLOBAL_APP.get("sample.queueName.demo");
	/**************************************获取分布式消息队列****************************************/
	
	/**************************************获取业务子系统编号****************************************/
	public final static String SYSTEM_ID_UPMS = GLOBAL_APP.get("upms.sysid");
	public final static String SYSTEM_ID_LOGGER = GLOBAL_APP.get("logger.sysid");
	public final static String SYSTEM_ID_XA = GLOBAL_APP.get("xa.sysid");
	public final static String SYSTEM_ID_GOODS = GLOBAL_APP.get("goods.sysid");
	public final static String SYSTEM_ID_MEMBER = GLOBAL_APP.get("member.sysid");
	public final static String SYSTEM_ID_BUSINESS = GLOBAL_APP.get("business.sysid");
	/**************************************获取业务子系统编号****************************************/
	
	/**************************************获取短信业务相关****************************************/
	public final static String SMS_VALIDTIME = GLOBAL_APP.get("sms.validtime");
	public final static String SMS_VALIDTIME_TIMES = GLOBAL_APP.get("sms.validtime.times");
	/**************************************获取短信业务相关****************************************/
}

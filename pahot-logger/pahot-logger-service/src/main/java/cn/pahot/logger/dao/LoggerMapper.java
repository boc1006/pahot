package cn.pahot.logger.dao;

/**
 * 系统日志服务DAO定义
 * <p>@Title: LoggerMapper.java 
 * <p>@Package cn.pahot.logger.biz 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月23日 下午1:32:39 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface LoggerMapper<T> {
	
	/**
	 * 保存系统访问日志
	 * @param accessLog
	 */
	void saveAccessLogger(T accessLog);
	
	/**
	 * 保存系统异常日志
	 * @param exceptionLog
	 */
	void saveExceptionLogger(T exceptionLog);
	
	/**
	 * 保存业务日志
	 * @param exceptionLog
	 */
	void saveBizLogger(T exceptionLog);
}

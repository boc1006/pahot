package cn.pahot.logger.facade;

import com.boc.common.exception.BizException;

import cn.pahot.logger.entity.AccessLogEntity;
import cn.pahot.logger.entity.BizLogEntity;
import cn.pahot.logger.entity.ExceptionLogEntity;
import cn.pahot.logger.exception.LoggerBizException;

/**
 * 系统日志服务接口定义
 * <p>@Title: LoggerFacade.java 
 * <p>@Package cn.pahot.logger.facade 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月23日 下午1:22:36 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface LoggerFacade {
	
	/**
	 * 记录访问日志
	 * @param entity
	 * @throws LoggerBizException
	 */
	void insertAccessLog(AccessLogEntity entity) throws BizException;
	
	/**
	 * 记录异常日志
	 * @param entity
	 * @throws BizException
	 */
	void insertExceptionLog(ExceptionLogEntity entity) throws BizException;
	
	/**
	 * 记录业务操作日志
	 * @param entity
	 * @throws BizException
	 */
	void insertBizLog(BizLogEntity entity) throws BizException;
}

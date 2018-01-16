package cn.pahot.logger.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boc.common.exception.BizException;

import cn.pahot.logger.biz.LoggerBiz;
import cn.pahot.logger.entity.AccessLogEntity;
import cn.pahot.logger.entity.BizLogEntity;
import cn.pahot.logger.entity.ExceptionLogEntity;
import cn.pahot.logger.facade.LoggerFacade;

/**
 * 系统日志服务实现
 * <p>@Title: LoggerService.java 
 * <p>@Package cn.pahot.logger.service 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月23日 下午1:30:32 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@Service("loggerFacade")
public class LoggerService implements LoggerFacade {

	@Resource
	private LoggerBiz loggerBiz;
	
	@Override
	public void insertAccessLog(AccessLogEntity entity) throws BizException {
		loggerBiz.saveAccessLogger(entity);
	}

	@Override
	public void insertExceptionLog(ExceptionLogEntity entity) throws BizException {
		loggerBiz.saveExceptionLogger(entity);
	}

	@Override
	public void insertBizLog(BizLogEntity entity) throws BizException {
		loggerBiz.saveBizLogger(entity);
	}

}

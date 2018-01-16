package cn.pahot.logger.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.boc.common.biz.IdWorkerUtil;
import com.boc.common.exception.BizException;
import com.boc.common.utils.DateUtils;

import cn.pahot.logger.dao.LoggerMapper;
import cn.pahot.logger.entity.AccessLogEntity;
import cn.pahot.logger.entity.BizLogEntity;
import cn.pahot.logger.entity.ExceptionLogEntity;
import cn.pahot.logger.exception.LoggerBizException;

/**
 * 系统日志服务业务处理
 * <p>@Title: LoggerBiz.java 
 * <p>@Package cn.pahot.logger.biz 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月23日 下午1:31:17 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@Service("loggerBiz")
public class LoggerBiz {
	
	@Resource
	private LoggerMapper<AccessLogEntity> accessLogDao;

	@Resource
	private LoggerMapper<ExceptionLogEntity> exceptionLogDao;
	
	@Resource
	private LoggerMapper<BizLogEntity> bizLogDao;
	
	/**
	 * 保存系统访问日志
	 * @param entity
	 * @throws BizException
	 */
	public void saveAccessLogger(AccessLogEntity entity) throws BizException {
		if(StringUtils.isEmpty(entity)) {
			throw LoggerBizException.PARAMS_IS_NULL.newInstance("AccessLogEntity");
		}
		
		if(entity.getId() == 0 || 
				StringUtils.isEmpty(entity.getSid()) ||
				StringUtils.isEmpty(entity.getUsertype()) ||
				StringUtils.isEmpty(entity.getUid()) ||
				entity.getAccesstime() == 0) {
			throw LoggerBizException.PARAMS_IS_NULL.newInstance("sid|usertype|uid|accesstime");
		}
		
		entity.setDbtime(DateUtils.getCurrDateTimeToLong());
		
		accessLogDao.saveAccessLogger(entity);
	}
	
	/**
	 * 保存系统异常日志
	 * @param entity
	 * @throws BizException
	 */
	public void saveExceptionLogger(ExceptionLogEntity entity) throws BizException {
		if(StringUtils.isEmpty(entity)) {
			throw LoggerBizException.PARAMS_IS_NULL.newInstance("AccessLogEntity");
		}
		
		if(entity.getId() == 0 || 
				StringUtils.isEmpty(entity.getSid()) ||
				entity.getErrortime() == 0) {
			throw LoggerBizException.PARAMS_IS_NULL.newInstance("sid|usertype|uid|accesstime");
		}
		entity.setDbtime(DateUtils.getCurrDateTimeToLong());
		
		exceptionLogDao.saveExceptionLogger(entity);
	}
	
	/**
	 * 保存业务操作日志
	 * @param entity
	 * @throws BizException
	 */
	public void saveBizLogger(BizLogEntity entity) throws BizException {
		if(StringUtils.isEmpty(entity)) {
			throw LoggerBizException.PARAMS_IS_NULL.newInstance("AccessLogEntity");
		}
		
		if(StringUtils.isEmpty(entity.getSid()) ||
				StringUtils.isEmpty(entity.getMethod_name()) ||
				entity.getAa01() == 0 ||
				entity.getAa02() == 0 ||
				StringUtils.isEmpty(entity.getHandler())) {
			throw LoggerBizException.PARAMS_IS_NULL.newInstance("sid|aa01|aa02|handler|method_name");
		}
		entity.setId(IdWorkerUtil.getId());
		bizLogDao.saveBizLogger(entity);
	}
}

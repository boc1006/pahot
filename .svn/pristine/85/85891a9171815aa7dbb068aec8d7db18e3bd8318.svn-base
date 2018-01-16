package com.boc.annotation.aspect;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.boc.common.biz.IdWorkerUtil;
import com.boc.common.enums.PropertiesHelper;
import com.boc.common.exception.BizException;
import com.boc.common.utils.DateUtils;
import com.boc.common.web.permissions.SecurityUtils;
import com.boc.common.web.permissions.Subject;
import com.boc.common.web.springmvc.BaseController;

import cn.pahot.logger.entity.AccessLogEntity;
import cn.pahot.xa.entity.MessageEntity;
import cn.pahot.xa.facade.CapXaFacade;

/**
 * 系统访问日志处理
 * <p>@Title: AccessLogAspect.java 
 * <p>@Package com.boc.annotation.aspect 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月23日 下午3:14:40 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@Aspect
@Component
public class AccessLogAspect {

    private Logger logger = Logger.getLogger(AccessLogAspect.class);

    @Resource
    private CapXaFacade capXaFacade;
    /**
     * 切点
     */
    @Pointcut("@annotation(com.boc.annotation.AccessLog)")
    public void accessLogAspect() {

    }

    /**
     * 访问日志记录
     * @param joinPoint
     * @throws BizException
     */
    @Before("accessLogAspect()")
    public void doBefore(JoinPoint joinPoint) throws BizException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Subject subject = SecurityUtils.getSubject();
        AccessLogEntity accessLogEntity = new AccessLogEntity();
        accessLogEntity.setId(IdWorkerUtil.getId());
        accessLogEntity.setSid(SecurityUtils.getWebSecurityManager().sid);
        accessLogEntity.setUid(subject==null?"0":subject.getUserid());
        accessLogEntity.setUsertype("01");//TODO 未实现用户类型的处理,默认都为01
        accessLogEntity.setUrl(request.getRequestURL().toString());
        accessLogEntity.setAccesstime(DateUtils.getCurrDateTimeToLong());
        accessLogEntity.setIp(BaseController.getRemoteHost(request));
        Enumeration<String> paramNames = request.getParameterNames();
        JSONObject param = new JSONObject();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String paramValues = request.getParameter(paramName);
			param.put(paramName, paramValues);
		}
        accessLogEntity.setParams((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName())+"===>"+param);
        if (PropertiesHelper.LOGGER_RECORD.equals("file")) {
        	logger.error("[ACCESS_LOGGER] "+JSONObject.toJSONString(accessLogEntity));
        } else {
        	MessageEntity entity = new MessageEntity();
        	entity.setQueuename(PropertiesHelper.LOGGER_QUEUENAME_ACCESS);
        	entity.setContent(JSONObject.toJSONString(accessLogEntity));
        	try {
        		capXaFacade.directSendMessage(entity);
        	} catch (BizException e) {
        		logger.error(e.getMsg());
        	}
        }
    }
}

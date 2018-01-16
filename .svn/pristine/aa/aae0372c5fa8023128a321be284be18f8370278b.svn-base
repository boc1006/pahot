package com.boc.common.core.biz;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 业务方法参数校验
 * <p>@Title pahot2.0
 * <p>@Author boc
 * <p>@Date 2017/12/27
 * <p>@Email 87080234@gmail.com
 * <p>@Department XXX部门
 * <p>@Version V1.0
 * <p>@Copyright © boc Inc. All rights reserved.
 */
@Aspect
@Component
public class MethodParamsVerifyAspect {

	private final static Logger logger = Logger.getLogger(MethodParamsVerifyAspect.class);
	public MethodParamsVerifyAspect() {
		logger.debug("实例化...");

	}

	/**
	 * 切点
	 */
	@Pointcut("@annotation(com.boc.common.core.biz.MethodParamsVerify)")
	public void methodParamVerifyAspect() {

	}

	@Before(value = "methodParamVerifyAspect()")
	public void before(JoinPoint joinPoint) {
		logger.debug("before...............................");
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = joinPoint.getTarget().getClass();

		Method[] methods = targetClass.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Parameter[] parameters = method.getParameters();
				for(int i = 0 ; i < parameters.length ; i ++) {
					Parameter parameter = parameters[i];
					Class clazz = parameter.getType();
					if(clazz == int.class ||
							clazz == float.class ||
							clazz == double.class ||
							clazz == boolean.class ||
							clazz ==byte.class ||
							clazz == long.class ||
							clazz == short.class ||
							clazz == char.class) {
						continue;
					}
					if(parameter.isAnnotationPresent(Ignore.class)) {
						continue;
					}
					if(StringUtils.isEmpty(arguments[i])) {
						throw ParamVerifyException.PARAM_IS_NULL.newInstance(targetClass+"."+methodName+"("+clazz.getName()+")");
					}
				}
			}
		}
	}
}

package com.boc.common.core.test.annotation.interceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.fastjson.JSONObject;
import com.boc.common.core.test.annotation.DubboProviderMethod;
import com.boc.common.core.test.mybatis.SqlMapThreadLocal;
import com.boc.common.core.test.mybatis.SqlThreadLocal;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;

import cn.pahot.logger.facade.DubboProviderMethodFacade;

/**
 * Dubbo服务接口调用拦截器
 * <p>@Title: DubboProviderMethodInvokeInterceptor.java 
 * <p>@Package com.boc.common.core.test.annotation.interceptor 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月18日 下午6:18:28 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class DubboProviderMethodInvokeInterceptor implements MethodInterceptor, InitializingBean, DisposableBean , org.springframework.context.ApplicationContextAware{
	
    private String serverPort = null;
    private boolean disabledProperty = false;
    private boolean disabledFacade = false;
    private ApplicationContext context;

    private DubboProviderMethodFacade dubboProviderMethodFacade;

	@Override
	public void destroy() throws Exception {
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
        dubboProviderMethodFacade = context.getBean("dubboProviderMethodFacade", DubboProviderMethodFacade.class);
        disabledProperty = Boolean.valueOf(System.getProperty("disable.junit.dubboProviderMethodFacade", "true"));

        Field field = ReflectionUtils.findField(dubboProviderMethodFacade.getClass(), "handler");
        if (field != null) {
            field.setAccessible(true);
            Object obj = ReflectionUtils.getField(field, dubboProviderMethodFacade);
            if (obj != null) {
                field = ReflectionUtils.findField(obj.getClass(), "invoker");
                if (field != null) {
                    field.setAccessible(true);
                    obj = ReflectionUtils.getField(field, obj);
                    if (obj != null) {
                        String url = obj.toString();
                        if (url != null && url.contains("disabled")) {
                            disabledFacade = true;
                        }
                    }
                }
            }
        }
        ProtocolConfig protocolConfig = context.getBean("dubbo", ProtocolConfig.class);
        if (protocolConfig != null && protocolConfig.getPort() > 0) {
            serverPort = protocolConfig.getPort().toString();
        }
        
	}

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		if(disabledProperty){
            return methodInvocation.proceed();
        }
        if(disabledFacade){
           throw new RuntimeException("cn.pahot.logger.service.DubboProviderMethodFacade is disabled , if you run junit test please enabled");
        }
        Method method = methodInvocation.getMethod();
        DubboProviderMethod dubboProviderMethod = method.getAnnotation(DubboProviderMethod.class);
        /* 方法上没有，从类上获取 */
        if (dubboProviderMethod == null) {
            dubboProviderMethod = method.getDeclaringClass().getAnnotation(DubboProviderMethod.class);
        }
        if (dubboProviderMethod != null) {
            // 参数校验
            if (StringUtils.isEmpty(dubboProviderMethod.name())) {
                throw new RuntimeException("接口开发人不能为空");
            }
            if (StringUtils.isEmpty(dubboProviderMethod.system())) {
                throw new RuntimeException("接口所属系统不能为空");
            }
            Object[] args = methodInvocation.getArguments();
            Class declaringClass = method.getDeclaringClass();
            Class[] interfaces = declaringClass.getInterfaces();
            if (interfaces != null && interfaces.length > 0) {
                for (Class interfaceClass : interfaces) {
                    if (interfaceClass.getName().startsWith("cn.pahot")) {
                        declaringClass = interfaceClass;
                    }
                }
            }
            // 接口参数的定义
            DTO<String,String> params = new BaseDTO<String,String>();
            params.put("serverIp", dubboProviderMethod.ip().trim());
            params.put("serverPort", serverPort);
            params.put("system", dubboProviderMethod.system().trim());
            params.put("username", dubboProviderMethod.name().trim());
            params.put("systemCode", dubboProviderMethod.systemNum().trim());
            params.put("serverName", declaringClass.getName());
            params.put("methodName", method.getName());
            params.put("paramerercount", String.valueOf(args.length));
            params.put("paramerer", args.length == 0 ? "" : args.length == 1 ? JSONObject.toJSONString(args[0]) : JSONObject.toJSONString(args));
            params.put("state", "0");

            java.util.Map<String, List<String>> map = SqlMapThreadLocal.get();
            if (map == null) {
                map = new HashMap<>(5);
                SqlMapThreadLocal.set(map);
            }
            String methodName = params.get("serverName") + "." + params.get("methodName");
            List<String> sqlList = (List<String>) map.get(methodName);
            if (sqlList == null) {
                sqlList = new ArrayList<String>(10);
                map.put(methodName, sqlList);
            }
            SqlThreadLocal.set(sqlList);

            Object result = methodInvocation.proceed();

            //获取执行的sql语句
            List<String> list = SqlThreadLocal.get();
            String sql = "";
            if (list != null && !list.isEmpty()) {
                sql = CollectionUtils.join(list, "; ");
            }
            params.put("sql", sql);

            dubboProviderMethodFacade.saveDubboProviderInfo(params);
            return result;
        }
        return methodInvocation.proceed();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

}

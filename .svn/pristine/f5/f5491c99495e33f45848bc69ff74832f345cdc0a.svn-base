package com.boc.annotation.aspect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONArray;
import com.boc.annotation.AccessControl;
import com.boc.common.exception.BizException;
import com.boc.common.web.enums.MenuEnums;
import com.boc.common.web.permissions.SecurityUtils;


/**
 * 权限控制逻辑处理
 * <p>@Title: AccessControlAspect.java 
 * <p>@Package com.boc.annotation.aspect 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月23日 下午6:34:57 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@Aspect
@Component
public class AccessControlAspect {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.boc.annotation.AccessControl)")
    public void controllerAspect() {

    }

    /**
     * 权限验证
     * @param joinPoint
     * @throws BizException
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws BizException {
        final Map<String,String> map = getControllerMethodDescription(joinPoint);
        MenuEnums menuEnums = new MenuEnums() {
            @Override
            public String getUri() {
                return map.get("url");
            }

            @Override
            public String getCode() {
                return map.get("code");
            }
        };
        boolean b = SecurityUtils.getWebSecurityManager().checkAuthorization(menuEnums);
        //如果权限存在情况并且code为空的情况下就查询出所有的按钮权限信息
//        if(b && map.get("code").equals("")){
            List<String> codes = SecurityUtils.getWebSecurityManager().getArightsForCodes(menuEnums);
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            request.setAttribute("hrightsCodes",JSONArray.toJSONString(codes));
//        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return url和code
     */
    public static Map<String,String> getControllerMethodDescription(JoinPoint joinPoint){
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        }catch (Exception e){
            throw BizException.INSTANCE.newInstance(e.getLocalizedMessage());
        }
        Method[] methods = targetClass.getMethods();
        String url = "";
        String code = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    url = method.getAnnotation(AccessControl.class).url();
                    code = method.getAnnotation(AccessControl.class).code();
                    break;
                }
            }
        }
        Map<String,String> map = new HashMap<String, String>();
        map.put("url",url);
        map.put("code",code);
        return map;
    }
}

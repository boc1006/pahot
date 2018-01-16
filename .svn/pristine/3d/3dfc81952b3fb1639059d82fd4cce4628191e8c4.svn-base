package com.boc.annotation;

import java.lang.annotation.*;

/**
 * 权限访问控制注释定义
 * <p>@Title: AccessControl.java 
 * <p>@Package com.boc.annotation 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月23日 下午6:34:00 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@Target({ElementType.TYPE, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessControl {
    String url();
    String code() default "";
}

package com.boc.common.core.lock.redis;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 基本Redis实现分布式锁
 * <p>@Title pahot2.0
 * <p>@Author boc
 * <p>@Date 2017/12/24
 * <p>@Email 87080234@gmail.com
 * <p>@Department XXX部门
 * <p>@Version V1.0
 * <p>@Copyright © boc Inc. All rights reserved.
 */
@Target({ElementType.TYPE, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {
	String lockName();//锁名称
	TimeUnit unit();//锁时间单位
	long timeout();//锁超时时间,超时后自动释放锁
	long tryTime();//尝试加锁等待时间
	RedisLockEnums lock();//锁类型
	boolean async() default false;//是否使用异步方式获取锁
}

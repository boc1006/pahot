package com.boc.common.core.lock.redis;

/**
 * 分布式锁类型
 * <p>@Title pahot2.0
 * <p>@Author boc
 * <p>@Date 2017/12/24
 * <p>@Email 87080234@gmail.com
 * <p>@Department XXX部门
 * <p>@Version V1.0
 * <p>@Copyright © boc Inc. All rights reserved.
 */
public enum  RedisLockEnums {
	REENTRANT, /*可重入锁*/
	FAIR,/*公平锁*/
	READ,/*读锁*/
	WRITE;/*写锁*/
}

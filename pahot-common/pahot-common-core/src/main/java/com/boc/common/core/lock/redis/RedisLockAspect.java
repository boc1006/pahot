package com.boc.common.core.lock.redis;

import com.boc.common.exception.BizException;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.concurrent.Future;
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
@Aspect
@Component
public class RedisLockAspect {
	private final static Logger log = Logger.getLogger(RedisLockAspect.class);
	@Autowired
	private Redisson redissonSentinel;

	public RedisLockAspect() {
		log.debug("实例化RedisLockAspect...");
		
	}
	
	/**
	 * 切点
	 */
	@Pointcut("@annotation(com.boc.common.core.lock.redis.RedisLock)")
	public void redisLockAspect() {

	}

	@Before(value = "redisLockAspect()")
	public void before(JoinPoint joinPoint) {
		log.debug("before...............................");
	}

	@Around(value = "redisLockAspect()")
	public void around(ProceedingJoinPoint pjp) throws Throwable {
		String lockPrefix=pjp.getTarget().getClass().getName();
		LockBean lb = getLockParam(pjp,lockPrefix);
		switch (lb.getLock()) {
			case REENTRANT:
				RLock reentrantLock = redissonSentinel.getLock(lb.getLockName());
				try {
					if(lb.isAysnc()) {
						Future<Boolean> res = reentrantLock.tryLockAsync(lb.trytime, lb.timeout, lb.unit);
						if(res.get()) {
							pjp.proceed();
						}else{//未获取到锁
							throw BizException.INSTANCE.newInstance("Unacquired Redis reentrantAsyncLock!");
						}
					} else {
						boolean res = reentrantLock.tryLock(lb.trytime, lb.timeout, lb.unit);
						if(res) {
							pjp.proceed();
						}else{//未获取到锁
							throw BizException.INSTANCE.newInstance("Unacquired Redis reentrantSyncLock!");
						}
					}
				} catch (Exception e){
					throw BizException.INSTANCE.newInstance("Acquired Redis reentrantLock error!"+e.getLocalizedMessage());
				} finally {
					reentrantLock.unlock();
				}
				break;
			case READ:
				RReadWriteLock readLock = redissonSentinel.getReadWriteLock(lb.getLockName());
				try {
					if(lb.isAysnc()) {
						Future<Boolean> res = readLock.readLock().tryLockAsync(lb.trytime, lb.timeout, lb.unit);
						if(res.get()) {
							pjp.proceed();
						}else{//未获取到锁
							throw BizException.INSTANCE.newInstance("Unacquired Redis readAsyncLock!");
						}
					}else{
						boolean res = readLock.readLock().tryLock(lb.trytime, lb.timeout, lb.unit);
						if(res) {
							pjp.proceed();
						}else{//未获取到锁
							throw BizException.INSTANCE.newInstance("Unacquired Redis readSyncLock!");
						}
					}
				} catch (Exception e){
					throw BizException.INSTANCE.newInstance("Acquired Redis readLock error!"+e.getLocalizedMessage());
				} finally {
					readLock.readLock().unlock();
				}
				break;
			case WRITE:
				RReadWriteLock writeLock = redissonSentinel.getReadWriteLock(lb.getLockName());
				try {
					if(lb.isAysnc()) {
						Future<Boolean> res = writeLock.writeLock().tryLockAsync(lb.trytime, lb.timeout, lb.unit);
						if(res.get()) {
							pjp.proceed();
						}else{//未获取到锁
							throw BizException.INSTANCE.newInstance("Unacquired Redis writeAsyncLock!");
						}
					}else{
						boolean res = writeLock.writeLock().tryLock(lb.trytime, lb.timeout, lb.unit);
						if(res) {
							pjp.proceed();
						}else{//未获取到锁
							throw BizException.INSTANCE.newInstance("Unacquired Redis writeSyncLock!");
						}
					}
				} catch (Exception e){
					throw BizException.INSTANCE.newInstance("Acquired Redis writeLock error!"+e.getLocalizedMessage());
				} finally {
					writeLock.writeLock().unlock();
				}
				break;
			case FAIR:
				RLock fairLock = redissonSentinel.getFairLock(lb.getLockName());
				try {
					if(lb.isAysnc()) {
						Future<Boolean> res = fairLock.tryLockAsync(lb.trytime, lb.timeout, lb.unit);
						if(res.get()) {
							pjp.proceed();
						}else{//未获取到锁
							throw BizException.INSTANCE.newInstance("Unacquired Redis fairAsyncLock!");
						}
					}else{
						boolean res = fairLock.tryLock(lb.trytime, lb.timeout, lb.unit);
						if(res) {
							pjp.proceed();
						}else{//未获取到锁
							throw BizException.INSTANCE.newInstance("Unacquired Redis fairSyncLock!");
						}
					}
				} catch (Exception e){
					throw BizException.INSTANCE.newInstance("Acquired Redis fairLock error!"+e.getLocalizedMessage());
				} finally {
					fairLock.unlock();
				}
				break;
			default:throw BizException.INSTANCE.newInstance("NotFind Redis lock Type!");
		}
	}

	@AfterReturning(value = "redisLockAspect()")
	public void afterReturning(JoinPoint joinPoint) {
	}

	@AfterThrowing(value = "redisLockAspect()")
	public void afterThrowing(JoinPoint joinPoint) {
		log.error("异常...");
	}

	@After(value = "redisLockAspect()")
	public void after(JoinPoint joinPoint) {
		log.debug("after........................................");
	}

	private LockBean getLockParam(JoinPoint joinPoint,String prefix) {
		LockBean lb = new LockBean();
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = null;
		try {
			targetClass = Class.forName(targetName);
		} catch (Exception e) {
			throw BizException.INSTANCE.newInstance(e.getLocalizedMessage());
		}
		Method[] methods = targetClass.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					lb.setLock(method.getAnnotation(RedisLock.class).lock());
					lb.setUnit(method.getAnnotation(RedisLock.class).unit());
					lb.setTrytime(method.getAnnotation(RedisLock.class).tryTime());
					lb.setTimeout(method.getAnnotation(RedisLock.class).timeout());
					lb.setLockName(prefix+"_"+method.getAnnotation(RedisLock.class).lockName());
					lb.setAysnc(method.getAnnotation(RedisLock.class).async());
					break;
				}
			}
		}
		return lb;
	}

	@SuppressWarnings("unused")
	private class LockBean implements Serializable {
		private static final long serialVersionUID = -2176238242549138935L;
		private TimeUnit unit;
		private long timeout;
		private long trytime;
		private RedisLockEnums lock;
		private String lockName;
		private boolean aysnc;

		public TimeUnit getUnit() {
			return unit;
		}

		public void setUnit(TimeUnit unit) {
			this.unit = unit;
		}

		public long getTimeout() {
			return timeout;
		}

		public void setTimeout(long timeout) {
			this.timeout = timeout;
		}

		public long getTrytime() {
			return trytime;
		}

		public void setTrytime(long trytime) {
			this.trytime = trytime;
		}

		public RedisLockEnums getLock() {
			return lock;
		}

		public void setLock(RedisLockEnums lock) {
			this.lock = lock;
		}

		public String getLockName() {
			return lockName;
		}

		public void setLockName(String lockName) {
			this.lockName = lockName;
		}

		public boolean isAysnc() {
			return aysnc;
		}

		public void setAysnc(boolean aysnc) {
			this.aysnc = aysnc;
		}
	}
}

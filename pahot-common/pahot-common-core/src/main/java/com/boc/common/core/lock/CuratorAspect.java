package com.boc.common.core.lock;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boc.common.exception.BizException;

@Aspect
@Component
public class CuratorAspect {
	private final static Logger log = Logger.getLogger(CuratorAspect.class);
	@Autowired
	private CuratorFramework curatorFramework;
	private final static String NODE_PREFIX = "/pahotLock/";
	
	public CuratorAspect () {
		log.debug("实例化...");
		
	}
	
	/**
	 * 切点
	 */
	@Pointcut("@annotation(com.boc.common.core.lock.CuratorLock)")
	public void curatorAspect() {

	}

	@Before(value = "curatorAspect()")
	public void before(JoinPoint joinPoint) {
		log.debug("before...............................");
	}

	@Around(value = "curatorAspect()")
	public void around(ProceedingJoinPoint pjp) throws Throwable {
		LockBean lb = getLockParam(pjp);
		InterProcessMutex lock = new InterProcessMutex(curatorFramework, NODE_PREFIX+pjp.getTarget().getClass().getName()+"/"+lb.getId());
		lock.acquire();
		try {
			pjp.proceed();
		} finally {
			lock.release();
		}
	}

	@AfterReturning(value = "curatorAspect()")
	public void afterReturning(JoinPoint joinPoint) {
	}

	@AfterThrowing(value = "curatorAspect()")
	public void afterThrowing(JoinPoint joinPoint) {
		log.error("异常...");
	}

	@After(value = "curatorAspect()")
	public void after(JoinPoint joinPoint) {
		log.debug("after........................................");
	}

	private LockBean getLockParam(JoinPoint joinPoint) {
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
					lb.setId(method.getAnnotation(CuratorLock.class).id());
					lb.setMills(method.getAnnotation(CuratorLock.class).keepMills());
					lb.setWait(method.getAnnotation(CuratorLock.class).toWait());
					break;
				}
			}
		}
		return lb;
	}

	@SuppressWarnings("unused")
	private class LockBean implements Serializable {
		private static final long serialVersionUID = -2176238242549138935L;
		private int id;
		private int mills;
		private boolean wait;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getMills() {
			return mills;
		}

		public void setMills(int mills) {
			this.mills = mills;
		}

		public boolean isWait() {
			return wait;
		}

		public void setWait(boolean wait) {
			this.wait = wait;
		}
	}
}

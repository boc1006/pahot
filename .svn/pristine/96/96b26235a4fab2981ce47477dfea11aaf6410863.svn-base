package com.boc.common.core.lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CuratorConfig {
	private final static Logger log = Logger.getLogger(CuratorConfig.class);
	@Value("${curator.lock.baseSleepTimeMs}")
	private int baseSleepTimeMs;
	@Value("${curator.lock.maxRetries}")
	private int maxRetries;
	@Value("${curator.lock.zk}")
	private String connectString;
	@Value("${curator.lock.session.timeout}")
	private int sessionTimeoutMs;
	
	@Bean
	public CuratorFramework curatorFramework() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs,maxRetries);
		CuratorFramework cf = CuratorFrameworkFactory.builder()
				.connectString(connectString)
				.sessionTimeoutMs(sessionTimeoutMs)
				.retryPolicy(retryPolicy)
				.build();
		cf.start();
		log.info("初始化分布式锁完成...");
		return cf;
	}
}

package cn.pahot.xa.scheduler;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import cn.pahot.xa.scheduler.crontab.XaCrontab;

/**
 * 分布式事务消息恢复子系统和状态确认子系统启动类
 * <p>@Title: Main.java 
 * <p>@Package cn.pahot.xa.scheduler 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月30日 下午2:09:43 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ImportResource(value = { "spring/spring-context.xml" })
@MapperScan(basePackages= {"com.pahot.xa.scheduler"})
public class AppScheduler {
	private static Logger logger = Logger.getLogger(AppScheduler.class);

	private static final CountDownLatch cdl = new CountDownLatch(1);

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = new SpringApplicationBuilder()
			.sources(AppScheduler.class)
			.web(false)
			.run(args);
		final XaCrontab settScheduled = (XaCrontab) context.getBean("xaCrontab");
		ThreadPoolTaskExecutor threadPool = (ThreadPoolTaskExecutor) context.getBean("threadPool");
		// 开启一个子线程处理状态为“待确认”但已超时的消息.
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						logger.info("执行(处理[waiting_confirm]状态的消息)任务开始");
						settScheduled.handleWaitingConfirmTimeOutMessages();
						logger.info("执行(处理[waiting_confirm]状态的消息)任务结束");
						logger.info("[waiting_confirm]睡眠60秒");
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						logger.error("开启一个子线程处理状态为“待确认”但已超时的消息->", e);
					}
				}
			}
		});
		
		// 开启一个子线程处理状态为“待确认”但已超时的订阅消息.
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						logger.info("执行(处理[waiting_confirm]状态的订阅消息)任务开始");
						settScheduled.handleWaitingConfirmTimeOutSubMessages();
						logger.info("执行(处理[waiting_confirm]状态的订阅消息)任务结束");
						logger.info("[waiting_confirm]睡眠60秒");
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						logger.error("开启一个子线程处理状态为“待确认”但已超时的订阅消息->", e);
					}
				}
			}
		});

		// 开启一个子线程处理状态为“发送中”但超时没有被成功消费确认的消息
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				while (true) {
                    try {
                    	logger.info("执行(处理[SENDING]的消息)任务开始");
                        settScheduled.handleSendingTimeOutMessage();
                        logger.info("执行(处理[SENDING]的消息)任务结束");

                        logger.info("[SENDING]睡眠60秒");
						Thread.sleep(60000);
					} catch (Exception e) {
						logger.error("开启一个子线程处理状态为“发送中”但超时没有被成功消费确认的消息->", e);
					}
				}
			}
		});
		
		// 开启一个子线程处理状态为“已确认”并达到延迟发送时间的消息
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						logger.info("执行(处理[CONFIRMED]的消息)任务开始");
						settScheduled.handleConfirmedAndDelaydMessage();
						logger.info("执行(处理[CONFIRMED]的消息)任务结束");
						
						logger.info("[CONFIRMED]睡眠60秒");
						Thread.sleep(60000);
					} catch (Exception e) {
						logger.error("开启一个子线程处理状态为“已确认”并达到延迟发送时间的消息->", e);
					}
				}
			}
		});
		logger.info("Scheduler Start Success!");
		cdl.await();
	}
}

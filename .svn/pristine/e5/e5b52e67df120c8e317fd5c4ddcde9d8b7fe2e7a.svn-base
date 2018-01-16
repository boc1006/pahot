package cn.pahot.logger;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = { "spring/spring-context.xml" })
@MapperScan(basePackages = { "com.boc.common.core.dao.ack", "cn.pahot.logger.dao" })
public class DubboProvider {
	private static Logger logger = Logger.getLogger(DubboProvider.class);

	private static final CountDownLatch cdl = new CountDownLatch(1);

	public static void main(String[] args) throws InterruptedException {
		new SpringApplicationBuilder()
			.sources(DubboProvider.class)
			.web(false)
			.run(args);
		logger.info("Dubbo Start Success!");
		cdl.await();
	}
}

package cn.pahot.example.consumer;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages= {"com.boc.common.core.lock","cn.pahot.example.consumer"})
@MapperScan(basePackages = { "com.boc.common.core.dao.ack", "cn.pahot.example.consumer.dao" })
@ImportResource(value = { "spring/spring-context.xml" })
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

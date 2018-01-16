package cn.pahot.goods;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = { "classpath:spring/spring-context-test.xml" })
@MapperScan(basePackages = { "com.boc.common.core.dao.ack", "cn.pahot.goods.dao"})
public class DubboProviderTest {
	private static Logger logger = Logger.getLogger(DubboProviderTest.class);

	private static final CountDownLatch cdl = new CountDownLatch(1);

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("disable.junit.dubboProviderMethodFacade", "false");
		new SpringApplicationBuilder()
			.sources(DubboProviderTest.class)
			.web(false)
			.run(args);
		logger.info("Dubbo Start Success!");
		cdl.await();
	}
}

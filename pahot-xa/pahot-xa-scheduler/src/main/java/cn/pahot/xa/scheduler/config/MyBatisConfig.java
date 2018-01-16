package cn.pahot.xa.scheduler.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.boc.common.enums.DataSourceEnums;

import cn.pahot.xa.scheduler.datasource.DynamicDataSource;

/**
 * 多数据源配置,用于分布式事务状态确定子系统
 * <p>@Title: MyBatisConfig.java 
 * <p>@Package cn.pahot.xa.scheduler.config 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月30日 下午2:06:58 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@Configuration
@MapperScan(basePackages = { "cn.pahot.xa.scheduler.dao" })
@PropertySource("classpath:jdbc.properties")
public class MyBatisConfig {

	@Autowired
	private Environment env;

	/**
	 * pt_upms数据源配置
	 * @return
	 * @throws Exception
	 */
	@Bean
	public DataSource upmsDataSource() throws Exception {
		Properties props = new Properties();
		props.put("driverClassName", "com.mysql.jdbc.Driver");
		props.put("url", env.getProperty("upms.jdbc.url"));
		props.put("username", env.getProperty("upms.jdbc.username"));
		props.put("password", env.getProperty("upms.jdbc.password"));
		props.put("filters","config");
		props.put("connectionProperties", "config.decrypt=true");
		return DruidDataSourceFactory.createDataSource(props);
	}

	/**
	 * pt_logger数据库数据库
	 * @return
	 * @throws Exception
	 */
	@Bean
	public DataSource loggerDataSource() throws Exception {
		Properties props = new Properties();
		props.put("driverClassName", "com.mysql.jdbc.Driver");
		props.put("url", env.getProperty("logger.jdbc.url"));
		props.put("username", env.getProperty("logger.jdbc.username"));
		props.put("password", env.getProperty("logger.jdbc.password"));
		props.put("filters","config");
		props.put("connectionProperties", "config.decrypt=true");
		return DruidDataSourceFactory.createDataSource(props);
	}
	
	/**
	 * pt_goods数据库数据库
	 * @return
	 * @throws Exception
	 */
	@Bean
	public DataSource goodsDataSource() throws Exception {
		Properties props = new Properties();
		props.put("driverClassName", "com.mysql.jdbc.Driver");
		props.put("url", env.getProperty("goods.jdbc.url"));
		props.put("username", env.getProperty("goods.jdbc.username"));
		props.put("password", env.getProperty("goods.jdbc.password"));
		props.put("filters","config");
		props.put("connectionProperties", "config.decrypt=true");
		return DruidDataSourceFactory.createDataSource(props);
	}
	
	/**
	 * pt_member数据库数据库
	 * @return
	 * @throws Exception
	 */
	@Bean
	public DataSource memberDataSource() throws Exception {
		Properties props = new Properties();
		props.put("driverClassName", "com.mysql.jdbc.Driver");
		props.put("url", env.getProperty("member.jdbc.url"));
		props.put("username", env.getProperty("member.jdbc.username"));
		props.put("password", env.getProperty("member.jdbc.password"));
		props.put("filters","config");
		props.put("connectionProperties", "config.decrypt=true");
		return DruidDataSourceFactory.createDataSource(props);
	}
	
	/**
	 * pt_business数据库数据库
	 * @return
	 * @throws Exception
	 */
	@Bean
	public DataSource businessDataSource() throws Exception {
		Properties props = new Properties();
		props.put("driverClassName", "com.mysql.jdbc.Driver");
		props.put("url", env.getProperty("business.jdbc.url"));
		props.put("username", env.getProperty("business.jdbc.username"));
		props.put("password", env.getProperty("business.jdbc.password"));
		props.put("filters","config");
		props.put("connectionProperties", "config.decrypt=true");
		return DruidDataSourceFactory.createDataSource(props);
	}

	/**
	 * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
	 * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
	 */
	@Bean
	@Primary
	public DynamicDataSource dataSource(@Qualifier("upmsDataSource") DataSource upmsDataSource,
			@Qualifier("loggerDataSource") DataSource loggerDataSource,
			@Qualifier("goodsDataSource") DataSource goodsDataSource,
			@Qualifier("memberDataSource") DataSource memberDataSource,
			@Qualifier("businessDataSource") DataSource businessDataSource) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DataSourceEnums.PT_UPMS.key, upmsDataSource);
		targetDataSources.put(DataSourceEnums.PT_LOGGER.key, loggerDataSource);
		targetDataSources.put(DataSourceEnums.PT_GOODS.key, goodsDataSource);
		targetDataSources.put(DataSourceEnums.PT_MEMBER.key, memberDataSource);
		targetDataSources.put(DataSourceEnums.PT_BUSINESS.key, businessDataSource);

		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
		dataSource.setDefaultTargetDataSource(upmsDataSource);// 默认的datasource设置为myTestDbDataSource

		return dataSource;
	}

	/**
	 * 根据数据源创建SqlSessionFactory
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
		// 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
//		fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));// 指定基包
//		fb.setMapperLocations(
//				new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//

		return fb.getObject();
	}

	/**
	 * 配置事务管理器
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
		return new DataSourceTransactionManager(dataSource);
	}

}

package com.boc.common.cache.redis;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableCaching
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
	@Value("${spring.redis.host}")
	private String host;
	
	@Value("${spring.redis.port}")
	private int port;
	
	@Value("${spring.redis.database}")
	private int database;
	
	@Value("${spring.redis.timeout}")
	private int timeout;
	
	@Value("${spring.redis.password}")
	private String password;
	
	@Value("${spring.redis.pool.max-active}")
	private int maxActive;
	
	@Value("${spring.redis.pool.max-wait}")
	private long maxWaitMillis;
	
	@Value("${spring.redis.pool.max-idle}")
	private int maxIdle;
	
	@Value("${spring.redis.pool.min-idle}")
	private int minIdle;

	@Bean
	public Redisson redissonSentinel() {
		Config config = new Config();
//		config.useClusterServers()
//				.setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
//				//可以用"rediss://"来启用SSL连接
//				.addNodeAddress("redis://10.82.0.102:7000")
//				.addNodeAddress("redis://10.82.0.102:7001")
//				.addNodeAddress("redis://10.82.0.102:7002")
//				.addNodeAddress("redis://10.82.0.102:7003")
//				.addNodeAddress("redis://10.82.0.102:7004")
//				.addNodeAddress("redis://10.82.0.102:7005");
		config.useSingleServer().setAddress("redis://"+host+":"+port);
		return (Redisson)Redisson.create(config);
	}

	// 缓存管理器
	@Bean
	public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		return cacheManager;
	}

	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
	}

}

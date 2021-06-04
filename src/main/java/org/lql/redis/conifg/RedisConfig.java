package org.lql.redis.conifg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Title: RedisConfig <br>
 * ProjectName: spring-boot-example <br>
 * description: redis配置 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/4 15:47 <br>
 */
@Configuration
public class RedisConfig {

    private RedisConnectionFactory redisConnectionFactory = null;

    @Bean
    public RedisConnectionFactory initRedisConnectionFactory() {
        if (this.redisConnectionFactory != null) {
            return this.redisConnectionFactory;
        }

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大空闲数
        poolConfig.setMaxIdle(30);
        // 最大连接数
        poolConfig.setMaxTotal(50);
        // 最大等待毫秒数
        poolConfig.setMaxWaitMillis(2000);
        // 创建jedis连接工厂
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        // 获取单机的redis配置
        RedisStandaloneConfiguration standaloneConfiguration = jedisConnectionFactory.getStandaloneConfiguration();
        standaloneConfiguration.setHostName("127.0.0.1");
        standaloneConfiguration.setPort(6379);
        this.redisConnectionFactory = jedisConnectionFactory;
        return jedisConnectionFactory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<Object, Object> initRedisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(initRedisConnectionFactory());

        return redisTemplate;
    }
}

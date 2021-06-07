package org.lql.redis.conifg;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;

/**
 * Title: RedisConfigByBoot <br>
 * ProjectName: spring-boot-example <br>
 * description: Spring boot会读取配置的属性来生成相关的Redis的操作对象，如RedisConnectionFactory、
 * RedisTemplate、StringRedisTemplate等，RedisTemplate会默认使用JdkSerializationRedisSerializer进行
 * 序列化键值，如果在Redis中只是使用字符串，那使用其自动生成的StringRedisTemplate即可，但是这样只能支持
 * 字符串，并不能支持java对象的存储，为此可以通过设置RedisTemplate的序列化器来处理<br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/7 20:35 <br>
 */
@Configuration
public class RedisConfigByBoot {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        initRedisTemplate();
    }

    private void initRedisTemplate() {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }
}

package org.lql.redis.conifg;

import org.aspectj.lang.annotation.Pointcut;
import org.lql.redis.listener.RedisMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

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

    @Autowired
    private RedisMessageListener redisMessageListener;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    private ThreadPoolTaskScheduler taskScheduler = null;

    @PostConstruct
    public void init() {
        initRedisTemplate();
    }

    private void initRedisTemplate() {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }

    /**
     * description: 创建任务池 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/6/8 10:59 <br>
     * 
     * @throws
     * @param 
     * @return org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
     */
    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler() {
        if (this.taskScheduler != null) {
            return this.taskScheduler;
        }

        this.taskScheduler = new ThreadPoolTaskScheduler();
        this.taskScheduler.setPoolSize(20);
        return this.taskScheduler;
    }

    @Bean
    public RedisMessageListenerContainer initRedisMessageListener() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        // Redis连接工厂
        container.setConnectionFactory(redisConnectionFactory);
        // 设置运行任务池
        container.setTaskExecutor(initTaskScheduler());
        // 定义监听渠道，名称为topic1
        Topic topic = new ChannelTopic("topic1");
        // 使用监听器监听redis消息
        container.addMessageListener(redisMessageListener, topic);

        return container;
    }
}

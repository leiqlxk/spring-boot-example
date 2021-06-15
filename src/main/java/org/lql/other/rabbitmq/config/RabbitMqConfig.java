package org.lql.other.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Title: RabbitMqConfig <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/15 13:52 <br>
 */
//@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.queue.msg}")
    private String msgQueueName;

    @Value("${rabbit.queue.object}")
    private String objectQueueName;

    @Bean
    public Queue createQueueMsg() {
        // boolean表示是否持久化
        return new Queue(msgQueueName, true);
    }

    @Bean
    public Queue createQueueObject() {
        return new Queue(objectQueueName, true);
    }
}

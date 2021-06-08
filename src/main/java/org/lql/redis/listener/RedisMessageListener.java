package org.lql.redis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * Title: RedisMessageListener <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/8 10:54 <br>
 */
@Component
public class RedisMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {
        // 消息体
        String body = new String(message.getBody());

        // 消息渠道名
        String topic = new String(bytes);

        System.out.println(body);
        System.out.println(topic);
    }
}

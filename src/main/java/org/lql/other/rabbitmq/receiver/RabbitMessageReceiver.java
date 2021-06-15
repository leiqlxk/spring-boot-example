package org.lql.other.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Title: RabbitMessageReceiver <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/15 14:10 <br>
 */
//@Component
public class RabbitMessageReceiver {

    @RabbitListener(queues = {"${rabbitmq.queue.msg}"})
    public void receiveMsg(String msg) {

    }

    @RabbitListener(queues = {"${rabbitmq.queue.object}"})
    public void receiveObject(Object object) {

    }
}

package org.lql.other.rabbitmq.service;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Title: RabbitMqServiceImpl <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/15 14:03 <br>
 */
//@Service
public class RabbitMqServiceImpl implements RabbitMqService, RabbitTemplate.ConfirmCallback {

    @Value("${rabbitmq.queue.msg}")
    private String msgRouting;

    @Value("${rabbit.queue.object}")
    private String objectRouting;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMsg(String msg) {
        System.out.println("发送消息：【" + msg + "】");
        // 设置回调
        rabbitTemplate.setConfirmCallback(this);

        rabbitTemplate.convertAndSend(msgRouting, msg);
    }

    @Override
    public void sendObject(Object object) {
        System.out.println("发送消息：【" + object + "】");
        // 设置回调
        rabbitTemplate.setConfirmCallback(this);

        rabbitTemplate.convertAndSend(objectRouting, object);
    }

    // 回调确认方法
    @Override
    public void confirm(CorrelationData correlationdata, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息成功消费");
        }else {
            System.out.println("消息消费失败：" + cause);
        }
    }
}

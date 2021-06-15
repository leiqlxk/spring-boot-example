package org.lql.other.rabbitmq.service;

/**
 * Title: RabbitMqService <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/15 14:02 <br>
 */
public interface RabbitMqService {

    void sendMsg(String msg);

    void sendObject(Object object);
}

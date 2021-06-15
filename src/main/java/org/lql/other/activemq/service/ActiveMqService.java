package org.lql.other.activemq.service;

/**
 * Title: ActiveMqService <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/15 13:35 <br>
 */
public interface ActiveMqService {

    void sendMsg(String message);

    void receiveMsg(String message);
}

package org.lql.iocannotation;

import org.lql.iocannotation.config.AppConfig;
import org.lql.iocannotation.pojo.DataBaseProperties;
import org.lql.iocannotation.domain.User;
import org.lql.iocannotation.domain.User1;
import org.lql.iocannotation.pojo.BusinessPerson;
import org.lql.iocannotation.pojo.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Title: IocTest <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/1 10:49 <br>
 */
public class IocTest {
    private static Logger logger = LoggerFactory.getLogger(IocTest.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = ctx.getBean(User.class);
        logger.info("userId:" + user.getId().toString());

        User1 user1 = ctx.getBean(User1.class);
        logger.info("userId:" + user1.getId());

        /*UserService userService = ctx.getBean(UserService.class);
        userService.printUser(user);*/

        Person person = ctx.getBean(BusinessPerson.class);
        person.service();
        person.allService();

        DataBaseProperties properties = ctx.getBean(DataBaseProperties.class);
        System.out.println(properties.getDriverName());
        System.out.println(properties.getUrl());
        System.out.println(properties.getUserName());
        System.out.println(properties.getPassword());

        ctx.close() ;
    }
}

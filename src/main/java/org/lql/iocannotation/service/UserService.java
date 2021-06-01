package org.lql.iocannotation.service;

import org.lql.iocannotation.domain.User;
import org.springframework.stereotype.Service;

/**
 * Title: UserService <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/1 11:27 <br>
 */
@Service
public class UserService {

    public void printUser(User user) {
        System.out.println("编号：" + user.getId());
        System.out.println("用户名称：" + user.getUserName());
        System.out.println("备注：" + user.getNote());
    }
}

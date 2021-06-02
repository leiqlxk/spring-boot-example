package org.lql.aop.service;

import org.lql.aop.domain.User;
import org.springframework.stereotype.Service;

/**
 * Title: UserServiceImpl <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/2 14:43 <br>
 */
@Service
public class UserServiceImpl implements UserService {
    public void printUser(User user) {
        if (user == null) {
            throw new RuntimeException("检查用户参数是否为空......");
        }

        System.out.println("id=" + user.getId());
        System.out.println("\tuserName=" + user.getUserName());
        System.out.println("\tnote=" + user.getNote());
    }
}

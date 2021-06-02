package org.lql.aop.advice;

import org.lql.aop.domain.User;

/**
 * Title: UserValidatorImpl <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/2 15:26 <br>
 */
public class UserValidatorImpl implements UserValidator {
    public boolean validate(User user) {
        System.out.println("引入新的接口：" + UserValidator.class.getSimpleName());
        return user != null;
    }
}

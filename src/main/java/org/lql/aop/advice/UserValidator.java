package org.lql.aop.advice;

import org.lql.aop.domain.User;

/**
 * Title: UserValidator <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/2 15:25 <br>
 */
public interface UserValidator {
    // 检测用户是否为空
    public boolean validate(User user);
}

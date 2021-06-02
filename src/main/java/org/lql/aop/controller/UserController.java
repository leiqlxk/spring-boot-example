package org.lql.aop.controller;

import org.lql.aop.advice.UserValidator;
import org.lql.aop.domain.User;
import org.lql.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Title: UserController <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/2 15:03 <br>
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/print")
    @ResponseBody
    public User printUser(Long id, String userName, String note) {
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setNote(note);
        userService.printUser(user);

        user = null;
        // 强制转换
        UserValidator userValidator = (UserValidator) userService;
        //验证是否为空
        if (userValidator.validate(user)) {
            userService.printUser(user);
        }
        return user;
    }
}

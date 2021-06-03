package org.lql.database.controller;

import org.lql.database.jpa.jpadao.JpaUserRepository;
import org.lql.database.jpa.jpadomain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Title: JpaController <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/3 11:41 <br>
 */
@Controller
@RequestMapping("/jpa")
public class JpaController {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id) {
        User user = jpaUserRepository.findById(id).get();
        return user;
    }

    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUserById(Long id) {
        User user = jpaUserRepository.getUserById(id);
        return user;
    }

    @RequestMapping("/findByUserNameLikeOrNoteLike")
    @ResponseBody
    public List<User> findByUserNameLikeOrNoteLike(String userName, String note) {
        List<User> users = jpaUserRepository.findByUserNameLikeOrNoteLike(userName, note);
        return users;
    }
}

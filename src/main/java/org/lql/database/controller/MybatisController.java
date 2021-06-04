package org.lql.database.controller;

import org.lql.database.mybatis.mybatisdomain.User;
import org.lql.database.service.MybatisUserService;
import org.lql.database.service.UserBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: MybatisController <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/3 14:17 <br>
 */
@Controller
@RequestMapping("/mybatis")
public class MybatisController {

    @Autowired
    private MybatisUserService mybatisUserService;

    @Autowired
    private UserBatchService userBatchService;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id) {
        return mybatisUserService.getUser(id);
    }

    @RequestMapping("/insertUsers")
    @ResponseBody
    public Map<String, Object> insertUsers() {
        User user1 = new User();
        user1.setUserName("curry");
        user1.setNote("test1");

        User user2 = new User();
        user2.setUserName("james");
        user2.setNote("test2");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        int inserts = userBatchService.insertUsers(users);
        Map<String, Object> result = new HashMap<>();
        result.put("success", inserts > 0);
        result.put("users", users);
        return result;
    }
}

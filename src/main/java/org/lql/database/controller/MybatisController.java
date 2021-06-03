package org.lql.database.controller;

import org.lql.database.mybatis.mybatisdomain.User;
import org.lql.database.service.MybatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id) {
        return mybatisUserService.getUser(id);
    }
}

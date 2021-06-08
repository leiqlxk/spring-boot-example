package org.lql.springmvc.controller;

import org.lql.database.mybatis.mybatisdomain.User;
import org.lql.database.service.MybatisUserService;
import org.lql.springmvc.service.MvcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Title: UserController <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/8 16:04 <br>
 */
@Controller
@RequestMapping("/mvcUserController")
public class MvcUserController {

    @Autowired
    private MybatisUserService userService;

    // 展示用户详情
    @RequestMapping("/details")
    public ModelAndView details(Long id) {
        User user = userService.getUser(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/details");
        modelAndView.addObject("user", user);

        return modelAndView;
//        return "user/details";
    }


}

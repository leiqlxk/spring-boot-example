package org.lql.rest.controller;

import org.lql.database.enumeration.SexEnum;
import org.lql.database.mybatis.mybatisdomain.User;
import org.lql.database.service.MybatisUserService;
import org.lql.rest.vo.ResultVo;
import org.lql.rest.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: RestUserController <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/10 15:30 <br>
 */
//@RestController会默认返回json视图可不在每个方法都标注@ResponseBody，当要返回jsp视图时要采用ModelAndView不能直接返回字符串
@RestController
@RequestMapping("/restUser")
public class RestUserController {

    @Autowired
    private MybatisUserService userService;

    @GetMapping("/restful")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("restful");
        return modelAndView;
    }

    @PostMapping("/user")
    @ResponseBody
    public User insertUser(@RequestBody UserVo userVo) {
        User user = changeToPo(userVo);
        userService.insertUser(user);
        return user;
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public UserVo getUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);

        return changeToVo(user);
    }

    // 参数多余五个应使用请求体传递
    @GetMapping("/users/{userName}/{note}/{start}/{limit}")
    @ResponseBody
    public List<UserVo> findUsers(@PathVariable("userName") String userName,
                                  @PathVariable("note") String note,
                                  @PathVariable("start") Integer start,
                                  @PathVariable("limit") Integer limit) {
        List<User> users = userService.findUsers(userName, note);

        return changeToVos(users);
    }

    @PutMapping("/user/{id}")
    @ResponseBody
    public UserVo updateUser(@PathVariable("id") Long id, @RequestBody UserVo userVo) {
        User user = changeToPo(userVo);
        user.setId(id);
        userService.updateUser(user);

        return changeToVo(user);
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public ResultVo deleteUser(@PathVariable("id") Long id) {
        int result = userService.deleteUser(id);

        ResultVo resultVo = new ResultVo(result > 0,  result > 0 ? "删除成功" : "删除用户【" + id + "】失败");

        return resultVo;
    }

    /*@GetMapping("/users")
    @ResponseBody
    public List<UserVo> getUsers(String userName, String note, Integer start, Integer limit) {
        List<User> users = userService.findUsers(userName, note);

        return changeToVos(users);
    }*/

    // vo转po
    private User changeToPo(UserVo userVo) {
        User user = new User();
        user.setId(userVo.getId());
        user.setUserName(userVo.getUserName());
        user.setSex(SexEnum.getEnumById(userVo.getSexCode()));
        user.setNote(userVo.getNote());

        return user;
    }

    // po转vo
    private UserVo changeToVo(User user) {
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUserName(user.getUserName());
        userVo.setSexCode(user.getSex().getId());
        userVo.setSexName(user.getSex().getName());
        userVo.setNote(user.getNote());

        return userVo;
    }

    // po列表转换位vo列表
    private List<UserVo> changeToVos(List<User> users) {
        List<UserVo> userVos = new ArrayList<>();

        for (User user : users) {
            UserVo userVo = changeToVo(user);
            userVos.add(userVo);
        }

        return userVos;
    }
}

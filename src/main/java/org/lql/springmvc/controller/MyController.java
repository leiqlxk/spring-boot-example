package org.lql.springmvc.controller;

import org.lql.database.mybatis.mybatisdomain.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: MyController <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/9 15:41 <br>
 */
@Controller
@RequestMapping("/my")
public class MyController {

    /**
     * description: 在无注解下获取参数，要求参数名称和HTTP请求参数名称一致, 参数默认可以为空 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/6/9 15:45 <br>
     *
     * @throws
     * @param intVal
     * @param longVal
     * @param str
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("/no/annotation")
    @ResponseBody
    public Map<String, Object> noAnnotation(Integer intVal, Long longVal, String str) {
        Map<String, Object> map = new HashMap<>();

        map.put("intVal", intVal);
        map.put("longVal", longVal);
        map.put("str", str);

        return map;
    }

    /**
     * description: 通过注解@RequestParam获取参数，该注解默认参数必填 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/6/9 16:10 <br>
     * 
     * @throws
     * @param intVal
     * @param longVal
     * @param str
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */ 
    @RequestMapping("/annotation")
    @ResponseBody
    public Map<String, Object> annotation(
            @RequestParam("int_val") Integer intVal, 
            @RequestParam("long_val") Long longVal, 
            @RequestParam("str") String str) {
        Map<String, Object> map = new HashMap<>();

        map.put("intVal", intVal);
        map.put("longVal", longVal);
        map.put("str", str);

        return map;
    }

    /**
     * description: 传递数组 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/6/9 16:15 <br>
     *
     * @throws
     * @param intArr
     * @param longArr
     * @param strArr
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("/requestArray")
    @ResponseBody
    public Map<String, Object> requestArray(int[] intArr, long[] longArr, String[] strArr) {
        Map<String, Object> map = new HashMap<>();

        map.put("intArr", intArr);
        map.put("longArr", longArr);
        map.put("strArr", strArr);

        return map;
    }

    /**
     * description: 通过@RequesBody接收json字符串请求 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/6/9 16:27 <br>
     *
     * @throws
     * @param user
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("/requestJson")
    @ResponseBody
    public Map<String, Object> requestJson(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();

        map.put("userName", user.getUserName());
        map.put("note", user.getNote());
        map.put("id", user.getId());

        return map;
    }

    /**
     * description: 通过{...}和@PathVariable在url中传递参数 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/6/9 16:49 <br>
     *
     * @throws
     * @param id
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("/path/{id}")
    @ResponseBody
    public Map<String, Object> requestPath(@PathVariable("id") Long id) {
        Map<String, Object> map = new HashMap<>();

        map.put("id", id);

        return map;
    }

    /**
     * description: 格式化 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/6/9 17:47 <br>
     *
     * @throws
     * @param date
     * @param number
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("/format")
    @ResponseBody
    public Map<String, Object> format(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                      @NumberFormat(pattern = "#,###.##") Double number) {
        Map<String, Object> map = new HashMap<>();

        map.put("date", date);
        map.put("number", number);

        return map;
    }

    /**
     * description: 使用自定义转化器 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/6/9 17:48 <br>
     *
     * @throws
     * @param user
     * @return org.lql.database.mybatis.mybatisdomain.User
     */
    @RequestMapping("/converter")
    @ResponseBody
    public User converter(User user) {
        return user;
    }
}

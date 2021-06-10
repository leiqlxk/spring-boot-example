package org.lql.springmvc.restclient;

import org.lql.database.mybatis.mybatisdomain.User;
import org.lql.rest.vo.UserVo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: RestTemplateTest <br>
 * ProjectName: spring-boot-example <br>
 * description: 客户端请求RestTemplate，其底层通过HttpURLConnection实现<br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/10 17:45 <br>
 */
public class RestTemplateTest {

    // 简单GET请求
    public static UserVo getUser(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        // url、返回类型、参数
        UserVo userVo = restTemplate.getForObject("http://localhost:8080/user/{id}", UserVo.class, id);

        return userVo;
    }

    // 多参数GET请求
    public static List<UserVo> findUser(String userName, String note, Integer start, Integer limit) {
        RestTemplate restTemplate = new RestTemplate();

        // 使用map封装多个参数，提高可读性
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        map.put("note", note);
        map.put("start", start);
        map.put("limit", limit);

        // map中的key和URI中的参数一一对应
        String url = "http://localhost:8080/users/{userName}/{note}/{start}/{limit}";
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class, map);
        List<UserVo> userVos = responseEntity.getBody();

        return userVos;
    }

    // post请求传递json请求体
    public static User insertUser(UserVo userVo) {
        RestTemplate restTemplate = new RestTemplate();

        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 创建请求实体对象
        HttpEntity<UserVo> entity = new HttpEntity<>(userVo, headers);

        User user = restTemplate.postForObject("http://localhost:8080/user", entity, User.class);

        return user;
    }

    // DELETE请求
    public static void deleteUser(Long id) {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.delete("http://localhost:8080/user/{id}", id);
    }
}

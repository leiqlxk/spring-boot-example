package org.lql.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Title: I18nController <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/10 14:20 <br>
 */
@Controller
@RequestMapping("/i18n")
public class I18nController {

    // 注入国际化消息接口对象
    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/test")
    @ResponseBody
    public Map<String, Object> page(HttpServletRequest request) {
        // 后台获取国际化区域
        Locale locale = LocaleContextHolder.getLocale();

        // 获取国际化消息
        String msg = messageSource.getMessage("msg", null, locale);

        System.out.println("msg = " + msg);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);

        return map;
    }
}

package org.lql.springmvc.advice;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title: MyControllerAdvice <br>
 * ProjectName: spring-boot-example <br>
 * description: 控制器增强 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/10 14:39 <br>
 */
@ControllerAdvice(basePackages = {"org.lql.springmvc.controller.*"}, annotations = Controller.class)
public class MyControllerAdvice {

    // 绑定格式化、参数转换规则和增加验证器等
    @InitBinder
    public void initDataBinder(WebDataBinder dataBinder) {
        CustomDateEditor customDateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false);

        dataBinder.registerCustomEditor(Date.class, customDateEditor);
    }

    // 在执行控制器之前执行，可以初始化数据模型
    @ModelAttribute
    public void projectModel(Model model) {
        model.addAttribute("project_name", "test");
    }

    // 异常处理，使得被拦截的控制器方法发生异常时，都能用相同的视图响应
    @ExceptionHandler(Exception.class)
    public String exception(Model model, Exception e) {
        // 给数据模型添加异常信息
        model.addAttribute("exception_message", e.getMessage());

        return "exception";
    }
}

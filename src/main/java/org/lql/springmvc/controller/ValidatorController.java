package org.lql.springmvc.controller;

import org.lql.springmvc.domain.ValidatorPojo;
import org.lql.springmvc.validator.UserValidator;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: ValidatorController <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/9 21:04 <br>
 */
@Controller
@RequestMapping("/valid")
public class ValidatorController {

    /**
     * description: 注册自定义校验器 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/6/9 21:45 <br>
     *
     * @throws
    * @param binder
     * @return void
     */
    // 此注解会在执行控制器方法前，处理器会先执行其标注的方法
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 绑定验证器
        binder.setValidator(new UserValidator());

        // 定义日期参数格式，参数不再需要注解@DateTimeFormat，boolean参数表示是否允许为空
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
    }

    @RequestMapping("/validate")
    @ResponseBody
    public Map<String, Object> validate(@Valid @RequestBody ValidatorPojo validator, Errors errors) {
        Map<String, Object> map = new HashMap<>();

        // 获取错误列表
        List<ObjectError> oes = errors.getAllErrors();
        for (ObjectError oe : oes) {
            String key = null;
            String msg = null;

            // 字段错误
            if (oe instanceof FieldError) {
                FieldError fe = (FieldError) oe;
                key = fe.getField();
            }else {
                // 非字段错误
                key = oe.getObjectName();
            }

            // 错误信息
            msg = oe.getDefaultMessage();
            map.put(key, msg);
        }

        return map;
    }
}

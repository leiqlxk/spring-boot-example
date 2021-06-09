package org.lql.springmvc.validator;

import org.lql.database.mybatis.mybatisdomain.User;
import org.lql.springmvc.domain.ValidatorPojo;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Title: UserValidator <br>
 * ProjectName: spring-boot-example <br>
 * description: 自定义用户验证器 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/9 21:39 <br>
 */
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        // 对象为空
        if (o == null) {
            // 直接在参数处报错，这样就不能进入控制器的方法
            errors.rejectValue("", null, "用户不能为空");
            return;
        }

        // 强制转换
        User user = (User) o;
        if (StringUtils.isEmpty(user.getUserName())) {
            errors.rejectValue("userName", null, "用户名不能为空");
        }
    }
}

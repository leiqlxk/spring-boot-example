package org.lql.springmvc.convert;

import org.lql.database.mybatis.mybatisdomain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Title: StringToUser <br>
 * ProjectName: spring-boot-example <br>
 * description: 自定义转换器，一般在conversionService中注入三种接口Converter、GenericConverter、Formatter <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/9 17:43 <br>
 */
@Component
public class StringToUser implements Converter<String, User> {
    @Override
    public User convert(String s) {
        User user = new User();
        String[] strArr = s.split("-");
        Long id = Long.parseLong(strArr[0]);
        String userName = strArr[1];
        String note = strArr[2];
        user.setId(id);
        user.setUserName(userName);
        user.setNote(note);
        return user;
    }
}

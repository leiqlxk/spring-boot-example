package org.lql.iocannotation.pojo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Title: Dog <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/1 11:46 <br>
 */
@Component
// 当发现有多个相同类型bean时优先使用此类
@Primary
public class Dog implements Animal {
    public void use() {
        System.out.println("狗【" + Dog.class.getSimpleName() + "】是看门用的");
    }
}

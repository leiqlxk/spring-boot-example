package org.lql.iocannotation.pojo;

import org.springframework.stereotype.Component;

/**
 * Title: Cat <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/1 11:50 <br>
 */
@Component
public class Cat implements Animal {
    public void use() {
        System.out.println("猫【" + Dog.class.getSimpleName() + "】是抓老鼠的");
    }
}

package org.lql.redis;

import org.lql.redis.conifg.RedisConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Title: RdisTest <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/4 16:03 <br>
 */
public class RdisTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);

        redisTemplate.opsForValue().set("string1", "curry");
        redisTemplate.opsForHash().put("hash", "fied", "hvalue");
    }
}

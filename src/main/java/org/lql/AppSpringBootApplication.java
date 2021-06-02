package org.lql;

import org.lql.aop.aspect.MyAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Title: SpringBootApplication <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/1 14:32 <br>
 */
@SpringBootApplication
public class AppSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppSpringBootApplication.class);
    }
}

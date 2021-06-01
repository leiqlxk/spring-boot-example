package org.lql.iocannotation.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.lql.iocannotation.condition.DatabaseConditional;
import org.lql.iocannotation.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Title: AppConfig <br>
 * ProjectName: spring-boot-example <br>
 * description: 配置文件 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/1 10:43 <br>
 */
// 此注解代表该类为一个java配置文件，spring容器会根据它来生成IOC容器去装配bean
@Configuration
// 此注解开启扫描，但默认只会扫描当前包及其子包
@ComponentScan(basePackages = "org.lql.iocannotation.*", excludeFilters = {@ComponentScan.Filter(classes = {Service.class})})
public class AppConfig {

    // 此注解代表将方法返回的POJO装配到IOC容器中，其属性name定义这个bean的名称，如果没有配置则将方法名称initUser作为bean的名称保存到IOC容器中
    @Bean(name = "user")
    public User initUser() {
        User user = new User();
        user.setId(1L);
        user.setUserName("user_name_1");
        user.setNote("note_1");
        return user;
    }

    //    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    @Bean(name = "dataSource", destroyMethod = "close")
    @Conditional(DatabaseConditional.class)
    public DataSource getDataSource(@Value("${database.driverName}") String driverName, @Value("${url}") String url,
                                    @Value("${userName}") String userName, @Value("${password}") String password) {
        Properties properties = new Properties();
        properties.setProperty("driver", driverName);
        properties.setProperty("url", url);
        properties.setProperty("username", userName);
        properties.setProperty("password", password);

        DataSource dataSource = null;

        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataSource;
    }
    // 自定义第三方bean
}

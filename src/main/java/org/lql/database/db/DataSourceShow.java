package org.lql.database.db;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Title: DataSourceShow <br>
 * ProjectName: spring-boot-example <br>
 * description: 监测数据库连接池类型 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/3 10:20 <br>
 */
@Component
public class DataSourceShow implements ApplicationContextAware {

    ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        DataSource dataSource = applicationContext.getBean("dataSource",DataSource.class);
        System.out.println("----------------------------------");
        System.out.println(dataSource.getClass().getName());
        System.out.println("----------------------------------");
    }
}

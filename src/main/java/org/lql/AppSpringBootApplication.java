package org.lql;

import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.annotation.Around;
import org.lql.database.mybatis.plugin.MyPlugin;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

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
// 定义JPA接口扫描包路径
@EnableJpaRepositories(basePackages = "org.lql.database.jpa.jpadao")
// 定义JPA实体类扫描包路径
@EntityScan(basePackages = "org.lql.database.jpa.jpadomain")
// 定义mybatis的扫描,当不存在多个SqlSessionFactory或者SqlSessionTemplate时，可以不进行相应的引用配置
@MapperScan(
        //指定扫描包
        basePackages = "org.lql.database.mybatis.mybatisdao",
        //指定sqlSessionFactory，如果sqlSessionTemplate被指定则作废
        sqlSessionFactoryRef="sqlSessionFactory",
        //指定sqlSessionTemplate，将忽略sqlSessionFactory
        sqlSessionTemplateRef="sqlSessionTemplate",
        // 限定扫描接口，不常用
        annotationClass= Repository.class
)
// 使用注解启动缓存机制
@EnableCaching
// 指定扫描的包，用于扫描继承了MongoRepository的接口
//@EnableMongoRepositories(basePackages = "org.lql.mongodb.repository")
// 启用定时任务
@EnableScheduling
public class AppSpringBootApplication {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactorys;

    @Autowired
    private MyPlugin myPlugin;

    public static void main(String[] args) {
        SpringApplication.run(AppSpringBootApplication.class);
    }

    // 编码方式添加mybatis插件
    @PostConstruct
    public void addMysqlInterceptor() {
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactorys) {
            sqlSessionFactory.getConfiguration().addInterceptor(myPlugin);
        }
    }
}

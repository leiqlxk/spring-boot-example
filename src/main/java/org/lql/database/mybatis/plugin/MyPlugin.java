package org.lql.database.mybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

/**
 * Title: MyPlugin <br>
 * ProjectName: spring-boot-example <br>
 * description: mybatis拦截器 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/3 15:11 <br>
 */
@Component
// 定义拦截签名
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class MyPlugin implements Interceptor {
    Properties properties = null;

    // 拦截方法逻辑
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("插件拦截方法......");
        return invocation.proceed();
    }

    // 生成Mybatis拦截器代理对象
    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    // 设置插件属性
    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}

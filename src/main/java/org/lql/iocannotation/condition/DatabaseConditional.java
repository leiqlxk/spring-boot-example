package org.lql.iocannotation.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Title: DatabaseConditional <br>
 * ProjectName: spring-boot-example <br>
 * description: 定义初始化数据库的条件 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/1 17:45 <br>
 */
public class DatabaseConditional implements Condition {
    
    /**
     * description: 数据库装配条件 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/6/1 17:51 <br>
     * 
     * @throws
     * @param conditionContext 条件上下文
     * @param annotatedTypeMetadata 注释类型的元数据
     * @return boolean true 装配bean，否则不装配
     */ 
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

        // 取出环境配置
        Environment environment = conditionContext.getEnvironment();

        // 判断属性文件是否存在对应的数据库配置
        return environment.containsProperty("database.driverName")
                && environment.containsProperty("database.url")
                && environment.containsProperty("database.userName")
                && environment.containsProperty("database.password");
    }
}

package org.lql.springmvc.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Title: InterceptorConfig <br>
 * ProjectName: spring-boot-example <br>
 * description: 配置拦截器 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/9 23:22 <br>
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器到spring mvc机制，然后返回一个拦截器注册，拦截器顺序处理器前方法按注册顺序，处理器后方法和处理器完成方法与注册顺序相反
        InterceptorRegistration ir = registry.addInterceptor(new Interceptor1());
        // 指定拦截匹配模式，限制拦截器拦截请求
        ir.addPathPatterns("/mvcUserController/**");
    }
}

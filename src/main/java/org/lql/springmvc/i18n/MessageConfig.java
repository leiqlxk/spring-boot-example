package org.lql.springmvc.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Title: MessageConfig <br>
 * ProjectName: spring-boot-example <br>
 * description: 国际化配置，正常使用SessionLocaleResolver <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/10 13:45 <br>
 */
@Configuration
public class MessageConfig implements WebMvcConfigurer {

    // 国际化拦截器
    private LocaleChangeInterceptor lci;

    // 国际化解析器
    @Bean(name = "localeResolver")
    public LocaleResolver initLocaleResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();

        // 默认国际化区域
        sessionLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);

        return sessionLocaleResolver;
    }

    // 创建国际化拦截器
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        if (lci != null) {
            return lci;
        }

        lci = new LocaleChangeInterceptor();
        // 设置参数名
        lci.setParamName("language");

        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}

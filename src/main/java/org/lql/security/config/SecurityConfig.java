package org.lql.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.sql.DataSource;

/**
 * Title: SecurityConfig <br>
 * ProjectName: spring-boot-example <br>
 * description: 自定义security <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/11 10:57 <br>
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Value("${system.user.password.secret}")
    private String secret;

    /*@Autowired
    private UserDetailsService userDetailsService;*/

    // 使用用户名查询密码
    public static final String PWD_QUERY = "select user_name, pwd, available from t_user_boot where user_name = ?";

    // 使用用户名称查询角色信息
    public static final String ROLE_QUERY = "select u.user_name, r.role_name from t_user_boot u, t_role_boot r, t_user_role_boot ur " +
            "where u.id = ur.user_id and r.id = ur.role_id and u.user_name = ?";

    /**
     * description: 用来配置用户签名服务，主要是user-details机制，你还可以给予用户赋予角色 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/6/11 10:58 <br>
     * 
     * @throws
     * @param auth 签名管理器构造器，用于构建用户具体权限控制
     * @return void
     */ 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 密码编码器
        // BCrypt密码编码器
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
        PasswordEncoder encoder = new Pbkdf2PasswordEncoder(this.secret);

        // 使用内存存储
        /*auth.inMemoryAuthentication()
                // 设置密码编码器
                .passwordEncoder(encoder)
                // 注册用户admin，密码abc，并赋予USER和ADMIN的角色权限
                .withUser("admin")
                // 可通过encoder.encode("abc")得到加密后的密码
                .password("$2a$10$73WQIPaZoRZEFMa8vm6/jOmSpYksR21qkrQnQYvWlafc./qTjgR6W")
                // 该方法是authorities的简写，配置角色名称，实际上spring security会加入前缀"ROLE_"
                .roles("USER", "ADMIN")
                // 连接方法and
                .and()
                // 注册用户myuser，密码123456，并赋予USER的角色权限
                .withUser("myuser")
                .password("$2a$10$Pfm9gCjjok/uR4iHvSHOTOCX8yU2vWeFIVn0CxLyPX5cWYd3CRWSi")
                .roles("USER");*/

        // 使用数据库验证
        auth.jdbcAuthentication()
                .passwordEncoder(encoder)
                // 数据源
                .dataSource(dataSource)
                // 查询用户，自动判断密码是否一致
                .usersByUsernameQuery(PWD_QUERY)
                // 赋予权限
                .authoritiesByUsernameQuery(ROLE_QUERY);

        // 使用自定义认证服务
//        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    /**
     * description: 用来配置filter链 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/6/11 11:00 <br>
     * 
     * @throws
     * @param web spring web security对象
     * @return void
     */ 
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * description: 用来配置拦截保护的请求，比如什么请求放行，什么请求需要验证 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/6/11 11:00 <br>
     * 
     * @throws
     * @param http http安全请求对象
     * @return void
     */ 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 只需要通过验证就可以访问所有的请求
        // authorizeRequests 方法限定只对签名成功的用户请求，过滤请求
        http.authorizeRequests()
                // anyRequest 方法限定所有请求
                .anyRequest()
                // authenticated 方法对所有签名成功的用户允许方法
                .authenticated()
                // and 方法是连接词 ， formLogin 代表使用 Spring Security 默认的登录界面
                .and().formLogin()
                // httpBasic 方法说明启用 HTTP 基础认证
                .and().httpBasic();
    }

    public static void main(String[] args) {
        PasswordEncoder encoder = new Pbkdf2PasswordEncoder("uvwxyz");
        System.out.println(encoder.encode("123456"));
    }
}

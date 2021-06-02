package org.lql.aop;

import org.lql.aop.interceptor.MyInterceptor;
import org.lql.aop.invocation.ProxyBean;
import org.lql.aop.service.HelloService;
import org.lql.aop.service.HelloServiceImpl;

/**
 * Title: AopTest <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/2 11:40 <br>
 */
public class AopTest {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        // 按约定获取proxy
        HelloService proxy = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());
        proxy.sayHello("Curry");
        System.out.println("##################### name is null ######################");
        proxy.sayHello(null);
    }
}

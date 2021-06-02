package org.lql.aop.interceptor;

import org.lql.aop.invocation.Invocation;

import java.lang.reflect.InvocationTargetException;

/**
 * Title: MyInterceptor <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/2 11:20 <br>
 */
public class MyInterceptor implements Interceptor {
    public boolean before() {
        System.out.println("before.....");
        return true;
    }

    public boolean after() {
        System.out.println("after.....");
        return true;
    }

    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("around before .....");
        Object object = invocation.proceed();
        System.out.println("around after .....");
        return object;
    }

    public void afterReturning() {
        System.out.println("after returning ......");
    }

    public void afterThrowing() {
        System.out.println("after throwing ......");
    }

    public boolean userAround() {
        return true;
    }
}

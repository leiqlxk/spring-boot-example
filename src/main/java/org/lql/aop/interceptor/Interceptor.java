package org.lql.aop.interceptor;

import org.lql.aop.invocation.Invocation;

import java.lang.reflect.InvocationTargetException;

/**
 * Title: Interceptor <br>
 * ProjectName: spring-boot-example <br>
 * description: 拦截器接口 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/2 11:08 <br>
 */
public interface Interceptor {

    // 事前方法
    public boolean before();

    // 事后方法
    public boolean after();

    /**
     * description: 取代原有事件方法 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/6/2 11:11 <br>
     *
     * @throws
     * @param invocation  回调参数，可以通过它的proceed方法，回调原有事件
     * @return java.lang.Object 原有事件返回对象
     */
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException;

    // 是否返回方法，事件没有发生异常执行
    public void afterReturning();

    // 事后异常方法，当事件发生异常后执行
    public void afterThrowing();

    // 是否使用around方法取代原有方法
    boolean userAround();
}

package org.lql.aop.invocation;

import org.lql.aop.interceptor.Interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Calendar;

/**
 * Title: ProxBean <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/2 11:35 <br>
 */
public class ProxyBean implements InvocationHandler {

    private Object target = null;

    private Interceptor interceptor = null;

    /**
     * description: 绑定代理对象 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/6/2 11:36 <br>
     *
     * @throws
     * @param object 被代理对象，其必须存在接口
     * @param interceptor 拦截器
     * @return java.lang.Object 代理对象
     */
    public static Object getProxyBean(Object object, Interceptor interceptor) {
        ProxyBean proxyBean = new ProxyBean();
        // 保存被代理对象
        proxyBean.target = object;
        // 保存拦截器
        proxyBean.interceptor = interceptor;

        // 生成代理对象
        Object proxy = Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), proxyBean);

        return proxy;
    }

    /**
     * description: 处理代理对象方法逻辑 <br>
     *
     * @author: leiql <br>
     * @version: 1.0 <br>
     * @since: 2021/6/2 11:50 <br>
     *
     * @throws
     * @param proxy 代理对象
     * @param method 当前方法
     * @param args 运行参数
     * @return java.lang.Object 调用结果
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 异常标识
        boolean exceptionFlag =false;

        Invocation invocation = new Invocation(this.target, method, args);
        Object retObject = null;
        try {
            if (this.interceptor.before()) {
                retObject = this.interceptor.around(invocation);
            }else {
                retObject = method.invoke(this.target, args);
            }
        }catch (Exception e) {
            exceptionFlag = true;
        }

        this.interceptor.after();
        if (exceptionFlag) {
            this.interceptor.afterThrowing();
        }else {
            this.interceptor.afterReturning();
            return retObject ;
        }
        return null;
    }
}

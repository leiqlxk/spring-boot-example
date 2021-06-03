package org.lql.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.lql.aop.advice.UserValidator;
import org.lql.aop.advice.UserValidatorImpl;
import org.lql.aop.domain.User;
import org.springframework.stereotype.Component;

/**
 * Title: MyAspect <br>
 * ProjectName: spring-boot-example <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/6/2 14:50 <br>
 */
// 切面声明
@Aspect
@Component
public class MyAspect {

    @DeclareParents(value = "org.lql.aop.service.UserServiceImpl", defaultImpl = UserValidatorImpl.class)
    public UserValidator userValidator;

    // 定义切点
    @Pointcut("execution(* org.lql.aop.service.UserServiceImpl.printUser(..))")
    public void pointCut() {
    }

    // 定义切面
    @Before("pointCut() && args(user)")
    public void before(JoinPoint joinPoint, User user) {
        Object[] objects = joinPoint.getArgs();
        System.out.println("objects:" + objects);
        System.out.println("before.....");
    }

    @After("execution(* org.lql.aop.service.UserServiceImpl.printUser(..))")
    public void after() {
        System.out.println("after.....");
    }

    @AfterReturning("execution(* org.lql.aop.service.UserServiceImpl.printUser(..))")
    public void afterReturning() {
        System.out.println("after returning......");
    }

    @AfterThrowing("execution(* org.lql.aop.service.UserServiceImpl.printUser(..))")
    public void afterThrowing() {
        System.out.println("after throwing......");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before ......");
        joinPoint.proceed();
        System.out.println("around after......");
    }
}

package com.example.mytest.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author maruko
 * @Date 2022/11/22 17:04
 * @Version 1.0
 */
@Aspect
@Component
public class AspectTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectTest.class);

    @Pointcut("execution(* com.example.mytest.controller.MyController.*(..))")
    public void pointExpression() {

    }

    @Before("pointExpression()")
    public void before(JoinPoint joinPoint) {
//        System.err.println(joinPoint.toString());
//        Object[] args = joinPoint.getArgs();
//        for (Object arg : args) {
//            System.err.println(arg);
//        }

//        System.err.println(joinPoint.getSignature().toLongString());
//        System.err.println(joinPoint.getSignature().toShortString());
//        System.err.println(joinPoint.getSignature().getName());
//        System.err.println(joinPoint.getSignature().toString());
//        System.err.println(joinPoint.getKind());
//        System.err.println(joinPoint.getTarget().toString());
//        System.err.println(joinPoint.getThis().toString());
//        System.err.println(joinPoint.getStaticPart());
//        System.err.println(joinPoint.getSourceLocation());
//        System.err.println(joinPoint.toLongString());
//        System.err.println(joinPoint.toShortString());
        System.err.println("前置通知，方法名为：" + joinPoint.getSignature().getName() + " 参数为：" + Arrays.asList(joinPoint.getArgs()));
    }

//    @After("execution(* com.zjf.demo.controller.UserController.*(..))")
    @After("pointExpression()")
    public void after(JoinPoint joinPoint) {
        System.err.println("后置通知，方法名为：" + joinPoint.getSignature().getName() + " 参数为：" + Arrays.asList(joinPoint.getArgs()));


    }

//    @AfterReturning(pointcut = "execution(* com.zjf.demo.controller.UserController.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.err.println("返回通知，方法名为：" + joinPoint.getSignature().getName() + " 参数为：" + Arrays.asList(joinPoint.getArgs()) + " 返回结果为：" + result);
    }

//    @AfterThrowing(value = "execution(* com.zjf.demo.controller.UserController.*(..))", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        System.err.println("异常通知，方法名为：" + joinPoint.getSignature().getName() + " 参数为：" + Arrays.asList(joinPoint.getArgs()) + " 异常为：" + exception);
    }

//    @Around("execution(* com.zjf.demo.controller.UserController.*(..))")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();

        List<Object> args = Arrays.asList(joinPoint.getArgs());

        Object result = null;
        try {
            //前置通知
            System.err.println("前置通知，方法名为：" + joinPoint.getSignature().getName() + " 参数为：" + Arrays.asList(joinPoint.getArgs()));

            result = joinPoint.proceed();

            //返回通知
            System.err.println("返回通知，方法名为：" + joinPoint.getSignature().getName() + " 参数为：" + Arrays.asList(joinPoint.getArgs()) + " 返回结果为：" + result);
        } catch (Throwable e) {
            // 异常通知
            System.err.println("异常通知，方法名为：" + joinPoint.getSignature().getName() + " 参数为：" + Arrays.asList(joinPoint.getArgs()) + " 异常为：" + e);
            throw new RuntimeException(e);
        }

        //后置通知
        System.err.println("后置通知，方法名为：" + joinPoint.getSignature().getName() + " 参数为：" + Arrays.asList(joinPoint.getArgs()));

        return result;
    }


}

package com.dugu.test.service.aop.impl;

import com.alibaba.fastjson.JSON;
import com.dugu.test.service.aop.MonitorLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author cihun
 * @date 2022-04-24 8:01 下午
 */
@Aspect
@Component
public class MonitorLogAopInterceptor {


    // 1.注解
    // @Pointcut("@annotation(com.dugu.test.service.aop.MonitorLog)")
    // public void execute() {}

    //2、Execution表达式
    //@Pointcut("execution (* com.dugu.test.service.aop.*.*(..))")
    //public void execute() {}

    //3、Execution表达式
//    @Pointcut(value = "bean(*ServiceImpl)")
//    public void execute() { }

    @Pointcut("within(com.dugu.test.service.aop.impl.*)")
    public void execute() {}

    @Around("execute()")
    Object around(ProceedingJoinPoint pj) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object object = pj.proceed(pj.getArgs());
        Long executeTime = System.currentTimeMillis() - beginTime; // 执行时长(毫秒)
        String interfaceParams = JSON.toJSONString(pj.getArgs()); // 接口入参
        String result = JSON.toJSONString(object);// 接口返回结果
        String interfaceCode = pj.getSignature().getName();// 接口名称
        MethodSignature methodSignature = (MethodSignature) pj.getSignature();
        Method targetMethod = methodSignature.getMethod();
        MonitorLog sysLog = targetMethod.getAnnotation(MonitorLog.class); // 从接口注解中获取注解信息
        // 建一张表，调用持久化服务存储日志，上述写的信息都可以存储到表中
        System.out.println(interfaceParams);
        System.out.println(result);
        return object;
    }

    @AfterThrowing(value = "execute()", throwing = "runtimeException")
    public void afterThrowingAdvice(JoinPoint pj, RuntimeException runtimeException) {
        System.out.println("exception=" + pj);
        // 接口调用后异常处理
    }
}

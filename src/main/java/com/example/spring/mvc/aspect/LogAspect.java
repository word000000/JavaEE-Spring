package com.example.spring.mvc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @Author:GQM
 * @Date:created in 22:34 2020/4/21
 * @Description:
 * @Modifyed_By:
 */
@Aspect
@Component
public  class LogAspect {

    @Around("execution(* com.example.spring.mvc.service.*.*(..))")
    public  Object processTx(ProceedingJoinPoint jp) throws java.lang.Throwable {
        Object object;
        String className = jp.getTarget().getClass().getSimpleName();
        String methodName = jp.getSignature().getName();
        Timestamp timestampBefore = new Timestamp(System.currentTimeMillis());
        System.out.println("["+timestampBefore+"作业管理系统]" +className+"."+methodName+"()  开始事务.....");
        object = jp.proceed();
        Timestamp timestampAfter = new Timestamp(System.currentTimeMillis());
        System.out.println("["+timestampBefore+"作业管理系统]" +className+"."+methodName+"()  结束事务.....");
        return object;
    }

    @AfterThrowing(throwing = "throwable",value = "execution(* com.example.spring.mvc.service.*.*(..))")
    public void afterThrowing(Throwable throwable) {
        System.out.println("异常："+throwable);
    }

}

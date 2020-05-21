package com.example.spring.mvc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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

    @Around(value = "execution(* com.example.spring.mvc.service.*.*(..))")
    public  Object logAspect(ProceedingJoinPoint jp){
        Object object = null;
        String className = jp.getTarget().getClass().getSimpleName();
        String methodName = jp.getSignature().getName();
        Timestamp timestampBefore = new Timestamp(System.currentTimeMillis());
        String argStr = "";
        System.out.println("["+timestampBefore+"作业管理系统]" +className+"."+methodName+"()  开始事务.....");

//        try{

        try {
            object = jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

//        }catch (Throwable ex) {
//            Timestamp timestampException = new Timestamp(System.currentTimeMillis());
//            System.out.println("["+timestampException+"作业管理系统] 捕获到异常"+ex);
//        }
        Timestamp timestampAfter = new Timestamp(System.currentTimeMillis());
        System.out.println("["+timestampAfter+"作业管理系统]" +className+"."+methodName+"()  结束事务.....");
        return object;
    }

    @AfterReturning( value = "execution(* com.example.spring.mvc.service.*.*(..))",returning="returnValue")
    public void logReturnValue(JoinPoint jp, Object returnValue){
        String className = jp.getTarget().getClass().getSimpleName();
        String methodName = jp.getSignature().getName();
        Timestamp timestampAfter = new Timestamp(System.currentTimeMillis());
        System.out.println("["+timestampAfter+"作业管理系统]" +className+"."+methodName+"()  返回参数....."+returnValue.toString());
    }

}

package com.example.demo.Services;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {


    @Before("execution( * com.example.demo.Controllers.*.*(..))")
    public void logBeforeControllerMethods() {
        System.out.println("Before Advice");
    }
    @AfterReturning(value = "execution( * com.example.demo.Controllers.*.*(..))",returning = "result")
    public void logAfterControllerMethods(Object result) {
        System.out.println("AfterReturning Advice " + result);
    }

    @AfterThrowing(value = "execution( * com.example.demo.Controllers.*.*(..))",throwing = "exception")
    public void logAfterControllerMethodThrowException(Exception exception){
        System.out.println("Exception thrown from controller method"+exception);
    }

    @Around("execution( * com.example.demo.Controllers.*.*(..))")
    public Object logAroundControllerMethods(ProceedingJoinPoint jointPoint) throws Throwable{
        long start = System.nanoTime();
        System.out.println(start);
        Object proceed = jointPoint.proceed();
        long executionTime = System.nanoTime()- start;
        System.out.println(jointPoint.getSignature()+"executed in"+executionTime+"ms");
        return proceed;


    }

}

package com.example.api02.aop;


import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class TimeAdvice {


    @Around("execution(* com.example.api02.service.*.*(*) )")

    public Object logTime(ProceedingJoinPoint pjp)throws Throwable{

        Object result = null;

        long start = System.currentTimeMillis();

        try{

            log.info("-----------------------------");

            result = pjp.proceed();


        }catch(Throwable e){

            log.error(e);

        }

        long end = System.currentTimeMillis();

        log.info("========================================");
        log.info("========================================");
        log.info("TIME: " + (end- start));
        log.info("========================================");

        return result;
    }
}

package com.example.todoapispring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class TimeMonitorAspect {

    @Around("@annotation(TimeMonitor)")
    public void logtime(ProceedingJoinPoint joinPoint){
        long startTime = System.currentTimeMillis();

        try{
            joinPoint.proceed();
        }
        catch(Throwable e){
            System.out.println("Something went wrong during the execution");
        }
        finally {
            long endTime = System.currentTimeMillis();

            long totalExecutionTime = endTime - startTime;

            System.out.println("Total execution time of the method is: " + totalExecutionTime + "ms..");

        }




    }
}

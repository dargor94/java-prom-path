package org.dargor.aop.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Log4j2
@Aspect
@Component
public class LoggingAspect {
    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void serviceMethods() {
        //Metodo vacio, las implementacions son los advices
    }

    //Advices

    @Before("execution(* org.dargor.aop.service.UserService.*(..))")
    public void logBeforeGetEmployee(JoinPoint joinPoint) {
        log.info("logBeforeGetEmployee = " + joinPoint.getSignature().getName());
    }

    @After("serviceMethods()")
    public void logAfterServiceMethods(JoinPoint joinPoint) {
        log.info("logAfterServiceMethods = " + joinPoint.getSignature().getName());
    }

    @After("execution(* org.dargor.aop.service.UserService.*(..))")
    public void logAfterGetEmployee(JoinPoint joinPoint) {
        log.info("logAfterGetEmployee = " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* org.dargor.aop.service.UserService.*(..))", throwing = "ex")
    public void logUserException(Exception ex) {
        log.error("ex.getMessage() = " + ex.getMessage());
    }

    @AfterReturning(value = "execution(* org.dargor.aop.service.*.get*(..))", returning = "value")
    public void logAfterReturningUser(Object value) {
        log.info("value = " + value);
    }

    @Around("execution(* org.dargor.aop.service.UserService.getUsersIds(..))")
    public Object usersAroundAdvice(ProceedingJoinPoint proceedingJoinPoint /*Parametro obligatorio para arounds*/) {
        log.info("Start usersAroundAdvice method invoking");
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        log.info("After invoking usersAroundAdvice method. Return value=" + value);
        return value;
    }
}

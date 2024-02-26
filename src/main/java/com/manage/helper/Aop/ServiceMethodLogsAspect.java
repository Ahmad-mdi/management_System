package com.manage.helper.Aop;

import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
public class ServiceMethodLogsAspect {
    private static final Logger logger = LoggerFactory.getLogger(ServiceMethodLogsAspect.class);
    //input methods getter:
    @AfterReturning(pointcut = "execution(* com.manage.service..*(..))", returning = "result")
    public void logServiceMethods (JoinPoint joinPoint , Object result){
        logger.info("Methode Name: " + joinPoint.getSignature().getName() + "returned" + result);
    }

}

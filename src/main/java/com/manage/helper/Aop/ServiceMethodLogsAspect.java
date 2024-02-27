package com.manage.helper.Aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.Map;

@Aspect
@Component
@AllArgsConstructor
public class ServiceMethodLogsAspect {
    //input methods getter:
    @AfterReturning(pointcut = "execution(* com.manage.service..*(..))", returning = "result")
    public void logServiceMethods (JoinPoint joinPoint,Object result){
        saveLogToJsonFile(joinPoint.getSignature().getName(),result);
    }

    private void saveLogToJsonFile (String methodName,Object result){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonLog = objectMapper.writeValueAsString(Map.of("methodName",methodName,"result",result));
            Files.write(Paths.get("logs/log_service_json/log.json"),
                    Collections.singletonList(jsonLog),
                    StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

}

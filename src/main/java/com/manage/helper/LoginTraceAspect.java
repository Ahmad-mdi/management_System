package com.manage.helper;

import com.manage.model.LoginTrace;
import com.manage.repository.user_trace.LoginTraceReportRepository;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
//aop config
@Aspect
@Component
@AllArgsConstructor
public class LoginTraceAspect {

    private final LoginTraceReportRepository loginTraceRepository;
    @AfterReturning(value = "execution(* com.manage.service.user.UserServiceImpl.login(..))", returning = "result")
    public void successfulLogin(JoinPoint joinPoint, Object result) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String username = (String) joinPoint.getArgs()[0];
        String ipAddress = request.getRemoteAddr();
        saveLoginTrace(username, ipAddress, true);
    }


    @AfterThrowing(value = "execution(* com.manage.service.user.UserServiceImpl.login(..))", throwing = "exception")
    public void failedLogin(JoinPoint joinPoint, Throwable exception) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String username = (String) joinPoint.getArgs()[0];
        String ipAddress = request.getRemoteAddr();
        saveLoginTrace(username, ipAddress, false);
    }

    private void saveLoginTrace(String username, String ipAddress, boolean isSuccess) {
        LoginTrace loginTrace = new LoginTrace();
        loginTrace.setUsername(username);
        loginTrace.setIp(ipAddress);
        loginTrace.setLoginTime(LocalDateTime.now());
        loginTrace.setSuccess(isSuccess);
        loginTraceRepository.save(loginTrace);
    }
}
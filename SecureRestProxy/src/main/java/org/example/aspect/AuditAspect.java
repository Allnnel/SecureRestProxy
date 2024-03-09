package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.model.AuditLog;
import org.example.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

@Component
@Aspect
public class AuditAspect {
    private final HttpServletRequest request;
    private final AuditLogService service;
    @Autowired
    public AuditAspect(HttpServletRequest request, AuditLogService service) {
        this.request = request;
        this.service = service;
    }

    @Before("execution(* org.example.controller..*(..))")
    public void beforeUserAction(JoinPoint joinPoint) {
        String ipAddress = request.getRemoteAddr();
        Date date = new Date();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        String methodName = joinPoint.getSignature().getName();
        String parameters = Arrays.toString(joinPoint.getArgs());

        service.save(new AuditLog(ipAddress, true, date, username, methodName, parameters));
    }

}

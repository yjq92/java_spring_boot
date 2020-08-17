package com.hqyj.javaSpringBoot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class ControllerAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("execution(public * com.hqyj.javaSpringBoot.modules.*.controller.*.*(..)")
    @Order(1)
    public void controllerPointCut() {
    }

    @Before(value = "com.hqyj.javaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public void beforeController(JoinPoint joinPoint) {
        LOGGER.debug("====== this is before controller =====");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER.debug("请求来源" + request.getRemoteAddr());
        LOGGER.debug("请求URL" + request.getRequestURL().toString());
        LOGGER.debug("请求方式" + request.getMethod());
        LOGGER.debug("响应方法" + joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName());
        LOGGER.debug("请求参数" + Arrays.toString(joinPoint.getArgs()));

    }

    @Around(value = "com.hqyj.javaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public Object arroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug("==== This is around controller ==== ");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @After(value = "com.hqyj.javaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public void afterController() {
        LOGGER.debug("==== This is after controller ====");
    }
}

package com.hqyj.javaSpringBoot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class ControllerAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("execution(public * com.hqyj.javaSpringBoot.modules.*.controller.*.*(..))") //切点 controllerPointCut，
    @Order(1)
    public void controllerPointCut() {
    }

    //将通知方法与切片进行绑定，访问控制器时，调用通知方法，实现业务逻辑的增强
    @Before(value = "com.hqyj.javaSpringBoot.aspect.ControllerAspect.controllerPointCut()")
    public void beforeController(JoinPoint joinPoint) {
        //需求：打印request相关日志信息
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

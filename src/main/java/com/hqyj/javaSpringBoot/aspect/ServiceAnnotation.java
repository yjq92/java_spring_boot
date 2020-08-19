package com.hqyj.javaSpringBoot.aspect;

import java.lang.annotation.*;

/**
 * 注解方式进行精确切入
 */
@Target(ElementType.METHOD) //注解写在方法上面
@Documented //文档类型
@Retention(RetentionPolicy.RUNTIME) //作用范围：RunTime
public @interface ServiceAnnotation {
    String value() default "qaqa"; //设置属性默认值
}

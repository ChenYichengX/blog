package com.chen.blog.aspect;

import java.lang.annotation.*;

/**
 * @ClassName SystemLog
 * @Author ChenYicheng
 * @Description 自定义日志注解
 * @Date 2021/10/28 17:36
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

    String serviceName() default "";

    String module() default "";

    String action() default "";
}

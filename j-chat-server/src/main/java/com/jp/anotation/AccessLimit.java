package com.jp.anotation;

import java.lang.annotation.*;

/**
 * Redis限流注解
 * @Author JP
 * @Date 2024/11/1 7:38
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {

    /**
     * 限制周期(秒)
     */
    int seconds();

    /**
     * 规定周期内限制次数
     */
    int maxCount();

    /**
     * 触发限制时的消息提示
     */
    String msg() default "操作过于频繁，请稍后再试";
}

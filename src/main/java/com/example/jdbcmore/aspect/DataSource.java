package com.example.jdbcmore.aspect;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @Author: liulang
 * @Date: 2020/7/23 20:40
 */


@Documented
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    String value() default "default";
}

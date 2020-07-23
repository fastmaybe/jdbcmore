package com.example.jdbcmore.aspect;

import com.example.jdbcmore.config.DataSourceContextHolder1;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: liulang
 * @Date: 2020/7/23 20:43
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);


    @Before("@annotation(dataSource)")
    public void changeDataSource(JoinPoint joinPoint,DataSource dataSource){
        String ds = dataSource.value();
        DataSourceContextHolder1.setDBType(ds);
    }

    /**
     * 建议加个后置切面 将其tread 值移除 避免可能缓存泄露的情况
     * @param joinPoint
     * @param dataSource
     */
    @After("@annotation(dataSource)")
    public void removeDataSource(JoinPoint joinPoint,DataSource dataSource){
       DataSourceContextHolder1.clearDBType();
    }

}

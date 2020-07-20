package com.example.jdbcmore.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JtaDataSourceConfig {

    //jta数据源primarydb
    @Bean( name="primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "primarydb")
    public DataSource primaryDataSource() {
        //这里是关键，返回的是AtomikosDataSourceBean，所有的配置属性也都是注入到这个类里面
        return new AtomikosDataSourceBean();
    }

    //jta数据源secondarydb
    @Bean( name="secondaryDataSource")
    @ConfigurationProperties(prefix = "secondarydb")
    public DataSource secondaryDataSource()  {
        return new AtomikosDataSourceBean();
    }

    //primaryJdbcTemplate使用primaryDataSource数据源
    @Bean
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("primaryDataSource") DataSource primaryDataSource) {
        return new JdbcTemplate(primaryDataSource);
    }

    //secondaryJdbcTemplate使用secondaryDataSource数据源
    @Bean
    public JdbcTemplate secondaryJdbcTemplate(
            @Qualifier("secondaryDataSource") DataSource secondaryDataSource) {
        return new JdbcTemplate(secondaryDataSource);
    }
}

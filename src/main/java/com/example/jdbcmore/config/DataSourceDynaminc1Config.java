package com.example.jdbcmore.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.jdbcmore.config.transaction.MyTransactionsFactory;
import com.mysql.cj.jdbc.MysqlXADataSource;
import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liulang
 * @Date: 2020/7/23 17:20
 */
@Configuration
@MapperScan("com.example.jdbcmore.mapper")
public class DataSourceDynaminc1Config {

    private static Logger logger= LogManager.getLogger(DataSourceDynaminc1Config.class);

    @ConfigurationProperties("spring.datasource.default")
    @Component("defaultDataSourceConf")
    @Data
    public static class DefaultDataSourceConf{
        private String url;
        private String username;
        private String password;
        private String driverClassName;
    }

    // 可以直接ConfigurationProperties 注入  为了看日志  改成手动
//    @Bean
//    @ConfigurationProperties("spring.datasource.default")
//    public DataSource defaultDataSource(){
//        return new DruidDataSource();
//    }

    @Bean
//    @ConfigurationProperties("spring.datasource.default")
    public DataSource defaultDataSource(@Qualifier("defaultDataSourceConf")DefaultDataSourceConf conf){
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(conf.url);
        mysqlXADataSource.setUser(conf.username);
        mysqlXADataSource.setPassword(conf.password);
        AtomikosDataSourceBean atomikosDataSource = new AtomikosDataSourceBean();
//        atomikosDataSource.setUniqueResourceName(uniqueResourceName);
        atomikosDataSource.setXaDataSource(mysqlXADataSource);
//        atomikosDataSource.setMinPoolSize(minPoolSize);
//        atomikosDataSource.setMaxPoolSize(maxPoolSize);
//        atomikosDataSource.setTestQuery("SELECT 1");


        logger.info("   ========== default datasource ==========");
        logger.info("[ default datasource url ] {}",conf.getUrl());
        logger.info("[ default datasource dbType ] {}",atomikosDataSource.getClass());

        return atomikosDataSource;
    }

    @ConfigurationProperties("spring.datasource.slave")
    @Component("slaveDataSourceConf")
    @Data
   public static class SlaveDataSourceConf{
        private String jdbcUrl;
        private String username;
        private String password;
        private String driverClassName;
    }

    @Bean
    public DataSource slaveDataSource(@Qualifier("slaveDataSourceConf")SlaveDataSourceConf conf){


        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(conf.jdbcUrl);
        mysqlXADataSource.setUser(conf.username);
        mysqlXADataSource.setPassword(conf.password);
        AtomikosDataSourceBean atomikosDataSource = new AtomikosDataSourceBean();
//        atomikosDataSource.setUniqueResourceName(uniqueResourceName);
        atomikosDataSource.setXaDataSource(mysqlXADataSource);
//        atomikosDataSource.setMinPoolSize(minPoolSize);
//        atomikosDataSource.setMaxPoolSize(maxPoolSize);
//        atomikosDataSource.setTestQuery("SELECT 1");


        logger.info("   ========== slave datasource ==========");
        logger.info("[ slave datasource url ] {}",conf.jdbcUrl);
        logger.info("[ slave datasource dbType ] {}",atomikosDataSource.getClass());
        return atomikosDataSource;
    }


    @Bean
    public DataSourceDynaminc1 dataSourceDynaminc1(@Qualifier("defaultDataSource") DataSource defaultDataSource,
                                                   @Qualifier("slaveDataSource") DataSource slaveDataSource){

        Map<Object,Object> map = new HashMap<>(10);
        map.put("default", defaultDataSource);
        map.put("slave", slaveDataSource);
        DataSourceDynaminc1 instance = DataSourceDynaminc1.getInstance();
        // 设置多数据源
        instance.setTargetDataSources(map);

        //设置默认数据源
//        logger.info("默认数据源： {}" ,);
        instance.setDefaultTargetDataSource(defaultDataSource);
        return instance;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceDynaminc1") DataSourceDynaminc1 dataSourceDynaminc1) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSourceDynaminc1);

        //设置别名
//        bean.setTypeAliasesPackage("");


        bean.setTransactionFactory(new MyTransactionsFactory());
//        bean.se
        //设置mapper扫描地址
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/*.xml"));

       return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}

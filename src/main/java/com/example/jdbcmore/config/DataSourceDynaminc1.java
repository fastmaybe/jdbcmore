package com.example.jdbcmore.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;


/**
 * 1 注册多个数据源
 * 2 设置数据源
 * @author liulang
 */

public class DataSourceDynaminc1 extends AbstractRoutingDataSource {

    private static DataSourceDynaminc1 instance;
    private static final byte[] LOCK=new byte[0];
    private static Map<Object,Object> dataSourceMap=new HashMap<Object, Object>();



    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        dataSourceMap.putAll(targetDataSources);
        super.afterPropertiesSet();    // 必须添加该句，否则新添加数据源无法识别到
    }

    public String getCurrentDatasourceKey()
    {
        return DataSourceContextHolder1.getDataSourceKey();
    }

    public static synchronized DataSourceDynaminc1 getInstance(){
        if(instance==null){
            synchronized (LOCK){
                if(instance==null){
                    instance=new DataSourceDynaminc1();
                }
            }
        }
        return instance;
    }


    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder1.getDBType();
    }
}

package com.example.jdbcmore.config;

/**
 * @Author: liulang
 * @Date: 2020/7/23 17:23
 */
public class DataSourceContextHolder1 {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static synchronized void setDBType(String dbType){
        contextHolder.set(dbType);
    }

    public static String getDBType(){
        return contextHolder.get();
    }

    public static void clearDBType(){
        contextHolder.remove();
    }

    public static String getDataSourceKey() {
        String key = contextHolder.get();
        if (null == key) {
            key = "default";   //默认default
        }
        return key;
    }


}

package com.example.jdbcmore.config.transaction;

/**
 * @Author: liulang
 * @Date: 2020/7/24 15:28
 */

import com.example.jdbcmore.config.DataSourceDynaminc1;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.transaction.SpringManagedTransaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 在加入声明式 事务时候如果没有@Transactional注解，在执行完第一条userMapper.getById(10)操作后连接会归还给资源池，
 * 再次执行userMapper.updateStatus(1)时，connection是null，因此还会执行openConnection()，
 * 之后会从我们配置的AbstractRoutingDataSource动态数据源获取连接，完成数据源切换。
 *
 * 但是一旦我们加上了@Transactional注解，将会保留这个个连接，以致于第二次对user库写操作时，
 * 并不会执行openConnection()方法，仍然使用之前的连接，这个时候数据源就没有动态切换
 *
 * 因此 我们需要重写 获取连接的方法  变成动态获取
 *
 */
public class MyManagedTransaction  extends SpringManagedTransaction {
    private static final Log LOGGER = LogFactory.getLog(MyManagedTransaction.class);

    DataSource dataSource;
    ConcurrentHashMap<String, Connection> map = new ConcurrentHashMap<>();

    public MyManagedTransaction(DataSource dataSource) {
        super(dataSource);
        this.dataSource = dataSource;
    }


    @Override
    public Connection getConnection() throws SQLException {
        DataSourceDynaminc1 dynamicDatasource = (DataSourceDynaminc1) dataSource;
        String key = dynamicDatasource.getCurrentDatasourceKey();
        if (map.containsKey(key)) {
            return map.get(key);
        }
        Connection con = dataSource.getConnection();
        map.put(key, con);
        return con;
    }
}

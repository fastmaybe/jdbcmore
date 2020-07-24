package com.example.jdbcmore.config.transaction;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;

import javax.sql.DataSource;

/**
 * @Author: liulang
 * @Date: 2020/7/24 15:40
 */

/**
 * 重写了getConnection方法，将connection也变成动态获取的。
 * 另外，SpringManagedTransaction也是通过工厂类生产的，因此需要重写工厂类如下：
 *
 * 最后，SqlSessionFactory类默认使用的是SpringManagedTransactionFactory，别忘了换成我们自己的MyTransactionsFactory，如下：
 */
public class MyTransactionsFactory  extends SpringManagedTransactionFactory {

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new MyManagedTransaction(dataSource);
    }
}

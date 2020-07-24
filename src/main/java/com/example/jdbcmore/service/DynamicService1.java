package com.example.jdbcmore.service;

import com.example.jdbcmore.aspect.DataSource;
import com.example.jdbcmore.mapper.UserMapper;
import com.example.jdbcmore.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: liulang
 * @Date: 2020/7/23 20:50
 */
@Service
public class DynamicService1 {


    @Transactional(rollbackFor = Exception.class)
    @DataSource("slave")
    public void testNoTra(){

        User slave = User.builder().id(16).name("slave").build();
        userMapper.insert(slave);

//        int a =10/0;
    }

    @Resource
    private UserMapper userMapper;

    public void testNoTra3(){

        User slave = User.builder().id(16).name("slave").build();
        userMapper.insert(slave);

        User slave2 = User.builder().id(16).name("master").build();
        userMapper.insertMaster(slave2);
//        int a =10/0;
    }
    @Transactional
    @DataSource("slave")
    public void testHasTra3(){

        User slave = User.builder().id(18).name("slave").build();
        userMapper.insert(slave);

        User slave2 = User.builder().id(18).name("master").build();
        userMapper.insertMaster(slave2);

    }


    public void testNoTra2(){
        //主数据源
        User slave = User.builder().id(14).name("master").build();
        userMapper.insert(slave);

        //slave
        User user = userMapper.selectByPrimaryKey(2);
        System.out.println(user.getName());

        // 理
        //主数据源
        User slave1 = User.builder().id(15).name("master").build();
        userMapper.insert(slave1);

    }

}

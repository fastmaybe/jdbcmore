package com.example.jdbcmore;

import com.example.jdbcmore.dao.UserJdbcDao;
import com.example.jdbcmore.mapper.db1.IUserMapper;
import com.example.jdbcmore.mapper.db2.IUser2Mapper;
import com.example.jdbcmore.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@ExtendWith(SpringExtension.class)  //Junit5
@SpringBootTest
class JdbcmoreApplicationTests {


    @Autowired
    private UserJdbcDao userJdbcDao;

    @Resource
    private IUserMapper userMapper;

    @Resource
    private IUser2Mapper userMapper2;
//    @Autowired
//    @Qualifier("primaryJdbcTemplate")
//    private JdbcTemplate primaryJdbcTemplate;

//    @Autowired
//    @Qualifier("secondaryJdbcTemplate")
//    private JdbcTemplate secondaryJdbcTemplate;

    @Test
    void contextLoads() {


    }

    @Test
    void save1Test1() {
//        User user = User.builder().id(1).name("11").build();
//
//
//        userJdbcDao.save1(primaryJdbcTemplate,user);

    }
    @Test
    void save1Test2() {
        User user = User.builder().id(1).name("11").build();


//        userJdbcDao.save1(secondaryJdbcTemplate,user);


    }

    @Test
    void query1() {

//        userJdbcDao.save1(secondaryJdbcTemplate,user);
        User user1 = userMapper.selectByPrimaryKey(1);
        User user2 = userMapper2.selectByPrimaryKey(1);
        System.out.println();
        System.out.println(user1.getName());
        System.out.println(user2.getName());

    }
}

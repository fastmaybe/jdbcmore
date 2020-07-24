package com.example.jdbcmore;

import com.example.jdbcmore.mapper.UserMapper;
import com.example.jdbcmore.pojo.User;
import com.example.jdbcmore.service.DynamicService1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

//@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)  //Junit5
@SpringBootTest
class JdbcmoreApplicationTests {


    @Resource
    private UserMapper userMapper;

    @Resource
    private DynamicService1 dynamicService1;

    @Test
    void contextLoads() {
        // db1 写  db2 读
        User slave = User.builder().id(3).name("master").build();
        userMapper.insert(slave);

        User user = userMapper.selectByPrimaryKey(2);
        System.out.println(user.getName());
    }


    @Test
    void getAA(){
        dynamicService1.testHasTra3();
    }


}

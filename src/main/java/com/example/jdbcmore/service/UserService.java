//package com.example.jdbcmore.service;
//
//import com.example.jdbcmore.dao.UserJdbcDao;
//import com.example.jdbcmore.pojo.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//
////@Service
//public class UserService {
//
//   @Resource
//    private UserJdbcDao userJdbcDao;
//
//    @Autowired
//    @Qualifier("primaryJdbcTemplate")
//    private JdbcTemplate primaryJdbcTemplate;
//
//    @Autowired
//    @Qualifier("secondaryJdbcTemplate")
//    private JdbcTemplate secondaryJdbcTemplate;
//
//
//    @Transactional
//   public  void  testJta(){
//
//       User user = User.builder().id(6).name("555").build();
//       userJdbcDao.save1(primaryJdbcTemplate,user);
//       userJdbcDao.save2(secondaryJdbcTemplate,user);
//
//       int a= 10/0;
//
//   }
//
//}

package com.example.jdbcmore.service;

import com.example.jdbcmore.dao.UserJdbcDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

   @Resource
    private UserJdbcDao userJdbcDao;



}

package com.example.jdbcmore.dao;

import com.example.jdbcmore.pojo.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserJdbcDao {


    public void save1(JdbcTemplate jdbcTemplate, User user){
        jdbcTemplate.update("INSERT INTO user(id, name) values(?, ?)",
                user.getId(),
                user.getName());
    }

    public void save2(JdbcTemplate jdbcTemplate, User user){
        jdbcTemplate.update("INSERT INTO user(id, name) values(?, ?)",
                user.getId(),
                user.getName());
    }
}

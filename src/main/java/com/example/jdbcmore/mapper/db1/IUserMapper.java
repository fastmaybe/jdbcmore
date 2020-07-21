package com.example.jdbcmore.mapper.db1;


import com.example.jdbcmore.pojo.User;


public interface IUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}

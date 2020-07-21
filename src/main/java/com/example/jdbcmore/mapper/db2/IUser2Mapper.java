package com.example.jdbcmore.mapper.db2;

import com.example.jdbcmore.pojo.User;

public interface IUser2Mapper {


    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}

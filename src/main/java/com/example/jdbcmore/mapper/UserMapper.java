package com.example.jdbcmore.mapper;

import com.example.jdbcmore.aspect.DataSource;
import com.example.jdbcmore.pojo.User;

/**
 * @Author: liulang
 * @Date: 2020/7/23 20:52
 */

public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    @DataSource()
    int insertMaster(User record);

    @DataSource("slave")
    int insert(User record);

    int insertSelective(User record);

    @DataSource("slave")
    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}

package com.vue.dao;

import com.vue.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    public List<User> selectAll();

    User selectUserByUserCodeAndUserPassword(@Param("userCode") String userCode, @Param("userPassword")String userPassword);

    User selectUserById(Integer id);

    int userAdd(User user);
}

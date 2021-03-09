package com.vue.service.impl;

import com.vue.dao.UserMapper;
import com.vue.pojo.User;
import com.vue.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> userList() {
        return userMapper.selectAll();
    }

    @Override
    public User doLogin(String userCode, String userPassword) {
       User user=userMapper.selectUserByUserCodeAndUserPassword(userCode,userPassword);
       return user;
    }

    @Override
    public User getUserById(Integer id) {
        User user = userMapper.selectUserById(id);
        return user;
    }

    @Override
    public boolean userAdd(User user) {
  /*      boolean flag=false;
       int count=userMapper.userAdd(user);
       if (count>0){
           flag=true;
       }
       return flag;*/
        return userMapper.userAdd(user)>0?true:false;
    }
}

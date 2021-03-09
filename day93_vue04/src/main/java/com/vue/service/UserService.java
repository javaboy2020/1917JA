package com.vue.service;

import com.vue.pojo.User;

import java.util.List;

public interface UserService {
    public List<User> userList();

    User doLogin(String userCode, String userPassword);

    User getUserById(Integer id);

    boolean userAdd(User user);
}

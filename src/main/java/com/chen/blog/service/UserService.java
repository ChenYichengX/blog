package com.chen.blog.service;

import com.chen.blog.entity.User;

/**
 * @author 陈奕成
 * @create 2020 05 10 13:46
 */
public interface UserService {
    User checkUser(String userName,String passWord);
}

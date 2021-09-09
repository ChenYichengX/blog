package com.chen.blog.service.impl;

import com.chen.blog.dao.UserRepository;
import com.chen.blog.entity.User;
import com.chen.blog.service.UserService;
import com.chen.blog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 陈奕成
 * @create 2020 05 10 13:47
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String userName, String passWord) {
        User user = userRepository.findByUserNameAndPassWord(userName, MD5Util.code(passWord));
        return user;
    }
}

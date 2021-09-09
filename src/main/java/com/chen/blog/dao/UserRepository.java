package com.chen.blog.dao;

import com.chen.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 陈奕成
 * @create 2020 05 10 13:49
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserNameAndPassWord(String userName,String passWord);
}

package com.half.dao;

import com.half.domain.User;

import java.util.List;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/10 21:52
 * @Version 1.0
 * @Description
 */
public interface IUserDao {

    List<User> findAll();
}

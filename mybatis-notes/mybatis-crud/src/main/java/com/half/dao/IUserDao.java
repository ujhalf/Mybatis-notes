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

    // @Select("SELECT * FROM `user`")  使用注解配置时 应当移除resources下的mapper.xml防止冲突
    List<User> findAll();

    void save(User user);

    void update(User user);

    void delete(Integer id);

    User findOne(Integer id);

    List<User> findByName(String name);

    Integer findTotal();
}

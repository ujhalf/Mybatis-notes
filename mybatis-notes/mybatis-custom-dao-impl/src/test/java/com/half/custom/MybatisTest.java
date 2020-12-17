package com.half.custom;

import com.half.custom.dao.IUserDao;
import com.half.custom.dao.impl.UserDaoImpl;
import half.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/15 14:23
 * @Version 1.0
 * @Description
 */
public class MybatisTest {
    InputStream is;
    IUserDao userDao;

    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        userDao = new UserDaoImpl(factory);

    }

    /*查询所有*/
    @Test
    public void findAll() {
        List<User> users = userDao.findAll();
        users.forEach(System.out::println);
    }
    /*添加*/
    @Test
    public void add() {
        User user=new User();
        user.setUsername("Lebron");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("Los Angeles");
        System.out.println(user);
        userDao.save(user);
        System.out.println(user);
    }

    /*修改*/
    @Test
    public void update() {
        User user=new User();
        user.setId(56);
        user.setUsername("James");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("Los Angeles");
        System.out.println(user);
        userDao.update(user);
        System.out.println(user);
    }

    /*删除*/
    @Test
    public void delete() {
        userDao.delete(56);
    }
    /*findOne*/
    @Test
    public void findOne() {
        User one = userDao.findOne(55);
        System.out.println(one);
    }

    @Test
    public void findByName() {
        List<User> users = userDao.findByName("王");
        users.forEach(System.out::println);
    }

    @Test
    public void findTotal() {
        System.out.println(userDao.findTotal());
    }
}

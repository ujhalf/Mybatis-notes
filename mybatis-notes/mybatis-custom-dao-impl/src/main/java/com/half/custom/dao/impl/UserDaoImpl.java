package com.half.custom.dao.impl;

import com.half.custom.dao.IUserDao;
import half.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/15 14:14
 * @Version 1.0
 * @Description
 */
public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public List<User> findAll() {

        SqlSession session = factory.openSession();
        List<User> users = session.selectList("com.half.custom.dao.IUserDao.findAll");
        session.close();

        return users;
    }

    @Override
    public void save(User user) {
        SqlSession session = factory.openSession();
        session.insert("com.half.custom.dao.IUserDao.save", user);
        session.commit();
        session.close();
    }

    @Override
    public void update(User user) {
        SqlSession session = factory.openSession();
        session.update("com.half.custom.dao.IUserDao.update", user);
        session.commit();
        session.close();
    }

    @Override
    public void delete(Integer id) {
        SqlSession session = factory.openSession();
        session.delete("com.half.custom.dao.IUserDao.delete", id);
        session.commit();
        session.close();
    }

    @Override
    public User findOne(Integer id) {
        SqlSession session = factory.openSession();
        User user = session.selectOne("com.half.custom.dao.IUserDao.findOne", id);
        session.close();
        return user;
    }

    @Override
    public List<User> findByName(String name) {

        SqlSession session = factory.openSession();
        List<User> users = session.selectList("com.half.custom.dao.IUserDao.findByName",name);
        session.close();

        return users;
    }

    @Override
    public Integer findTotal() {
        SqlSession session = factory.openSession();
        Integer total = session.selectOne("com.half.custom.dao.IUserDao.findTotal");
        session.close();

        return total;
    }
}

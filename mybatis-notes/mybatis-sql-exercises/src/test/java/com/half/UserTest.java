package com.half;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sql.dao.IAccountDao;
import sql.dao.IUserDao;
import sql.domain.Account;
import sql.domain.AccountUser;
import sql.domain.Role;
import sql.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/14 12:43
 * @Version 1.0
 * @Description
 */
public class UserTest {

    private InputStream is;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        if (sqlSession != null) {
            sqlSession.close();
        }
        if (is != null) {
            is.close();
        }
    }


    @Test
    public void testFindAll() throws IOException {
        List<User> accounts = userDao.findAll();
        for (User user : accounts) {
            System.out.println("---------------");
            System.out.println(user);
            List<Account> accounts1 = user.getAccounts();
            if (accounts1 != null && accounts1.size() > 0) {
                accounts1.forEach(System.out::println);
            }
        }
    }
    @Test
    public void findAllRoles() throws IOException {
        List<User> users = userDao.findAllRoles();
        for (User user : users) {
            System.out.println("---------------");
            System.out.println(user);
            List<Role> roles = user.getRoles();
            if (roles != null && roles.size() > 0) {
                roles.forEach(System.out::println);
            }
        }
    }



}

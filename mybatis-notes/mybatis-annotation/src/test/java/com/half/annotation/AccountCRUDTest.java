package com.half.annotation;

import com.half.annotation.dao.IAccountDao;
import com.half.annotation.dao.IUserDao;
import com.half.annotation.domain.Account;
import com.half.annotation.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/18 11:32
 * @Version 1.0
 * @Description
 */
public class AccountCRUDTest {
    InputStream is;
    SqlSessionFactory factory;
    SqlSession session;
    IAccountDao accountDao;

    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(is);
        session = factory.openSession();
        accountDao = session.getMapper(IAccountDao.class);

    }

    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        is.close();
    }

    @Test
    public void save() {
        User user = new User();
        user.setAddress("changpin");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setUserName("green");
        // accoun.save(user);
    }


    @Test
    public void findAll() {
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

    @Test
    public void update() {
        User user = new User();
        user.setUserId(57);
        user.setAddress("changpin");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setUserName("green_updated");
        // accoun.update(user);
    }

    @Test
    public void delete() {
        //  accoun.delete(57);

    }

    @Test
    public void findOne() {
        // User one = accoun.findOne(54);
        // System.out.println(one);
    }



}

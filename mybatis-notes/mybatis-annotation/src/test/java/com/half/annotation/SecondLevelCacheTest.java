package com.half.annotation;

import com.half.annotation.dao.IUserDao;
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

/**
 * @Author Hui-min Lu
 * @Date 2020/12/18 14:13
 * @Version 1.0
 * @Description
 */
public class SecondLevelCacheTest {
    InputStream is;
    SqlSessionFactory factory;
    SqlSession session;
    IUserDao userDao;

    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(is);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);

    }

    @After
    public void destroy() throws IOException {

        is.close();
    }

    @Test
    public void findOne() {
        User one = userDao.findOne(54);
        System.out.println(one);
        session.close();
        session=factory.openSession();
        userDao=session.getMapper(IUserDao.class);
        User user2 = userDao.findOne(54);
        System.out.println(user2);
    }

}

package com.half;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sql.dao.IUserDao;
import sql.domain.Account;
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
public class SecondLevelCacheTest {

    private InputStream is;

    private SqlSessionFactory factory;

    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(is);

    }

    @After
    public void destroy() throws IOException {
        if (is != null) {
            is.close();
        }
    }


    @Test
    public void fistLevelCache() throws IOException {

        SqlSession sqlSession1 = factory.openSession();
        IUserDao userDao1 = sqlSession1.getMapper(IUserDao.class);
        User one = userDao1.findOne(41);
        System.out.println(one);
        sqlSession1.close();//一级缓存清空

        SqlSession sqlSession2 = factory.openSession();
        IUserDao userDao2 = sqlSession2.getMapper(IUserDao.class);
        User two = userDao2.findOne(41);
        System.out.println(two);
        sqlSession2.close();


    }


}

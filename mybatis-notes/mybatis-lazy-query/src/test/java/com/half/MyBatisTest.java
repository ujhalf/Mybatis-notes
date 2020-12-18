package com.half;

import sql.dao.IAccountDao;
import sql.dao.IUserDao;
import sql.domain.Account;
import sql.domain.AccountUser;
import sql.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/14 12:43
 * @Version 1.0
 * @Description
 */
public class MyBatisTest {

    private InputStream is;
    private SqlSession sqlSession;
    private IAccountDao accountDao;

    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = factory.openSession();
        accountDao = sqlSession.getMapper(IAccountDao.class);
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
        List<Account> accounts = accountDao.findAll();
      //  System.out.println(accounts);
//        for (Account account : accounts) {
//            System.out.println(account);
//            //System.out.println(account.getUser());
//        }
    }

    @Test
    public void testFindAllAccounts() throws IOException {
        List<AccountUser> accounts = accountDao.findAllAccounts();
        accounts.forEach(System.out::println);
    }


}

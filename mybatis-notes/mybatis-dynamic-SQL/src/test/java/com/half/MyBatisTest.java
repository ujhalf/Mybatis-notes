package com.half;

import com.half.dao.IUserDao;
import com.half.domain.QueryVo;
import com.half.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
        List<User> users = userDao.findAll();
        users.forEach(System.out::println);
    }





    @Test
    public void findOne() {
        User one = userDao.findOne(52);
        System.out.println(one);
        sqlSession.commit();
    }

    @Test
    public void findByName() {
        List<User> byName = userDao.findByName("王");
        byName.forEach(System.out::println);
    }

    @Test
    public void findTotal() {
        Integer total = userDao.findTotal();
        System.out.println(total);
    }

    @Test
    public void findByQueryVo() {
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUserName("luhuimin");
        queryVo.setUser(user);
        List<User> users = userDao.findByQueryVo(queryVo);
        users.forEach(System.out::println);


    }


    @Test
    public void findUserInIds() {
        QueryVo queryVo = new QueryVo();
        User user = new User();
        List<Integer>ids=new ArrayList<>();
        ids.add(43);
        ids.add(52);
        ids.add(46);
        queryVo.setIds(ids);
        queryVo.setUser(user);
        List<User> users = userDao.findUserInIds(queryVo);
        users.forEach(System.out::println);


    }
}

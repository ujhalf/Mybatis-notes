package com.half;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sql.dao.IRoleDao;
import sql.dao.IUserDao;
import sql.domain.Account;
import sql.domain.Role;
import sql.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/16 23:57
 * @Version 1.0
 * @Description
 */
public class RoleTest {

    private InputStream is;
    private SqlSession sqlSession;
    private IRoleDao roleDao;

    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = factory.openSession();
        roleDao = sqlSession.getMapper(IRoleDao.class);
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
        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            System.out.println("begin---------------");
            System.out.println(role);
            List<User> users = role.getUsers();
            if (users != null && users.size() > 0) {
                users.forEach(System.out::println);
            }
            System.out.println("end---------------");
        }
    }

}

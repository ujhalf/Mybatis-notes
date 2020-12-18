package com.half.annotation;

import com.half.annotation.dao.IUserDao;
import com.half.annotation.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/18 11:20
 * @Version 1.0
 * @Description
 */
public class AnnoTest {
    public static void main(String[] args) throws IOException {

        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();

        IUserDao userdao = session.getMapper(IUserDao.class);
        List<User> users = userdao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        session.close();
        is.close();

    }
}

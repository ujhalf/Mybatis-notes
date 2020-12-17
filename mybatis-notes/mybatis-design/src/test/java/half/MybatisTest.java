package half;


import half.domain.User;
import half.dao.IUserDao;


import half.mybatis.io.Resources;
import half.mybatis.sqlsession.SqlSession;
import half.mybatis.sqlsession.SqlSessionFactoryBuilder;
import half.mybatis.sqlsession.SqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/10 23:45
 * @Version 1.0
 * @Description
 */
public class MybatisTest {
    public static void main(String[] args) throws IOException {
        //1.读取配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory  构建者模式 隐藏构建细节
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        //3.创建SqlSession对象  工厂模式:解耦
        SqlSession sqlSession = factory.openSession();
        //4.创建dao接口的代理对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        //5.使用代理对象执行方法  代理模式:不修改源码的基础上对方法实现增强
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
            //6.释放 System.out.println(user);
        }
        sqlSession.close();
        is.close();


    }
}

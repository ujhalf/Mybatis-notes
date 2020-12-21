<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="com.half.dao.IUserDao" %>
<%@ page import="com.half.domain.User" %>
<%@ page import="java.util.List" %>
<%@page language="java" contentType="text/html;UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
<h2>Hello World!</h2>

<%

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
    }
    //6.释放资源
    sqlSession.close();
    is.close();



%>
</body>
</html>

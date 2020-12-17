package half.mybatis.sqlsession.defaults;

import half.mybatis.cfg.Configuration;
import half.mybatis.sqlsession.SqlSession;
import half.mybatis.sqlsession.proxy.MapperProxy;
import half.mybatis.sqlsession.SqlSessionFactory;
import half.utils.DataSourceUtil;


import javax.sql.DataSource;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/12 11:17
 * @Version 1.0
 * @Description
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;
    private Connection connection;

    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        this.connection = DataSourceUtil.getConnection(cfg);
    }


    //创建代理对象 执行查询 封装结果
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},
                new MapperProxy(cfg.getMappers(), connection));

    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

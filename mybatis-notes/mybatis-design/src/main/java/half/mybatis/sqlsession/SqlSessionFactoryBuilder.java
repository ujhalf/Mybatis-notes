package half.mybatis.sqlsession;


import half.mybatis.cfg.Configuration;
import half.mybatis.sqlsession.defaults.DefaultSqlsessionFactory;
import half.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/12 10:49
 * @Version 1.0
 * @Description 用于创建SqlSessionFactory
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream config) {

        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);

        return new DefaultSqlsessionFactory(cfg);
    }
}

package half.mybatis.sqlsession.defaults;

import half.mybatis.cfg.Configuration;
import half.mybatis.sqlsession.SqlSession;
import half.mybatis.sqlsession.SqlSessionFactory;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/12 11:15
 * @Version 1.0
 * @Description
 */
public class DefaultSqlsessionFactory implements SqlSessionFactory {
    private Configuration cfg;

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }

    public DefaultSqlsessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

}

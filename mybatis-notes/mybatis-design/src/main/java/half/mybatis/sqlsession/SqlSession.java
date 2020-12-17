package half.mybatis.sqlsession;

import half.dao.IUserDao;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/12 10:54
 * @Version 1.0
 * @Description
 */
public interface SqlSession {
    <T> T getMapper(Class<T> daoInterfaceClass);

    void close();
}

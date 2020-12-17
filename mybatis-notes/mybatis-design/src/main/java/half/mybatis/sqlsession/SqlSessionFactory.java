package half.mybatis.sqlsession;


/**
 * @Author Hui-min Lu
 * @Date 2020/12/12 10:50
 * @Version 1.0
 * @Description
 */
public interface SqlSessionFactory {
    SqlSession openSession();

}

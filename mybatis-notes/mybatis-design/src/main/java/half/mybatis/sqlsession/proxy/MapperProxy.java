package half.mybatis.sqlsession.proxy;

import half.mybatis.cfg.Mapper;
import half.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/12 11:21
 * @Version 1.0
 * @Description
 */
public class MapperProxy implements InvocationHandler {
    private Map<String, Mapper> mapppers;

    public MapperProxy(Map<String, Mapper> mapppers, Connection connection) {
        this.mapppers = mapppers;
        this.connection = connection;
    }

   private  Connection connection;



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        String key = className + "." + methodName;
        Mapper mapper = mapppers.get(key);
        if (mapper == null) {
            throw new IllegalAccessException("参数异常");
        }
        //调用工具类 执行查询所有
        return new Executor().selectList(mapper,connection);



    }
}

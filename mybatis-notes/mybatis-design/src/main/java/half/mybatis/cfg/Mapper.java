package half.mybatis.cfg;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/12 11:02
 * @Version 1.0
 * @Description 封装sql语句 与执行结果的全限定类名
 */
public class Mapper {
   private String queryString;
   private String resultType;

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}

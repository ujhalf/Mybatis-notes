package half.mybatis.io;

import java.io.InputStream;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/12 10:47
 * @Version 1.0
 * @Description  根据配置文件 获取输入流
 */
public class Resources {
    public static InputStream getResourceAsStream(String path) {
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }
}

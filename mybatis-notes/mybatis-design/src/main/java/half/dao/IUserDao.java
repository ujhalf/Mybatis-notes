package half.dao;


import half.domain.User;
import half.mybatis.annotations.Select;

import java.util.List;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/10 21:52
 * @Version 1.0
 * @Description
 */
public interface IUserDao {

   // @Select("SELECT * FROM `user`")  //使用注解配置时 应当移除resources下的mapper.xml防止冲突
    List<User> findAll();
}

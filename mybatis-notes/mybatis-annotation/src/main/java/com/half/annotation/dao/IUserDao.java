package com.half.annotation.dao;

import com.half.annotation.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/18 11:11
 * @Version 1.0
 * @Description
 */
@CacheNamespace(blocking = true)
public interface IUserDao {

    /**
     * 查询所有
     */
    @Select("select * from user")
    //为配置的userMap起一个id 此后就可以引用这个
    @Results(id = "userMap", value = {@Result(id = true, column = "id", property = "userId"),
            @Result(column = "username", property = "userName"),
            //一对多使用@Many注解
            @Result(property = "accounts", column = "id",
                    many = @Many(select = "com.half.annotation.dao.IAccountDao.findByUid" ,
                            fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    /*添加**/
    @Insert("insert into user(username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    void save(User user);

    @Update("update user set username=#{username},sex=#{sex},address=#{address},birthday=#{birthday} where id=#{id}")
    void update(User user);

    @Delete("delete from user where id =#{id}")
    void delete(Integer id);

    @Select("select * from user where id=#{id}")
    @ResultMap(value = {"userMap"})
    User findOne(Integer id);

    //@Select("select * from user where username like #{name}")
    @Select("select * from user where username like '%${value}%'")
    @ResultMap(value = {"userMap"})
    List<User> findByName(String name);

    @Select("select count(*) from user")
    Integer findTotal();
}

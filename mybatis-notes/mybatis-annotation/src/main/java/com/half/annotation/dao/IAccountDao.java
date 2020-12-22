package com.half.annotation.dao;

import com.half.annotation.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/18 12:25
 * @Version 1.0
 * @Description
 */
public interface IAccountDao {
    /**
     * 查询所有账户
     */
    @Select("select * from account")
    @Results(id = "accountMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "money", property = "money"),
            //一对一 使用`@One`注解
            @Result(column = "uid", property = "user", one = @One(select = "com.half.annotation.dao.IUserDao.findOne", fetchType = FetchType.EAGER)
            )
    })
    List<Account> findAll();

    @Select("select * from account where uid=#{uid}")
    List<Account> findByUid(Integer uid);
}

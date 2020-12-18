package com.half.annotation.domain;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/18 10:58
 * @Version 1.0
 * @Description
 */
public class User implements Serializable {

    private Integer userId;
    private String userName;
    private String address;
    private String sex;
    private Date birthday;

    List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


}

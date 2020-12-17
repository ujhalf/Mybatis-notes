package com.half.domain;


import java.util.List;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/16 13:03
 * @Version 1.0
 * @Description
 */
public class QueryVo {
    private User user;

    List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package sql.dao;

import sql.domain.Role;

import java.util.List;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/16 23:52
 * @Version 1.0
 * @Description
 */
public interface IRoleDao {
    /**
     * 查询所有角色
     */
    List<Role> findAll();
}

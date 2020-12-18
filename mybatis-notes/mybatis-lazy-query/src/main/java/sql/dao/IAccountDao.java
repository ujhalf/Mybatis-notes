package sql.dao;

import sql.domain.Account;
import sql.domain.AccountUser;

import java.util.List;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/16 16:10
 * @Version 1.0
 * @Description
 */
public interface IAccountDao {
    List<Account> findAll();

    List<AccountUser> findAllAccounts();

    List<Account>findById(Integer uid);

}

package org.jiangtao.daoImpl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import org.jiangtao.bean.Accounts;
import org.jiangtao.utils.Collections;
import org.jiangtao.utils.TokenProcessor;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by MrJiang on 2016/3/17. des:自定义的dao层，实现D的某些方法 简单有效
 */
public class AccountsDaoImpl {

  private static AccountsDaoImpl instance;
  private ConnectionSource collections;
  private Dao<Accounts, java.lang.String> accountDao;

  private AccountsDaoImpl() {
  }

  public static AccountsDaoImpl getInstance() {
    if (instance == null) {
      synchronized (AccountsDaoImpl.class) {
        instance = new AccountsDaoImpl();
      }
    }
    return instance;
  }

  public Accounts getAccount(String phone, String password) {

    collections = Collections.getInstance().openConnectionResource();
    try {
      accountDao = DaoManager.createDao(collections, Accounts.class);
      ArrayList<Accounts> accountsArrayList = (ArrayList<Accounts>) accountDao.queryForAll();
      if (accountsArrayList != null) {
        for (Accounts accounts : accountsArrayList) {
          if (phone.equals(accounts.getPhone()) && password.equals(accounts.getPassword())) {
            java.lang.String token = TokenProcessor.getInstance().generateToken(phone, true);
            Accounts accounts1 = new Accounts(token);
            accountDao.updateId(accounts1, accounts.getId() + "");
            Accounts accounts2 = accountDao.queryForId(accounts.getId() + "");
            return accounts2;
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Collections.getInstance().closeConnectionResource(collections);
    }
    return null;
  }

  public Accounts updateAccounts(String userName, String id) {
    collections = Collections.getInstance().openConnectionResource();
    try {
      accountDao = DaoManager.createDao(collections, Accounts.class);
      Accounts accounts = accountDao.queryForId(id);
      accounts.setUsername(userName);
      accountDao.update(accounts);
      return accounts;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Collections.getInstance().closeConnectionResource(collections);
    }
    return null;
  }

  public Accounts updateAccountsSex(String sex, String id) {
    collections = Collections.getInstance().openConnectionResource();
    try {
      accountDao = DaoManager.createDao(collections, Accounts.class);
      Accounts accounts = accountDao.queryForId(id);
      accounts.setSex(sex);
      accountDao.update(accounts);
      return accounts;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Collections.getInstance().closeConnectionResource(collections);
    }
    return null;
  }

  public Accounts updateAccountsAge(String age, String id) {
    collections = Collections.getInstance().openConnectionResource();
    try {
      accountDao = DaoManager.createDao(collections, Accounts.class);
      Accounts accounts = accountDao.queryForId(id);
      accounts.setAge(age);
      accountDao.update(accounts);
      return accounts;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Collections.getInstance().closeConnectionResource(collections);
    }
    return null;
  }
}
